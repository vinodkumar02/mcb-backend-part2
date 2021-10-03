package com.mcb.part2.service;

import com.mcb.part2.dto.CommonBaseDTO;
import com.mcb.part2.dto.MarksDto;

public interface MarksService {

	CommonBaseDTO addMarks(MarksDto markDto);

	CommonBaseDTO updateMarks(Integer markId, MarksDto markDto);

	CommonBaseDTO deleteMarks(Integer markId);

	CommonBaseDTO findALLMarks(int pageNo, int pageSize);

	CommonBaseDTO findMarksById(Integer markId);

	CommonBaseDTO findStudentMarks(Integer studentId, Integer subjectId);

	CommonBaseDTO findStudentMarks(Integer studentId);
}
