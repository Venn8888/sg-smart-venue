package com.sg.common.api;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/7/29
 */
public interface IErrorCode {

    /**
     * 错误编码 -1、失败 0、成功
     */
    long getCode();

    /**
     * 错误描述
     */
    String getMsg();

}
