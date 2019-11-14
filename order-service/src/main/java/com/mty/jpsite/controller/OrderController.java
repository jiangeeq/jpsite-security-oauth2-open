package com.mty.jpsite.controller;

import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/")
    public String order() {
        return "order";
    }

    @GetMapping("/addOrder")
    public String addOrder() {
        return "addOrder";
    }

    @GetMapping("/updateOrder")
    public String updateOrder() {
        return "updateOrder";
    }

    @GetMapping("/queryOrder")
    public String queryOrder() {
        return "queryOrder";
    }

    @GetMapping("/deleteOrder")
    public String deleteOrder() {
        return "deleteOrder";
    }
}
