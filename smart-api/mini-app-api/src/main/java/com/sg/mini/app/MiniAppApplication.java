package com.sg.mini.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/7/29
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableOpenApi
public class MiniAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniAppApplication.class, args);
    }

}
