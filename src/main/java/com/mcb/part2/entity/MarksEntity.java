package com.mcb.part2.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "markes")
@Data
public class MarksEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 423968905474386095L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mark_id")
	private Integer markId;

	@Column(name = "mark")
	private Integer mark;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
	@Column(name = "date")
	private Date date;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "student_id", referencedColumnName = "student_id", foreignKey = @ForeignKey(name = "markes_students_fk"))
	private StudentEntity student;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "subject_id", referencedColumnName = "subject_id", foreignKey = @ForeignKey(name = "markes_subjects_fk"))
	private SubjectEntity subject;

	public MarksEntity() {
		date = new Date();
	}
}
