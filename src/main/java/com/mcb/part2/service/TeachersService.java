package com.mcb.part2.service;

import com.mcb.part2.dto.CommonBaseDTO;

public interface TeachersService {

	CommonBaseDTO addTeacher(String name);

	CommonBaseDTO assignSubject(Integer teacherId, Integer groupId, Integer subjectId);

	CommonBaseDTO findAllTeachers(int pageNo, int pageSize);

	CommonBaseDTO findStudentCount(Integer teacherId);
}
