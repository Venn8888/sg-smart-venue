package com.sg.common.domain.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/5/26
 */
@Data
public class WxMobileLoginResponse implements Serializable {

    private static final long serialVersionUID = 9176819353328797877L;

    private String phoneNumber;

    private String purePhoneNumber;

    private String countryCode;
}
