package com.mcb.part2.config;

import java.io.IOException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.mcb.part2.cexception.InvalidRequestException;
import com.mcb.part2.cexception.NoRecordFoundException;
import com.mcb.part2.constants.ResponseMessageConstant;
import com.mcb.part2.dto.CommonBaseDTO;




@ControllerAdvice
public class ExceptionControllerAdvice {
	
	
	@ExceptionHandler(value = {InvalidRequestException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public CommonBaseDTO exception(HttpServletRequest request, com.mcb.part2.cexception.InvalidRequestException ex) {
	
		Object [] ob= {};
		if(ex.getObjects()!=null)
		{
			ob=ex.getObjects();
		}
	
		return new CommonBaseDTO(ex.getResponseMessageConstant().getErrorCode(),
				ex.getResponseMessageConstant().getMessage(ob));
	}
	
	
	@ExceptionHandler(value = {NoRecordFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public CommonBaseDTO exception(HttpServletRequest request, NoRecordFoundException ex) {
	
		Object [] ob= {};
		if(ex.getObjects()!=null)
		{
			ob=ex.getObjects();
		}
	
		return new CommonBaseDTO(ex.getResponseMessageConstant().getErrorCode(),
				ex.getResponseMessageConstant().getMessage(ob));
	}
	
	@ExceptionHandler(value = { DataIntegrityViolationException.class })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public CommonBaseDTO exception(HttpServletRequest request, DataIntegrityViolationException ex) 
	{
		
		return new CommonBaseDTO(ResponseMessageConstant.DATA_VIOLATION.getErrorCode(),
				ResponseMessageConstant.DATA_VIOLATION.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public CommonBaseDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, Locale locale,
			HttpServletRequest request) {

		BindingResult result = ex.getBindingResult();
		ex.printStackTrace();
		return processFieldErrors(new HashSet<FieldError>(result.getFieldErrors()), locale);

	}


	
	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public CommonBaseDTO handle(javax.validation.ConstraintViolationException exception, Locale locale) {

		exception.printStackTrace();	
		return processFieldErrors(exception.getConstraintViolations(), locale);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public CommonBaseDTO handle(HttpMessageNotReadableException exception, Locale locale) {

		CommonBaseDTO commonResponse = new CommonBaseDTO();
		if(exception.getCause() instanceof  InvalidFormatException)
		{
			InvalidFormatException ex=(InvalidFormatException)exception.getCause();
			String field=ex.getPathReference().split("\"")[1];
			commonResponse.setStatusCode(ResponseMessageConstant.INVALID_REQUEST_PARM.getErrorCode());
			commonResponse.setMessage(ResponseMessageConstant.INVALID_REQUEST_PARM.getMessage(new Object[] { field }));
			return commonResponse;
		}
		throw exception;
	}
	private CommonBaseDTO processFieldErrors(Set<ConstraintViolation<?>> fieldErrors, Locale locale) {
		CommonBaseDTO commonResponse = new CommonBaseDTO();
		String defaultMessage = "103";
		String field = "";
		for (ConstraintViolation<?> fieldError : fieldErrors) {

			defaultMessage = fieldError.getMessage();
			field = (String) fieldError.getPropertyPath().toString();

		}
		if (defaultMessage.equalsIgnoreCase("103")) {
			commonResponse.setStatusCode(ResponseMessageConstant.MANDTORY_REQUEST_PARM.getErrorCode());
			commonResponse.setMessage(ResponseMessageConstant.MANDTORY_REQUEST_PARM.getMessage(new Object[] { field }));
			return commonResponse;
		}

		commonResponse.setStatusCode(ResponseMessageConstant.INVALID_REQUEST_PARM.getErrorCode());
		commonResponse.setMessage(ResponseMessageConstant.INVALID_REQUEST_PARM.getMessage(new Object[] { field }));
		return commonResponse;

	}

	private CommonBaseDTO processFieldErrors(HashSet<FieldError> fieldErrors, Locale locale) {
		CommonBaseDTO commonResponse = new CommonBaseDTO();
		String defaultMessage = "103";
		String field = "";

		for (FieldError fieldError : fieldErrors) {
			defaultMessage = fieldError.getDefaultMessage();
			field = fieldError.getField();

		}
		Optional<ResponseMessageConstant> optional=ResponseMessageConstant.getResponseMessageContext(Integer.parseInt(defaultMessage));
		if(optional.isPresent())
		{
			
			commonResponse.setStatusCode(optional.get().getErrorCode());
			commonResponse.setMessage(optional.get().getMessage(new Object[] { field }));
			return commonResponse;
		}
		
		commonResponse.setStatusCode(ResponseMessageConstant.INVALID_REQUEST_PARM.getErrorCode());
		commonResponse.setMessage(ResponseMessageConstant.INVALID_REQUEST_PARM.getMessage(new Object[] { field }));
		return commonResponse;
	}

	@ExceptionHandler(value = { IOException.class, Exception.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public CommonBaseDTO exception(HttpServletRequest request, Exception ex) {
		
		ex.printStackTrace();
		return new CommonBaseDTO(ResponseMessageConstant.INTERNAL_SERVER_ERROR);
	}
}