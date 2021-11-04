package com.my.dynamic.Web.service.impl;

import com.my.dynamic.Web.dao.PermissionApiDao;
import com.my.dynamic.Web.dao.PermissionDao;
import com.my.dynamic.Web.dao.PermissionMenuDao;
import com.my.dynamic.Web.dao.PermissionPointDao;
import com.my.dynamic.Web.service.PermissionService;
import com.my.dynamic.entity.result.ResultCode;
import com.my.dynamic.entity.shiro.Permission;
import com.my.dynamic.entity.shiro.PermissionApi;
import com.my.dynamic.entity.shiro.PermissionMenu;
import com.my.dynamic.entity.shiro.PermissionPoint;
import com.my.dynamic.exception.CommonException;
import com.my.dynamic.utils.BeanMapUtils;
import com.my.dynamic.utils.IdWorker;
import com.my.dynamic.utils.PermissionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class PermissionServiceImpl implements PermissionService {


    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private PermissionMenuDao permissionMenuDao;

    @Autowired
    private PermissionPointDao permissionPointDao;

    @Autowired
    private PermissionApiDao permissionApiDao;

    @Override
    public List<Permission> findAll(Map<String, Object> map) {

        Specification<Permission> spec = new Specification<Permission>() {
            /**
             *
             * @param root 查询哪个表,这里与pe_permission关联
             * @param query 查询哪些字段，排序是什么
             * //把Predicate应用到CriteriaQuery中去,因为还可以给CriteriaQuery
             *   添加其他的功能，比如排序、分组啥的
             *   query.where(cb.and(p3,cb.or(p1,p2)));
             * //添加排序的功能
             * query.orderBy(cb.desc(root.get("uuid").as(Integer.class)));
             * @param cb 字段之间是什么关系，如何生成一个查询条件，每一个查询条件都是什么方式
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> list = new ArrayList<>();
                //根据父id查询
                if (!StringUtils.isEmpty(map.get("pid"))) {
                    list.add(cb.equal(root.get("pid").as(String.class), (String) map.get("pid")));
                }
                //根据enVisible查询
                if (!StringUtils.isEmpty(map.get("enVisible"))) {
                    list.add(cb.equal(root.get("enVisible").as(String.class), (String) map.get("enVisible")));
                }
                //根据类型 type
                if (!StringUtils.isEmpty(map.get("type"))) {
                    String type = (String) map.get("type");
                    CriteriaBuilder.In<Object> in = cb.in(root.get("type"));
                    if ("0".equals(type)) {
                        in.value(1).value(2);
                    } else {
                        in.value(Integer.parseInt(type));
                    }
                    list.add(in);
                }

                return cb.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return permissionDao.findAll(spec);
    }

    /**
     * 保存权限
     * @param map
     */
    @Override
    public void save(Map<String, Object> map) throws Exception {

        //设置主键的值
        String id = idWorker.nextId()+"";
        //通过map构造permission对象
        Permission perm = BeanMapUtils.mapToBean(map, Permission.class);
        perm.setId(id);
//        //根据类型构造不同的资源对象(菜单、按钮、api)
//        int type= perm.getType();
//        switch (type) {
//            case PermissionConstants.PERMISSION_MENU:
//                PermissionMenu menu = BeanMapUtils.mapToBean(map, PermissionMenu.class);
//                menu.setId(id);
//                permissionMenuDao.save(menu);
//                break;
//            case PermissionConstants.PERMISSION_POINT:
//                PermissionPoint point = BeanMapUtils.mapToBean(map, PermissionPoint.class);
//                point.setId(id);
//                permissionPointDao.save(point);
//                break;
//            case PermissionConstants.PERMISSION_API:
//                PermissionApi api = BeanMapUtils.mapToBean(map, PermissionApi.class);
//                api.setId(id);
//                permissionApiDao.save(api);
//                break;
//            default:
//                throw new CommonException(ResultCode.FAIL);
//        }
        //保存
        permissionDao.save(perm);
    }

    /**
     * 修改权限
     * @param map
     */
    @Override
    public void update(Map<String, Object> map) throws Exception {
        Permission perm = BeanMapUtils.mapToBean(map,Permission.class);
        //1.通过传递的权限id查询权限
        Permission permission = permissionDao.findById(perm.getId()).get();
        permission.setName(perm.getName());
        permission.setCode(perm.getCode());
        permission.setDescription(perm.getDescription());
        permission.setEnVisible(perm.getEnVisible());
        //2.根据类型构造不同的资源
        int type = perm.getType();
        switch (type) {
            case PermissionConstants.PERMISSION_MENU:
                PermissionMenu menu = BeanMapUtils.mapToBean(map,PermissionMenu.class);
                menu.setId(perm.getId());
                permissionMenuDao.save(menu);
                break;
            case PermissionConstants.PERMISSION_POINT:
                PermissionPoint point = BeanMapUtils.mapToBean(map,PermissionPoint.class);
                point.setId(perm.getId());
                permissionPointDao.save(point);
                break;
            case PermissionConstants.PERMISSION_API:
                PermissionApi api = BeanMapUtils.mapToBean(map,PermissionApi.class);
                api.setId(perm.getId());
                permissionApiDao.save(api);
                break;
            default:
                throw new CommonException(ResultCode.FAIL);
        }
        //3.保存
        permissionDao.save(permission);
    }

    /**
     * 根据id查询权限
     * @param id
     * @return
     */
    @Override
    public Map findById(String id) throws CommonException{

        Permission perm = permissionDao.findById(id).get();
        int type=perm.getType();

        Object object=null;
        if(type == PermissionConstants.PERMISSION_MENU) {
            object = permissionMenuDao.findById(id).get();
        }else if (type == PermissionConstants.PERMISSION_POINT) {
            object = permissionPointDao.findById(id).get();
        }else if (type == PermissionConstants.PERMISSION_API) {
            object = permissionApiDao.findById(id).get();
        }else {
            throw new CommonException(ResultCode.FAIL);
        }

        Map<String, Object> map = BeanMapUtils.beanToMap(object);

        map.put("name",perm.getName());
        map.put("type",perm.getType());
        map.put("code",perm.getCode());
        map.put("description",perm.getDescription());
        map.put("pid",perm.getPid());
        map.put("enVisible",perm.getEnVisible());


        return map;

    }

    /**
     * 根据id删除
     * @param id
     */
    @Override
    public void deleteById(String id) throws CommonException{
        //1.通过传递的权限id查询权限
        Permission permission = permissionDao.findById(id).get();
        permissionDao.delete(permission);
        //2.根据类型构造不同的资源
        int type = permission.getType();
        switch (type) {
            case PermissionConstants.PERMISSION_MENU:
                permissionMenuDao.deleteById(id);
                break;
            case PermissionConstants.PERMISSION_POINT:
                permissionPointDao.deleteById(id);
                break;
            case PermissionConstants.PERMISSION_API:
                permissionApiDao.deleteById(id);
                break;
            default:
                throw new CommonException(ResultCode.FAIL);
        }
    }
}
