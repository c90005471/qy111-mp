package com.aaa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.aaa.dao")
public class Qy111MpApplication {

    public static void main(String[] args) {
        SpringApplication.run(Qy111MpApplication.class, args);
    }

}
