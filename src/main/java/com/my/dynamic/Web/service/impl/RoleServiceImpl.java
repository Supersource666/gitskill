package com.my.dynamic.Web.service.impl;

import com.my.dynamic.Web.dao.PermissionDao;
import com.my.dynamic.Web.dao.RoleDao;
import com.my.dynamic.Web.service.BaseService;
import com.my.dynamic.Web.service.RoleService;
import com.my.dynamic.entity.shiro.Permission;
import com.my.dynamic.entity.shiro.Role;
import com.my.dynamic.entity.shiro.User;
import com.my.dynamic.utils.IdWorker;
import com.my.dynamic.utils.PermissionConstants;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl extends BaseService implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private IdWorker idWorker;


    /**
     * 给角色分配权限
     * @param roleId
     * @param permIds
     */
    @Override
    public void assignPerms(String roleId, List<String> permIds) {

        //获取分配的角色对象
        Role role = roleDao.findById(roleId).get();
        //构造角色的权限集合
        Set<Permission> perms=new HashSet<>();
        for (String permId : permIds) {
            Permission permission = permissionDao.findById(permId).get();
            //需要根据父id和类型查询API权限列表
            List<Permission> apiList= permissionDao.findByTypeAndPid(PermissionConstants.PERMISSION_API,permission.getId());
            perms.addAll(apiList);
            perms.add(permission);
        }
        //设置角色和权限的关系
        role.setPermissions(perms);
        //更新角色
        roleDao.save(role);

    }

    /**
     * 添加用户角色
     * @param role
     */
    @Override
    public void save(Role role) {
        role.setId(idWorker.nextId()+"");
        roleDao.save(role);
    }

    /**
     * 更新用户角色
     * @param role
     */
    @Override
    public void update(Role role) {
        Role target = roleDao.findById(role.getId()).get();
        target.setDescription(role.getDescription());
        target.setName(role.getName());
        roleDao.save(target);
    }

    /**
     * 根据id删除用户角色
     * @param id
     */
    @Override
    public void delete(String id) {
        roleDao.deleteById(id);
    }

    /**
     * 根据id查询用户角色的信息
     * @param id
     * @return
     */
    @Override
    public Role findById(String id) {
        return roleDao.findById(id).get();
    }

    /**
     * 分页查询角色
     * @param companyId
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public Page<Role> findByPage(String companyId, int page, int pageSize) {


        Specification<Role> spec=new Specification<Role>() {

            /**
             * 动态拼接查询条件
             * @param root
             * @param query
             * @param criteriaBuilder
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                List<Predicate> list=new ArrayList<>();

                //根据请求的companyId
                if(!StringUtils.isEmpty(companyId)){
                    list.add(criteriaBuilder.equal(root.get("companyId").as(String.class),companyId));
                }

                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };



        return roleDao.findAll(spec, PageRequest.of(page-1,pageSize));
    }

    /**
     * 查询所有的角色
     * @param companyId
     * @return
     */
    @Override
    public List<Role> findAll(String companyId) {
        return roleDao.findAll(getSpec(companyId));
    }
}
