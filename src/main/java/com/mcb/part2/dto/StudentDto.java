package com.mcb.part2.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class StudentDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 423968905474386095L;

	@NotEmpty(message = "103")
	private String firstName;

	@NotEmpty(message = "103")
	private String lastName;
	
	@NotNull(message = "103")
	private Integer groupId;

}
