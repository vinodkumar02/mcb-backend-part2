package com.mcb.part2.constants;

import java.util.Locale;
import java.util.Optional;

import org.springframework.context.MessageSource;

public enum ResponseMessageConstant {

	SUCCESS_RESPONSE(200, 200), CREATED_RESPONSE(201, 201), DELETE_RESPONSE(204, 204), ACCESS_DENIED(401, 401),
	DATA_VIOLATION(409, 409), INTERNAL_SERVER_ERROR(500, 500),

	MANDTORY_REQUEST_PARM(103, 103), INVALID_REQUEST_PARM(104, 104), NO_RECOERD_FOUND(404, 105);

	private int errorCode;
	private int messageCode;

	private ResponseMessageConstant(int errorCode, int messageCode) {
		this.errorCode = errorCode;
		this.messageCode = messageCode;
	}

	private MessageSource messageSource;

	public int getErrorCode() {
		return errorCode;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String getMessage() {
		return messageSource.getMessage(String.valueOf(messageCode), new Object[] {}, Locale.ENGLISH);
	}

	public String getMessage(Object[] arg1) {

		return messageSource.getMessage(String.valueOf(messageCode), arg1, Locale.ENGLISH);
	}

	public String getMessage(String value) {
		Object[] ob = new Object[1];
		ob[0] = value;
		return messageSource.getMessage(String.valueOf(messageCode), ob, Locale.ENGLISH);
	}

	public static Optional<ResponseMessageConstant> getResponseMessageContext(int code) {
		for (ResponseMessageConstant responseMessageConstant : ResponseMessageConstant.values()) {
			if (responseMessageConstant.messageCode == code) {
				return Optional.of(responseMessageConstant);
			}
		}
		return Optional.of(null);

	}
}
