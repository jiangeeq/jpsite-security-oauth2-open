package com.mty.jpsite.mapper;

import com.mty.jpsite.entity.Permission;
import com.mty.jpsite.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author jiangpeng
 * @Description
 * @date 2019/10/1216:05
 */
public interface UserMapper {
    // 查询用户信息
    @Select(" select * from sys_user where username = #{userName}")
    User findByUsername(@Param("userName") String userName);

    @Select("select * from sys_user")
    List<User> getAllUser();

    // 查询用户的权限
    @Select(" select permission.* from sys_user user" + " inner join sys_user_role user_role"
            + " on user.id = user_role.user_id inner join "
            + "sys_role_permission role_permission on user_role.role_id = role_permission.role_id "
            +
            " inner join sys_permission permission on role_permission.perm_id = permission.id where user.username = #{userName};")
    List<Permission> findPermissionByUsername(@Param("userName") String userName);

    @Insert("insert into sys_user(id, username, realname, password, createDate, lastLoginTime, enabled, accountNonExpired, accountNonLocked, credentialsNonExpired) " +
            "values(#{id}, #{username}, #{realname}, #{password}, #{createDate}, #{lastLoginTime}, #{enabled}, #{accountNonExpired}, #{accountNonLocked}, #{credentialsNonExpired})")
    int insert(User user);

    @Select("select max(id) from sys_user")
    int getMaxId();
}
