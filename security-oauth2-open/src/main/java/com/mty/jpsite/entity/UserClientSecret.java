package com.mty.jpsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  用户秘钥关联表
 * @author jiangpeng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserClientSecret {
    private int userId;
    private String clientId;
}
