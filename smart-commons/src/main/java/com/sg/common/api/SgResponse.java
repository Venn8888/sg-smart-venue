package com.sg.common.api;

import com.sg.common.exception.SgException;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/7/29
 */
@Data
@Accessors(chain = true)
public class SgResponse<T> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 业务错误码
     */
    private long code;
    /**
     * 结果集
     */
    private T data;
    /**
     * 描述
     */
    private String msg;

    public SgResponse() {
        // to do nothing
    }

    public SgResponse(IErrorCode errorCode) {
        errorCode = Optional.ofNullable(errorCode).orElse(SgErrorCode.FAILED);
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public static <T> SgResponse<T> ok(T data) {
        SgErrorCode aec = SgErrorCode.SUCCESS;
        if (data instanceof Boolean && Boolean.FALSE.equals(data)) {
            aec = SgErrorCode.FAILED;
        }
        return restResult(data, aec);
    }

    public static <T> SgResponse<T> failed(String msg) {
        return restResult(null, SgErrorCode.FAILED.getCode(), msg);
    }

    public static <T> SgResponse<T> failed(IErrorCode errorCode) {
        return restResult(null, errorCode);
    }

    public static <T> SgResponse<T> restResult(T data, IErrorCode errorCode) {
        return restResult(data, errorCode.getCode(), errorCode.getMsg());
    }

    private static <T> SgResponse<T> restResult(T data, long code, String msg) {
        SgResponse<T> apiResult = new SgResponse<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public boolean ok() {
        return SgErrorCode.SUCCESS.getCode() == code;
    }

    /**
     * 服务间调用非业务正常，异常直接释放
     */
    public T serviceData() {
        if (!ok()) {
            throw new SgException(this.msg);
        }
        return data;
    }
}
