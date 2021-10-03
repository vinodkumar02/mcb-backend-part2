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
import org.springframework.web.bind.annotation.RestController;

import com.mcb.part2.dto.CommonBaseDTO;
import com.mcb.part2.dto.MarksDto;
import com.mcb.part2.service.MarksService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/marks/v1/")
public class MarksController {

	@Autowired
	MarksService marksService;

	@ApiOperation(value = "this API is use add new marks", response = CommonBaseDTO.class)
	@PostMapping("/mark")
	public CommonBaseDTO addMark(@Valid @RequestBody MarksDto markDto) {
		return marksService.addMarks(markDto);
	}

	@ApiOperation(value = "this API is use update marks ", response = CommonBaseDTO.class)
	@PutMapping("/mark/{markId}")
	public CommonBaseDTO updateMark(@PathVariable("markId") Integer markId,
			@Valid @RequestBody MarksDto markDto) {
		return marksService.updateMarks(markId, markDto);
	}

	@ApiOperation(value = "this API is use delete exsiting mark", response = CommonBaseDTO.class)
	@DeleteMapping("/mark/{markId}")
	public CommonBaseDTO deleteMark(@PathVariable("markId") Integer markId) {
		return marksService.deleteMarks(markId);
	}

	@ApiOperation(value = "this API is use to get mark by id", response = CommonBaseDTO.class)
	@GetMapping("/mark/{markId}")
	public CommonBaseDTO getMark(@PathVariable("markId") Integer markId) {
		return marksService.findMarksById(markId);
	}

	@ApiOperation(value = "this API is use to get marks", response = CommonBaseDTO.class)
	@GetMapping("/{pageNo}/{pageSize}")
	public CommonBaseDTO getMark(@PathVariable("pageNo") Integer pageNo,@PathVariable("pageSize") Integer pageSize) {
		return marksService.findALLMarks(pageNo, pageSize);
	}
	
	@ApiOperation(value = "this API is use to get student marks ", response = CommonBaseDTO.class)
	@GetMapping("/mark/student/{studentId}")
	public CommonBaseDTO getMarksForStudent(@PathVariable("studentId") Integer studentId) {
		return marksService.findStudentMarks(studentId);
	}
	
	@ApiOperation(value = "this API is use to get student marks ", response = CommonBaseDTO.class)
	@GetMapping("/mark/student/{studentId}/subject/{subjectId}")
	public CommonBaseDTO getMarksForStudent(@PathVariable("subjectId") Integer subjectId,@PathVariable("studentId") Integer studentId) {
		return marksService.findStudentMarks(subjectId);
	}
}
