package com.zjw.oa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description springboot
 *
 * @author ZhengJiawei
 * @date 2019-03-19 17:19:15
 */
@RestController
@SpringBootApplication
public class OaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OaApplication.class, args);
    }
    @RequestMapping("/")
    String index() {
        return "Hello Spring Boot";
    }
}
