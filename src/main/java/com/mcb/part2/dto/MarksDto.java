package com.mcb.part2.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class MarksDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 423968905474386095L;

	@NotNull(message = "103")
	private Integer mark;
	
	@NotNull(message = "103")
	private Integer studentId;

	@NotNull(message = "103")
	private Integer subjectId;
}
