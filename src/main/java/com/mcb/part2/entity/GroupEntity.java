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
@Table(name = "groups")
@Data
@NoArgsConstructor
public class GroupEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 423968905474386095L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="group_id")
	private Integer groupId;
	
	@Column(name ="name" ,unique = true)
	private String name;

	public GroupEntity(Integer groupId) {
		super();
		this.groupId = groupId;
	}
	
	
	
}
