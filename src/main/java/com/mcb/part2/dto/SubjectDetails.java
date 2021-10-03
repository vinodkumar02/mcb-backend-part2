package com.mcb.part2.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubjectDetails {

	private int subjectId;
	private String title;
	private Date date;
	private int marks;
	public SubjectDetails(int subjectId, String title, Date date, int marks) {
		super();
		this.subjectId = subjectId;
		this.title = title;
		this.date = date;
		this.marks = marks;
	}
	
	
	
}
