package com.mty.jpsite.mapper;

import com.mty.jpsite.entity.OauthClientDetails;
import org.apache.ibatis.annotations.Insert;

/**
 * @author jiangpeng
 * @Description
 * @date 2019/10/2320:01
 */
public interface OauthClientDetailsMapper {
    @Insert("insert into oauth_client_details values(#{clientId}, #{resourceIds}, #{clientSecret}, #{scope}, #{authorizedGrantTypes}, #{webServerRedirectUri}, " +
            "#{authorities}, #{accessTokenValidity}, #{refreshTokenValidity}, #{additionalInformation}, #{autoapprove})")
    int insert(OauthClientDetails clientDetails);
}
