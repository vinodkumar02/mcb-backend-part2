package com.mcb.part2.service;

import com.mcb.part2.dto.CommonBaseDTO;

public interface SubjectsService {

	CommonBaseDTO addSubject(String title);
	CommonBaseDTO updateSubject(String title, Integer subjectId);
	CommonBaseDTO deleteSubject(Integer subjectId);
	CommonBaseDTO findALLSubjects(int pageNo, int pageSize);
	CommonBaseDTO findSubjectsById(Integer subjectId);
}
