package com.sg.common.exception;


/**
 * @author
 * @version
 */
@SuppressWarnings("serial")
public class BusinessException extends BaseException {
	private ErrorCode code;
	private String[] params;
	public BusinessException() {
	}

	public BusinessException(Throwable ex) {
		super(ex);
	}

	public BusinessException(String message) {
		super(message);
	}
	
	public BusinessException(ErrorCode code, String message) {
		super(message);
		this.code = code;
	}

	public BusinessException(String message, String[] params) {
		super(message);
		this.params = params;
	}

	public BusinessException(String message, Throwable ex) {
		super(message, ex);
	}

	protected ErrorCode getBaseCode() {
		return ErrorCode.BAD_REQUEST;
	}

	public String[] getParams() {
		return params;
	}

	@Override
	public ErrorCode getCode() {
		return code;
	}
}