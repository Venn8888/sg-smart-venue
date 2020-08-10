package com.sg.common.exception;

/**
 * 提示消息异常
 *
 * @author admin
 */
public class BaseAlertException extends BaseException {
    private static final long serialVersionUID = 4908906410210213271L;

    public BaseAlertException() {
    }

    public BaseAlertException(String msg) {
        super(msg);
    }

    public BaseAlertException(ErrorCode code, String msg) {
        super(code, msg);
    }

    public BaseAlertException(ErrorCode code, String msg, Throwable cause) {
        super(code, msg, cause);
    }
}
