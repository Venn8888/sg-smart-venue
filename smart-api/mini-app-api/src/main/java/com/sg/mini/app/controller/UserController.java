package com.sg.mini.app.controller;

import com.sg.common.api.SgController;
import com.sg.common.api.SgResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/7/29
 */
@RestController
@RequestMapping("/user")
public class UserController extends SgController {



    @PostMapping("/save")
    public SgResponse save() throws RuntimeException {
        return success(null);
    }
}
