package com.mcb.part2.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mcb.part2.entity.MarksEntity;
import com.mcb.part2.entity.StudentEntity;
import com.mcb.part2.entity.SubjectEntity;

@Repository
public interface MarksRepository extends JpaRepository<MarksEntity, Integer> {

	@Query("SELECT obj  FROM MarksEntity obj "
			+ "WHERE obj.subject = :subject and obj.student=:student  order by obj.date desc")
	List<MarksEntity> getStudentMarkes(@Param("student") StudentEntity studentEntity,
			@Param("subject") SubjectEntity serviceId);

	@Query("SELECT obj  FROM MarksEntity obj " + "WHERE obj.student=:student  order by obj.date desc")
	List<MarksEntity> getStudentMarkes(@Param("student") StudentEntity studentEntity);

}
