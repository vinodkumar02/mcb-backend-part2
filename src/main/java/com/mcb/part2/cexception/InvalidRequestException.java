package com.mcb.part2.cexception;

import java.util.List;

import com.mcb.part2.constants.ResponseMessageConstant;

public class InvalidRequestException extends RuntimeException {

	/**
	 * 
	 */
	private Object value;
	private static final long serialVersionUID = 3598859628828600953L;
	private ResponseMessageConstant responseMessageConstant;
	private Object[] objects;

	public InvalidRequestException(ResponseMessageConstant responseMessageConstant, Object... objects) {
		super(responseMessageConstant.getMessage(objects));
		this.responseMessageConstant = responseMessageConstant;
		this.objects = objects;

	}

	public InvalidRequestException(ResponseMessageConstant responseMessageConstant, Throwable cause) {
		super(responseMessageConstant.getMessage(), cause);
		this.responseMessageConstant = responseMessageConstant;

	}

	public Object getValue() {
		return value;
	}

	public ResponseMessageConstant getResponseMessageConstant() {
		return responseMessageConstant;
	}

	public Object[] getObjects() {
		return objects;
	}

}
