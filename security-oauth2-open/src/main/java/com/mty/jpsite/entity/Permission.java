package com.mty.jpsite.entity;

import lombok.Data;

/**
 * 系统权限表
 * @author jiangpeng
 */
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
