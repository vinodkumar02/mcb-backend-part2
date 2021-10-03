package com.mcb.part2.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subjects")
@Data
@NoArgsConstructor
public class SubjectEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 423968905474386095L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "subject_id")
	private Integer subjectId;

	@Column(name="title",unique = true)
	private String title;

	public SubjectEntity(Integer subjectId) {
		this.subjectId = subjectId;
	}
}
