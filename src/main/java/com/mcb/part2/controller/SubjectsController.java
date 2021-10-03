package com.mcb.part2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mcb.part2.dto.CommonBaseDTO;
import com.mcb.part2.service.SubjectsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/subjects/v1/")
public class SubjectsController {

	@Autowired
	SubjectsService subjectsService;

	@ApiOperation(value = "this API is use add new subject", response = CommonBaseDTO.class)
	@PostMapping("/subject")
	public CommonBaseDTO addSubject(@Valid @RequestParam(name = "title", required = true) String title) {
		return subjectsService.addSubject(title);
	}

	@ApiOperation(value = "this API is use update subject title", response = CommonBaseDTO.class)
	@PutMapping("/subject/{subjectId}")
	public CommonBaseDTO updateSubject(@PathVariable("subjectId") Integer subjectId,
			@ApiParam(value = "name of the Subject") @RequestParam(name = "title", required = true) String title) {
		return subjectsService.updateSubject(title, subjectId);
	}

	@ApiOperation(value = "this API is use delete exsiting Subject", response = CommonBaseDTO.class)
	@DeleteMapping("/subject/{subjectId}")
	public CommonBaseDTO deleteSubject(@PathVariable("subjectId") Integer subjectId) {
		return subjectsService.deleteSubject(subjectId);
	}

	@ApiOperation(value = "this API is use to get subject by id", response = CommonBaseDTO.class)
	@GetMapping("/subject/{subjectId}")
	public CommonBaseDTO getSubject(@PathVariable("subjectId") Integer subjectId) {
		return subjectsService.findSubjectsById(subjectId);
	}

	@ApiOperation(value = "this API is use to get subjects", response = CommonBaseDTO.class)
	@GetMapping("/{pageNo}/{pageSize}")
	public CommonBaseDTO getSubject(@PathVariable("pageNo") Integer pageNo,@PathVariable("pageSize") Integer pageSize) {
		return subjectsService.findALLSubjects(pageNo, pageSize);
	}
}
