package com.mcb.part2.service;

import com.mcb.part2.dto.CommonBaseDTO;
import com.mcb.part2.dto.StudentDto;

public interface StudentsService {

	CommonBaseDTO addStudent(StudentDto studentDto);
	
	CommonBaseDTO updateStudent(Integer studentId, StudentDto studentDto);
	
	CommonBaseDTO deleteStudent(Integer studentId);
	
	CommonBaseDTO findALLStudents(int pageNo, int pageSize);
	CommonBaseDTO findStudentsById(Integer studentId);
}
