package org.milkteaboy.simplefarm.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 单元测试Springboot启动类
 */
@SpringBootApplication(scanBasePackages = {"org.milkteaboy.simplefarm"})
@MapperScan("org.milkteaboy.simplefarm.dao")
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class);
    }

}
