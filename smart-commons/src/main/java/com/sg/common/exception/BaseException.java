package com.sg.common.exception;

@SuppressWarnings("serial")
public abstract class BaseException extends RuntimeException {
    private static final long serialVersionUID = 3655050728585279326L;

    private ErrorCode code = ErrorCode.ERROR;

    public BaseException() {

    }

    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(ErrorCode code, String msg) {
        super(msg);
        this.code = code;
    }
    
    public BaseException(Throwable cause) {
        super(cause);
    }
    
    public BaseException(ErrorCode code, Throwable cause) {
        super(cause);
        this.code = code;
    }
    
    public BaseException(String msg, Throwable cause) {
    	super(msg, cause);
    }

    public BaseException(ErrorCode code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }

    public void setCode(ErrorCode code) {
        this.code = code;
    }

}
