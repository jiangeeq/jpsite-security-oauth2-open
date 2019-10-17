package com.mty.jpsite.entity;

import lombok.Data;

@Data
public class Permission {
	private Integer id;
	// 权限名称
	private String permName;
	// 权限标识
	private String permTag;
	// 请求url
	private String url;
}
