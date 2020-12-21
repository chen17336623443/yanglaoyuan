package com.yanglaoyuan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.yanglaoyuan.model.Mapper"})
public class YanglaoyuanApplication {

    public static void main(String[] args) {
        SpringApplication.run(YanglaoyuanApplication.class, args);
    }

}
