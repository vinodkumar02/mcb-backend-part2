package com.mcb.part2.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "teachers")
@Data
public class TeacherEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 423968905474386095L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="teacher_id")
	private Integer teacherId;

	private String name;
	
}
