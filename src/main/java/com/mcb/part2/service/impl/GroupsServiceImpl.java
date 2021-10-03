package com.mcb.part2.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcb.part2.cexception.NoRecordFoundException;
import com.mcb.part2.constants.ResponseMessageConstant;
import com.mcb.part2.dto.CommonBaseDTO;
import com.mcb.part2.entity.GroupEntity;
import com.mcb.part2.repository.GroupsRepository;
import com.mcb.part2.service.GroupsService;
import com.mcb.part2.utils.RepositoryUtil;

@Service
public class GroupsServiceImpl implements GroupsService {

	@Autowired
	private GroupsRepository groupsRepository;
	
	@Autowired
	private RepositoryUtil groupRepositoryUtil;

	@Override
	public CommonBaseDTO addGroup(String name) {
		GroupEntity groupEntity = new GroupEntity();
		groupEntity.setName(name);
		groupsRepository.save(groupEntity);
		return new CommonBaseDTO(ResponseMessageConstant.CREATED_RESPONSE);
	}

	@Override
	public CommonBaseDTO updateGroup(String name, Integer groupId) {

		Optional<GroupEntity> groOptional = groupsRepository.findById(groupId);
		if (!groOptional.isPresent()) {
			throw new NoRecordFoundException(ResponseMessageConstant.NO_RECOERD_FOUND, "Group", groupId);
		}
		groOptional.get().setName(name);
		groupsRepository.save(groOptional.get());
		return new CommonBaseDTO();
	}

	@Override
	public CommonBaseDTO deleteGroup(Integer groupId) {
		Optional<GroupEntity> groOptional = groupsRepository.findById(groupId);
		if (!groOptional.isPresent()) {
			throw new NoRecordFoundException(ResponseMessageConstant.NO_RECOERD_FOUND, "Group", groupId);
		}
		groupsRepository.delete(groOptional.get());
		return new CommonBaseDTO(ResponseMessageConstant.DELETE_RESPONSE);
	}

	@Override
	public CommonBaseDTO findALLGroup() {
		CommonBaseDTO commonBaseDTO = new CommonBaseDTO();
		List<GroupEntity> groupEntities = groupsRepository.findAll();
		commonBaseDTO.setTotalRecords(Long.valueOf(groupEntities.size()));
		commonBaseDTO.setData(groupEntities);
		return commonBaseDTO;
	}

	@Override
	public CommonBaseDTO findGroupById(Integer groupId) {

		return new CommonBaseDTO(groupRepositoryUtil.findGroupById(groupId));

	}

}
