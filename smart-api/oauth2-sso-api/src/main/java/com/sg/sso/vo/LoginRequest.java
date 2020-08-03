package com.sg.sso.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/8/3
 */
@Data
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = 2684296535228006561L;

    private String username;

    private String password;
}
