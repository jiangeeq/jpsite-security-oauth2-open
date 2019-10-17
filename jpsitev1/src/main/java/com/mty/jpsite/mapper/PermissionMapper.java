package com.mty.jpsite.mapper;

import com.mty.jpsite.entity.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author jiangpeng
 * @Description
 * @date 2019/10/1216:10
 */
public interface PermissionMapper {
    @Select(" select * from sys_permission ")
    List<Permission> findAllPermission();
}
