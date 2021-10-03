package com.mcb.part2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mcb.part2.dto.CommonBaseDTO;
import com.mcb.part2.dto.StudentDto;
import com.mcb.part2.service.StudentsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/students/v1/")
public class StudentsController {

	@Autowired
	StudentsService studentsService;

	@ApiOperation(value = "this API is use add new student", response = CommonBaseDTO.class)
	@PostMapping("/student")
	public CommonBaseDTO addstudent(@Valid @RequestBody StudentDto studentDto) {
		return studentsService.addStudent(studentDto);
	}

	@ApiOperation(value = "this API is use update student title", response = CommonBaseDTO.class)
	@PutMapping("/student/{studentId}")
	public CommonBaseDTO updatestudent(@PathVariable("studentId") Integer studentId,
			@Valid @RequestBody StudentDto studentDto) {
		return studentsService.updateStudent(studentId, studentDto);
	}

	@ApiOperation(value = "this API is use delete exsiting student", response = CommonBaseDTO.class)
	@DeleteMapping("/student/{studentId}")
	public CommonBaseDTO deletestudent(@PathVariable("studentId") Integer studentId) {
		return studentsService.deleteStudent(studentId);
	}

	@ApiOperation(value = "this API is use to get student by id", response = CommonBaseDTO.class)
	@GetMapping("/student/{studentId}")
	public CommonBaseDTO getstudent(@PathVariable("studentId") Integer studentId) {
		return studentsService.findStudentsById(studentId);
	}

	@ApiOperation(value = "this API is use to get students", response = CommonBaseDTO.class)
	@GetMapping("/{pageNo}/{pageSize}")
	public CommonBaseDTO getstudent(@PathVariable("pageNo") Integer pageNo,@PathVariable("pageSize") Integer pageSize) {
		return studentsService.findALLStudents(pageNo,pageSize);
	}
}
