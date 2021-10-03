package com.mcb.part2.controller;

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
import com.mcb.part2.service.GroupsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/groups/v1/")
public class GroupsController {

	@Autowired
	GroupsService groupsService;

	@ApiOperation(value = "this API is use add new group", response = CommonBaseDTO.class)
	@PostMapping("/group")
	public CommonBaseDTO addGroup(
			@ApiParam(value = "name of the group") @RequestParam(name = "name", required = true) String name) {
		return groupsService.addGroup(name);
	}

	@ApiOperation(value = "this API is use update group name", response = CommonBaseDTO.class)
	@PutMapping("/{groupId}/group")
	public CommonBaseDTO updateGroup(@PathVariable("groupId") Integer groupId,
			@ApiParam(value = "name of the group") @RequestParam(name = "name", required = true) String name) {
		return groupsService.updateGroup(name, groupId);
	}

	@ApiOperation(value = "this API is use delete exsiting group", response = CommonBaseDTO.class)
	@DeleteMapping("/{groupId}")
	public CommonBaseDTO deleteGroup(@PathVariable("groupId") Integer groupId) {
		return groupsService.deleteGroup(groupId);
	}

	@ApiOperation(value = "this API is use to get group by id", response = CommonBaseDTO.class)
	@GetMapping("/{groupId}")
	public CommonBaseDTO getGroup(@PathVariable("groupId") Integer groupId) {
		return groupsService.findGroupById(groupId);
	}

	@ApiOperation(value = "this API is use to get all groups", response = CommonBaseDTO.class)
	@GetMapping
	public CommonBaseDTO getGroup() {
		return groupsService.findALLGroup();
	}
}
