package com.mty.jpsite.mapper;

import com.mty.jpsite.entity.UserClientSecret;
import org.apache.ibatis.annotations.Insert;

/**
 * @author jiangpeng
 * @Description
 * @date 2019/11/1315:36
 */
public interface UserClientSecretMapper {
    @Insert("insert into user_client_secret values(#{userId}, #{clientId})")
    void save(UserClientSecret userClientSecret);
}
