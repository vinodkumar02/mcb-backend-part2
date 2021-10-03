package com.mcb.part2.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="subject_teacher")
@Data
public class SubjectTeacherEntrity implements Serializable{

	private static final long serialVersionUID = -3457826012824079886L;
	
	@EmbeddedId
	private SubjectTeacherKey id;
	
	@MapsId("subjectId") 
    @ManyToOne
	private SubjectEntity subject;
	
	@MapsId("teacherId") 
    @ManyToOne
	private TeacherEntity teacher;
	
	@MapsId("groupId") 
    @ManyToOne
	private GroupEntity group;
}
