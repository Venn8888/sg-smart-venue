package com.sg.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/7/30
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableOpenApi
@RefreshScope
@EnableCaching
public class SsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoApplication.class, args);
    }

}
