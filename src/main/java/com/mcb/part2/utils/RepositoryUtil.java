package com.mcb.part2.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mcb.part2.cexception.NoRecordFoundException;
import com.mcb.part2.constants.ResponseMessageConstant;
import com.mcb.part2.dto.CommonBaseDTO;
import com.mcb.part2.entity.GroupEntity;
import com.mcb.part2.entity.StudentEntity;
import com.mcb.part2.entity.SubjectEntity;
import com.mcb.part2.repository.GroupsRepository;
import com.mcb.part2.repository.StudentRepository;
import com.mcb.part2.repository.SubjectRepository;

@Repository
public class RepositoryUtil {
	
	@Autowired
	private GroupsRepository groupsRepository;

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	public GroupEntity findGroupById(Integer groupId) 
	{

		Optional<GroupEntity> groOptional = groupsRepository.findById(groupId);
		if (!groOptional.isPresent()) {
			throw new NoRecordFoundException(ResponseMessageConstant.NO_RECOERD_FOUND, "Group", groupId);
		}
		return groOptional.get();

	}
	
	public  StudentEntity findStudentsById(Integer studentId) {

		Optional<StudentEntity> groOptional = studentRepository.findById(studentId);
		if (!groOptional.isPresent()) {
			throw new NoRecordFoundException(ResponseMessageConstant.NO_RECOERD_FOUND, "Student", studentId);
		}
		return groOptional.get();

	}
	
	
	public SubjectEntity findSubjectsById(Integer subjectId) {

		Optional<SubjectEntity> groOptional = subjectRepository.findById(subjectId);
		if (!groOptional.isPresent()) {
			throw new NoRecordFoundException(ResponseMessageConstant.NO_RECOERD_FOUND, "Subject", subjectId);
		}
		return groOptional.get();

	}
}
