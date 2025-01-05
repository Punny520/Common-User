package com.punny.commonuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.punny.commonuser.mapper")
public class CommonUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonUserApplication.class, args);
    }

}
