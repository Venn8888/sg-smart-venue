package com.sg.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author dwf
 * @date 2020/7/29 22:44
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.sg.**"})
@MapperScan(basePackages = "com.sg.**.mapper")
public class SystemRpcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemRpcApplication.class, args);
    }

}
