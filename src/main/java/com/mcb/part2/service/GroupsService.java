package com.mcb.part2.service;

import com.mcb.part2.dto.CommonBaseDTO;

public interface GroupsService {

	CommonBaseDTO addGroup(String name);
	CommonBaseDTO updateGroup(String name, Integer groupId);
	CommonBaseDTO deleteGroup(Integer groupId);
	CommonBaseDTO findALLGroup();
	CommonBaseDTO findGroupById(Integer groupId);
}
