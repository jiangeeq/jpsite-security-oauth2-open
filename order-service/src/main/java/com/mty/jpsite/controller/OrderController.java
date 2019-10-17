package com.mty.jpsite.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiangpeng
 * @Description
 * @date 2019/10/1416:53
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @RequestMapping("/addOrder")
    public String addOrder() {
        return "addOrder";
    }
    @RequestMapping("/updateOrder")
    public String updateOrder() {
        return "updateOrder";
    }
    @RequestMapping("/queryOrder")
    public String queryOrder() {
        return "queryOrder";
    }
    @RequestMapping("/deleteOrder")
    public String deleteOrder() {
        return "deleteOrder";
    }
}
