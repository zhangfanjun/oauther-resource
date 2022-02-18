package com.zhang.oauther2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.zhang.oauther2.mysql.mapper")
@SpringBootApplication
public class Oauther2Application {

    public static void main(String[] args) {
        SpringApplication.run(Oauther2Application.class, args);
    }

}
