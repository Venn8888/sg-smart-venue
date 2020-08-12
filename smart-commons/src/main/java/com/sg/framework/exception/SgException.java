package com.sg.framework.exception;


import com.sg.api.IErrorCode;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/7/29
 */
public class SgException extends RuntimeException{

    private static final long serialVersionUID = -5885155226898287919L;

    /**
     * 错误码
     */
    private IErrorCode errorCode;

    public SgException(IErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    public SgException(String message) {
        super(message);
    }

    public SgException(Throwable cause) {
        super(cause);
    }

    public SgException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
