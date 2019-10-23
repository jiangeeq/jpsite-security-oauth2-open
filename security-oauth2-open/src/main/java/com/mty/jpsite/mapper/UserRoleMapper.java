package com.mty.jpsite.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @author jiangpeng
 * @Description 用户角色表Mapper
 * @date 2019/10/2315:38
 */
public interface UserRoleMapper {

    @Insert("insert into sys_user_role(user_id, role_id) values(#{userId}, #{roleId})")
    void insert(@Param("userId") int userId, @Param("roleId") int roleId);
}