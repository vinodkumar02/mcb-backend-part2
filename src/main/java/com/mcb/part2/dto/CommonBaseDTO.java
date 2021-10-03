package com.mcb.part2.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mcb.part2.constants.ResponseMessageConstant;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonBaseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	Integer statusCode;

	String message;

	Object data;

	Long totalRecords;


	public CommonBaseDTO() {

		statusCode = ResponseMessageConstant.SUCCESS_RESPONSE.getErrorCode();
		message = ResponseMessageConstant.SUCCESS_RESPONSE.getMessage();
	}

	public CommonBaseDTO(Integer statusCode , String message) {

		this.statusCode = statusCode;
		this.message = message;
	}
	
	public CommonBaseDTO(ResponseMessageConstant responseMessageConstant) {

		statusCode = responseMessageConstant.getErrorCode();
		message = responseMessageConstant.getMessage();
	}
	
	public CommonBaseDTO(Object data) {

		statusCode = ResponseMessageConstant.SUCCESS_RESPONSE.getErrorCode();
		message = ResponseMessageConstant.SUCCESS_RESPONSE.getMessage();
		this.data=data;
	}
}
