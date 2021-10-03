package com.mcb.part2.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mcb.part2.cexception.NoRecordFoundException;
import com.mcb.part2.constants.ResponseMessageConstant;
import com.mcb.part2.dto.CommonBaseDTO;
import com.mcb.part2.dto.MarksDto;
import com.mcb.part2.dto.MarksResponseDto;
import com.mcb.part2.dto.SubjectDetails;
import com.mcb.part2.entity.MarksEntity;
import com.mcb.part2.entity.StudentEntity;
import com.mcb.part2.entity.SubjectEntity;
import com.mcb.part2.repository.MarksRepository;
import com.mcb.part2.service.MarksService;
import com.mcb.part2.utils.CommonUtil;
import com.mcb.part2.utils.RepositoryUtil;

@Service
public class MarksServiceImpl implements MarksService {

	@Autowired
	private MarksRepository marksRepository;

	@Autowired
	RepositoryUtil repositoryUtil;

	@Autowired
	CommonUtil commonUtil;

	@Override
	public CommonBaseDTO addMarks(MarksDto marksDto) {

		StudentEntity studentEntity = repositoryUtil.findStudentsById(marksDto.getStudentId());
		SubjectEntity subjectEntity = repositoryUtil.findSubjectsById(marksDto.getSubjectId());

		MarksEntity marksEntity = new MarksEntity();
		marksEntity.setMark(marksDto.getMark());
		marksEntity.setStudent(studentEntity);
		marksEntity.setSubject(subjectEntity);

		marksRepository.save(marksEntity);
		return new CommonBaseDTO(ResponseMessageConstant.CREATED_RESPONSE);
	}

	@Override
	public CommonBaseDTO updateMarks(Integer marksId, MarksDto marksDto) {

		Optional<MarksEntity> marOptional = marksRepository.findById(marksId);
		if (!marOptional.isPresent()) {
			throw new NoRecordFoundException(ResponseMessageConstant.NO_RECOERD_FOUND, "Marks", marksId);
		}
		StudentEntity studentEntity = repositoryUtil.findStudentsById(marksDto.getStudentId());
		SubjectEntity subjectEntity = repositoryUtil.findSubjectsById(marksDto.getSubjectId());

		MarksEntity marksEntity = marOptional.get();
		marksEntity.setMark(marksDto.getMark());
		marksEntity.setStudent(studentEntity);
		marksEntity.setSubject(subjectEntity);

		marksRepository.save(marksEntity);

		return new CommonBaseDTO();
	}

	@Override
	public CommonBaseDTO deleteMarks(Integer marksId) {
		Optional<MarksEntity> stOptional = marksRepository.findById(marksId);
		if (!stOptional.isPresent()) {
			throw new NoRecordFoundException(ResponseMessageConstant.NO_RECOERD_FOUND, "Marks", marksId);
		}
		marksRepository.delete(stOptional.get());
		return new CommonBaseDTO(ResponseMessageConstant.DELETE_RESPONSE);
	}

	@Override
	public CommonBaseDTO findALLMarks(int pageNo, int pageSize) {
		CommonBaseDTO commonBaseDTO = new CommonBaseDTO();
		Page<MarksEntity> marksEntities = marksRepository.findAll(PageRequest.of(pageNo, pageSize));
		commonBaseDTO.setData(marksEntities);
		return commonBaseDTO;
	}

	@Override
	public CommonBaseDTO findMarksById(Integer marksId) {

		Optional<MarksEntity> marOptional = marksRepository.findById(marksId);
		if (!marOptional.isPresent()) {
			throw new NoRecordFoundException(ResponseMessageConstant.NO_RECOERD_FOUND, "Marks", marksId);
		}
		return new CommonBaseDTO(marOptional.get());

	}

	@Override
	public CommonBaseDTO findStudentMarks(Integer studentId, Integer subjectId) {
		CommonBaseDTO commonBaseDTO = new CommonBaseDTO();
		List<MarksEntity> MarksEntities = marksRepository.getStudentMarkes(new StudentEntity(studentId),
				new SubjectEntity(subjectId));
		commonBaseDTO.setTotalRecords(Long.valueOf(MarksEntities.size()));
		commonBaseDTO.setData(MarksEntities);
		return commonBaseDTO;
	}

	@Override
	public CommonBaseDTO findStudentMarks(Integer studentId) {
		CommonBaseDTO commonBaseDTO = new CommonBaseDTO();
		List<MarksEntity> marksEntities = marksRepository.getStudentMarkes(new StudentEntity(studentId));
		commonBaseDTO.setTotalRecords(Long.valueOf(marksEntities.size()));

		Map<Integer, MarksResponseDto> map = new HashMap<>();

		marksEntities.forEach(obj -> {

			SubjectDetails subjectDetail = new SubjectDetails(obj.getSubject().getSubjectId(),
					obj.getSubject().getTitle(), obj.getDate(), obj.getMark());
			if (map.containsKey(obj.getStudent().getStudentId())) {
				map.get(studentId).getSubjectDetails().add(subjectDetail);
			} else {
				MarksResponseDto marksResponseDto = new MarksResponseDto(obj.getStudent().getStudentId(),
						obj.getStudent().getFirstName(), obj.getStudent().getLastName());
				List<SubjectDetails> subjectDetails = new LinkedList<>();
				subjectDetails.add(subjectDetail);
				marksResponseDto.setSubjectDetails(subjectDetails);
				map.put(studentId, marksResponseDto);
			}

		});
		commonBaseDTO.setData(map);
		return commonBaseDTO;
	}

}
