package com.stableload;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.stableload.Mapper")
public class StableloadApplication {

    public static void main(String[] args) {
        SpringApplication.run(StableloadApplication.class, args);
    }

}
