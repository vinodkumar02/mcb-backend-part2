package com.mcb.part2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mcb.part2.dto.CommonBaseDTO;
import com.mcb.part2.service.TeachersService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/teachers/v1/")
public class TeacherController {

	@Autowired
	TeachersService teachersService;

	@ApiOperation(value = "this API is use add new Teacher", response = CommonBaseDTO.class)
	@PostMapping("/teacher")
	public CommonBaseDTO addTeacher(@RequestParam(name = "name", required = true) String name) {
		return teachersService.addTeacher(name);
	}

	@ApiOperation(value = "this API is use to get Teachers", response = CommonBaseDTO.class)
	@GetMapping("/{pageNo}/{pageSize}")
	public CommonBaseDTO getTeacher(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
		return teachersService.findAllTeachers(pageNo, pageSize);
	}
	
	@ApiOperation(value = "this API is use to mapped tecaher with subject and group", response = CommonBaseDTO.class)
	@PostMapping("/teacher/{teacherId}/subject/{subjectId}/group/{groupId}")
	public CommonBaseDTO mappedTeacher(@PathVariable("teacherId") Integer teacherId,@PathVariable("subjectId") Integer subjectId,@PathVariable("groupId") Integer groupId)
	{
		return teachersService.assignSubject(teacherId, groupId, subjectId);
	}
	
	@ApiOperation(value = "this API is use to get  number of student  for particular teacher", response = CommonBaseDTO.class)
	@GetMapping("/teacher/{teacherId}/students")
	public CommonBaseDTO getStudents(@PathVariable("teacherId") Integer teacherId)
	{
		return teachersService.findStudentCount(teacherId);
	}
}
