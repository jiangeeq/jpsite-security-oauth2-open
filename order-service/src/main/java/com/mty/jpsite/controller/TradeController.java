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
@RequestMapping("/api/trade")
public class TradeController {

    @GetMapping("/")
    public String trade() {
        return "trade";
    }

    @GetMapping("/addTrade")
    public String addTrade() {
        return "addTrade";
    }

    @GetMapping("/updateTrade")
    public String updateTrade() {
        return "updateTrade";
    }

    @GetMapping("/queryTrade")
    public String queryTrade() {
        return "queryTrade";
    }

    @GetMapping("/deleteTrade")
    public String deleteTrade() {
        return "deleteTrade";
    }
}
