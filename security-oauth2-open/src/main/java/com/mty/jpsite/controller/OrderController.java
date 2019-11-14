package com.mty.jpsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class OrderController {

	@RequestMapping("/code")
	//@ResponseBody
	public String index(HttpServletRequest request) {
		//return request.getParameter("code");
		return "code";
	}

	// 查询订单
	@RequestMapping("/showOrder")
	public String showOrder() {
		return "showOrder";
	}

	// 添加订单
	@RequestMapping("/addOrder")
	public String addOrder() {
		return "addOrder";
	}

	// 修改订单
	@RequestMapping("/updateOrder")
	public String updateOrder() {
		return "updateOrder";
	}

	// 删除订单
	@RequestMapping("/deleteOrder")
	public String deleteOrder() {
		return "deleteOrder";
	}
}
