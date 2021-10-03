package com.mcb.part2.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
public class StudentEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 423968905474386095L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="student_id")
	private Integer studentId;
	
	@Column(name ="first_name")
	private String firstName;
	
	@Column(name ="last_name")
	private String lastName;
	
	@ManyToOne
	@JoinColumn(name = "group_id", referencedColumnName = "group_id", foreignKey = @ForeignKey(name = "students_groups_fk"))
	@JsonIgnore
	private GroupEntity group;
	
	public StudentEntity(Integer studentId)
	{
		this.studentId=studentId;
	}

}
