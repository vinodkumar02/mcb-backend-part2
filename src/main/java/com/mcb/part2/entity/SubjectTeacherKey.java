package com.mcb.part2.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class SubjectTeacherKey implements Serializable{

	private static final long serialVersionUID = -3457826012824079886L;
	
	private Integer subjectId;
	private Integer teacherId;
	private Integer groupId;
}
