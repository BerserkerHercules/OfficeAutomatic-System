package com.zjw.oa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description
 *
 * @author dddz97
 * @date 2019-03-21 10:48:00
 */
@MapperScan("com.zjw.oa.mapper")
@SpringBootApplication
public class OaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OaApplication.class, args);
        System.out.println("\nSpringboot have been started!");
    }

}
