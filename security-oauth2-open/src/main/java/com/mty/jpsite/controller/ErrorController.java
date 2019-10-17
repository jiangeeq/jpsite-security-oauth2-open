package com.mty.jpsite.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiangpeng
 * @Description
 * @date 2019/10/1215:10
 */
@RestController
public class ErrorController {
    @GetMapping("/error/403")
    public String error(){
        return "/error/403";
    }
}
