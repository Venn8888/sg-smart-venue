package com.sg.framework.base;

/**
 * <b> 定义请求来源平台 </b>
 * <p>
 * 功能描述:
 * </p>
 *
 * @author 
 * @date 2017/9/9
 * @time 15:57
 * @Path com.yy.framework.base.SuperBootPlatForm
 */
public enum  BasePlatForm {
    PLATFORM_IOS("Ios", "苹果"),
    PLATFORM_ANDROID("Android", "安卓"),
    PLATFORM_WEB("Web", "PC端"),
    PLATFORM_AJAX("Ajax", "AJAX"),
    PLATFORM_OTHER("Other", "其他");

    private String code;

    private  String platform;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    BasePlatForm(String code, String platform){
        this.code = code;
        this.platform = platform;
    }

    
    @Override
    public String toString() {
        return "SuperBootPlatForm{" +
                "code=" + code +
                ", platform='" + platform + '\'' +
                '}';
    }
}
