package com.mcb.part2.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mcb.part2.cexception.NoRecordFoundException;
import com.mcb.part2.constants.ResponseMessageConstant;
import com.mcb.part2.dto.CommonBaseDTO;
import com.mcb.part2.dto.StudentDto;
import com.mcb.part2.entity.GroupEntity;
import com.mcb.part2.entity.StudentEntity;
import com.mcb.part2.repository.StudentRepository;
import com.mcb.part2.service.StudentsService;
import com.mcb.part2.utils.CommonUtil;
import com.mcb.part2.utils.RepositoryUtil;

@Service
public class StudentsServiceImpl implements StudentsService {

	@Autowired
	private StudentRepository studentsRepository;

	@Autowired
	RepositoryUtil repositoryUtil;

	@Autowired
	CommonUtil commonUtil;

	@Override
	public CommonBaseDTO addStudent(StudentDto studentDto) {

		GroupEntity groupEntity = repositoryUtil.findGroupById(studentDto.getGroupId());
		StudentEntity studentEntity = commonUtil.modalMap(studentDto, StudentEntity.class);
		studentEntity.setGroup(groupEntity);
		studentsRepository.save(studentEntity);
		return new CommonBaseDTO(ResponseMessageConstant.CREATED_RESPONSE);
	}

	@Override
	public CommonBaseDTO updateStudent(Integer StudentId, StudentDto studentDto) {

		Optional<StudentEntity> stuOptional = studentsRepository.findById(StudentId);
		if (!stuOptional.isPresent()) {
			throw new NoRecordFoundException(ResponseMessageConstant.NO_RECOERD_FOUND, "Student", StudentId);
		}
		GroupEntity groupEntity = repositoryUtil.findGroupById(studentDto.getGroupId());
		commonUtil.modalMapCopy(studentDto, stuOptional.get());
		stuOptional.get().setGroup(groupEntity);
		studentsRepository.save(stuOptional.get());

		return new CommonBaseDTO();
	}

	@Override
	public CommonBaseDTO deleteStudent(Integer StudentId) {
		Optional<StudentEntity> stOptional = studentsRepository.findById(StudentId);
		if (!stOptional.isPresent()) {
			throw new NoRecordFoundException(ResponseMessageConstant.NO_RECOERD_FOUND, "Student", StudentId);
		}
		studentsRepository.delete(stOptional.get());
		return new CommonBaseDTO(ResponseMessageConstant.DELETE_RESPONSE);
	}

	@Override
	public CommonBaseDTO findALLStudents(int pageNo, int pageSize) {
		CommonBaseDTO commonBaseDTO = new CommonBaseDTO();
		Page<StudentEntity> StudentEntities = studentsRepository.findAll(PageRequest.of(pageNo, pageSize));
		commonBaseDTO.setData(StudentEntities);
		return commonBaseDTO;
	}

	@Override
	public CommonBaseDTO findStudentsById(Integer studentId) {

		return new CommonBaseDTO(repositoryUtil.findStudentsById(studentId));

	}

}
