package com.sg.gateway.server.controller;

import com.sg.gateway.server.configuration.ApiProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xxxxxxx
 * @date: 2018/11/5 16:33
 * @description:
 */
@RestController
public class IndexController {
    @Autowired
    private ApiProperties apiProperties;

    @Value("${spring.application.name}")
    private String serviceId;

    @GetMapping("/")
    public String index() {
        if (apiProperties.getApiDebug()) {
            return "redirect:doc.html";
        }
        return "index";
    }
}
