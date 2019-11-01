package com.mty.jpsite.entity;

import lombok.Data;

/**
 * @author jiangpeng
 * @Description
 * @date 2019/10/2319:49
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
