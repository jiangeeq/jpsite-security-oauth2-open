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
@RequestMapping("/api/product")
public class ProductController {

    @GetMapping("/")
    public String product() {
        return "product";
    }

    @GetMapping("/addProduct")
    public String addProduct() {
        return "addProduct";
    }

    @GetMapping("/updateProduct")
    public String updateProduct() {
        return "updateProduct";
    }

    @GetMapping("/queryProduct")
    public String queryProduct() {
        return "queryProduct";
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct() {
        return "deleteProduct";
    }
}
