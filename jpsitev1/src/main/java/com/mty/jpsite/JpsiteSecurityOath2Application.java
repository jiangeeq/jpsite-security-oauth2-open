package com.mty.jpsite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jiangpeng
 * @Description
 * @date 2019/10/1214:53
 */
@SpringBootApplication
@MapperScan("com.mty.jpsite.mapper")
public class JpsiteSecurityOath2Application {
    public static void main(String[] args) {
        SpringApplication.run(JpsiteSecurityOath2Application.class, args);
    }
}
