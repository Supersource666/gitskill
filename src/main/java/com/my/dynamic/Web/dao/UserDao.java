package com.my.dynamic.Web.dao;

import com.my.dynamic.entity.shiro.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * JpaRepository<User,String>
 *  User封装的实体类   Iterable<T> save();//保存集合
 *  String数据查询的类型    boolean exists(String id);//根据id判断实体是否存在
 * JpaSpecificationExecutor<User>
 *
 */
public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {

    public User findByWorkNumber(String workNumber);

    User findByUsername(String username);

}
