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
    public String error403(){
        return "/error/403";
    }

    @GetMapping("/error/404")
    public String error404(){
        return "/error/404";
    }
}
