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
import com.mcb.part2.entity.SubjectEntity;
import com.mcb.part2.repository.SubjectRepository;
import com.mcb.part2.service.SubjectsService;
import com.mcb.part2.utils.RepositoryUtil;

@Service
public class SubjectsServiceImpl implements SubjectsService {

	@Autowired
	private SubjectRepository subjectsRepository;
	
	@Autowired
	private RepositoryUtil repositoryUtil;

	@Override
	public CommonBaseDTO addSubject(String title) {
		SubjectEntity SubjectEntity = new SubjectEntity();
		SubjectEntity.setTitle(title);
		subjectsRepository.save(SubjectEntity);
		return new CommonBaseDTO(ResponseMessageConstant.CREATED_RESPONSE);
	}

	@Override
	public CommonBaseDTO updateSubject(String title, Integer subjectId) {

		Optional<SubjectEntity> groOptional = subjectsRepository.findById(subjectId);
		if (!groOptional.isPresent()) {
			throw new NoRecordFoundException(ResponseMessageConstant.NO_RECOERD_FOUND, "Subject", subjectId);
		}
		groOptional.get().setTitle(title);
		subjectsRepository.save(groOptional.get());
		return new CommonBaseDTO();
	}

	@Override
	public CommonBaseDTO deleteSubject(Integer subjectId) {
		Optional<SubjectEntity> groOptional = subjectsRepository.findById(subjectId);
		if (!groOptional.isPresent()) {
			throw new NoRecordFoundException(ResponseMessageConstant.NO_RECOERD_FOUND, "Subject", subjectId);
		}
		subjectsRepository.delete(groOptional.get());
		return new CommonBaseDTO(ResponseMessageConstant.DELETE_RESPONSE);
	}

	@Override
	public CommonBaseDTO findALLSubjects(int pageNo, int pageSize) {
		CommonBaseDTO commonBaseDTO = new CommonBaseDTO();
		Page<SubjectEntity> SubjectEntities = subjectsRepository.findAll(PageRequest.of(pageNo, pageSize));
		commonBaseDTO.setData(SubjectEntities);
		return commonBaseDTO;
	}

	@Override
	public CommonBaseDTO findSubjectsById(Integer subjectId) {

		return new CommonBaseDTO(repositoryUtil.findSubjectsById(subjectId));

	}

}
