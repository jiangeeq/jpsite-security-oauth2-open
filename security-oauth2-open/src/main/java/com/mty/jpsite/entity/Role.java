package com.mty.jpsite.entity;

import lombok.Data;

// 角色信息表
@Data
public class Role {
	private Integer id;
	private String roleName;
	private String roleDesc;
}
