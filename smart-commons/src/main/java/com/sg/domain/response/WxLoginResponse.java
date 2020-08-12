package com.sg.domain.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/5/26
 */
@Data
public class WxLoginResponse implements Serializable {

    private static final long serialVersionUID = -6592152828615174973L;

    private String openid;

    private String session_key;

    private String unionid;

    private String errcode;

    private String errmsg;
}
