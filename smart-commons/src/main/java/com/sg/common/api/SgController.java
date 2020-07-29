package com.sg.common.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/7/29
 */
public class SgController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 请求成功
     *
     * @param data 数据内容
     * @param <T>  对象泛型
     * @return ignore
     */
    protected <T> SgResponse<T> success(T data) {
        return SgResponse.ok(data);
    }

    /**
     * 请求失败
     *
     * @param msg 提示内容
     * @return ignore
     */
    protected <T> SgResponse<T> failed(String msg) {
        return SgResponse.failed(msg);
    }

    /**
     * 请求失败
     *
     * @param errorCode 请求错误码
     * @return ignore
     */
    protected <T> SgResponse<T> failed(IErrorCode errorCode) {
        return SgResponse.failed(errorCode);
    }
}
