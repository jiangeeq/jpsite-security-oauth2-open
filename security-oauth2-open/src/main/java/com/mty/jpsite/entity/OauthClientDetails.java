package com.mty.jpsite.entity;

import lombok.Data;

/**
 * oauth 客户秘钥表
 * @author jiangpeng
 */
@Data
public class OauthClientDetails {
    private String clientId;
    private String resourceIds;
    private String clientSecret;
    private String scope;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private String additionalInformation;
    private String autoapprove;
}
