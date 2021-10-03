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
import com.mcb.part2.entity.GroupEntity;
import com.mcb.part2.entity.SubjectEntity;
import com.mcb.part2.entity.SubjectTeacherEntrity;
import com.mcb.part2.entity.SubjectTeacherKey;
import com.mcb.part2.entity.TeacherEntity;
import com.mcb.part2.repository.StudentRepository;
import com.mcb.part2.repository.SubjectTeacherRepository;
import com.mcb.part2.repository.TeacherRepository;
import com.mcb.part2.service.TeachersService;
import com.mcb.part2.utils.RepositoryUtil;

@Service
public class TeacherServiceImpl implements TeachersService {

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private SubjectTeacherRepository subjectTeacherRepository;

	@Autowired
	private RepositoryUtil groupRepositoryUtil;
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public CommonBaseDTO addTeacher(String name) {
		TeacherEntity teacherEntity = new TeacherEntity();
		teacherEntity.setName(name);
		teacherRepository.save(teacherEntity);
		return new CommonBaseDTO(ResponseMessageConstant.CREATED_RESPONSE);
	}

	@Override
	public CommonBaseDTO findAllTeachers(int pageNo, int pageSize) {
		CommonBaseDTO commonBaseDTO = new CommonBaseDTO();
		Page<TeacherEntity> groupEntities = teacherRepository.findAll(PageRequest.of(pageNo, pageSize));
		commonBaseDTO.setData(groupEntities);
		return commonBaseDTO;
	}

	@Override
	public CommonBaseDTO assignSubject(Integer teacherId, Integer groupId, Integer subjectId) {
		Optional<TeacherEntity> teOptional = teacherRepository.findById(teacherId);
		if (!teOptional.isPresent()) {
			throw new NoRecordFoundException(ResponseMessageConstant.NO_RECOERD_FOUND, "Teacher", teacherId);
		}
		GroupEntity groupEntity = groupRepositoryUtil.findGroupById(groupId);
		SubjectEntity subjectEntity = groupRepositoryUtil.findSubjectsById(subjectId);

		SubjectTeacherKey subjectTeacherKey = new SubjectTeacherKey();
		subjectTeacherKey.setGroupId(groupId);
		subjectTeacherKey.setSubjectId(subjectId);
		subjectTeacherKey.setTeacherId(teacherId);

		SubjectTeacherEntrity subjectTeacherEntrity = new SubjectTeacherEntrity();
		subjectTeacherEntrity.setId(subjectTeacherKey);
		subjectTeacherEntrity.setGroup(groupEntity);
		subjectTeacherEntrity.setSubject(subjectEntity);
		subjectTeacherEntrity.setTeacher(teOptional.get());
		subjectTeacherRepository.save(subjectTeacherEntrity);
		return new CommonBaseDTO();
	}

	@Override
	public CommonBaseDTO findStudentCount(Integer teacherId) {
		
		CommonBaseDTO commonBaseDTO = new CommonBaseDTO();
		commonBaseDTO.setTotalRecords(studentRepository.studentCount(teacherId));
		return commonBaseDTO;
	}

}
