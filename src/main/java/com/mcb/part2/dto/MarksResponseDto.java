package com.mcb.part2.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MarksResponseDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6357193990899852874L;
	private Integer studentId;
	private String firstName;
	private String lastName;

	private List<SubjectDetails> subjectDetails;

	public MarksResponseDto(Integer studentId, String firstName, String lastName) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	

}
