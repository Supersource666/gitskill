package com.my.dynamic.Web.service.impl;

import com.my.dynamic.Web.dao.RoleDao;
import com.my.dynamic.Web.dao.UserDao;
import com.my.dynamic.Web.service.UserService;
import com.my.dynamic.entity.shiro.Role;
import com.my.dynamic.entity.shiro.User;
import com.my.dynamic.utils.IdWorker;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RoleDao roleDao;

    @Override
    public User findByWorkNumber(String workNumber) {
        return userDao.findByWorkNumber(workNumber);
    }

    @Override
    public void save(User user) {
        String id=idWorker.nextId()+"";
        //对密码进行加密（密码、盐、加密次数）
        String password=new Md5Hash(user.getPassword(),user.getWorkNumber(),3).toString();
        user.setId(id);
        user.setPassword(password);
        user.setEnableState(1);
        user.setLevel("user");

        //保存数据
        userDao.save(user);

    }

    @Override
    public void deleteById(String id) {
        //根据用户id删除
        userDao.deleteById(id);
    }

    @Override
    public User findById(String id) {
        //根据id查询用户
        return userDao.findById(id).get();
    }

    @Override
    public void update(User user) {
        //1.根据id查询用户
        User target=userDao.findById(user.getId()).get();
        //2.更改用户的设置
        target.setUsername(user.getUsername());
        target.setPassword(user.getPassword());
        target.setDepartmentId(user.getDepartmentId());
        target.setDepartmentName(user.getDepartmentName());
        //3.更新用户
        userDao.save(target);
    }

    @Override
    public Page<User> findAll(Map map, int page, int size) {

        Specification<User> spec=new Specification<User>() {

            /**
             * 动态拼接查询条件
             * @param root
             * @param query
             * @param criteriaBuilder
             * @return
             */
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                List<Predicate> list=new ArrayList<>();

                //根据请求的companyId
                if(!StringUtils.isEmpty(map.get("companyId"))){
                    list.add(criteriaBuilder.equal(root.get("companyId").as(String.class),(String)map.get("companyId")));
                }

                //根据请求的部门id
                if (!StringUtils.isEmpty(map.get("departmentId"))){
                    list.add(criteriaBuilder.equal(root.get("departmentId").as(String.class),(String)map.get("departmentId")));
                }

                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };

        Page<User> pageUser=userDao.findAll(spec, PageRequest.of(page-1,size));
        return pageUser;



    }

    @Override
    public void assignRoles(String userId, List<String> roleIds) {
        //1.获取用户
        User user = userDao.findById(userId).get();

        //2.设置角色集合
        Set<Role> roles=new HashSet<>();
        for (String roleId : roleIds) {
            Role role=roleDao.findById(roleId).get();
            roles.add(role);
        }
        //3.设置用户和角色集合
        user.setRoles(roles);
        //4.更新角色
        userDao.save(user);
    }

    @Override
    public User findByUserName(String username) {

        User user = null;
        try {
            user = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return user;

    }
}
