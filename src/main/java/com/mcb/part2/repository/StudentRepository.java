package com.mcb.part2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mcb.part2.entity.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

	
	@Query("SELECT count(obj) FROM StudentEntity obj where obj.group.groupId IN ( SELECT obj.id.groupId from SubjectTeacherEntrity obj where obj.id.teacherId=:teacherId ) ")
	public Long studentCount(@Param("teacherId") Integer techerId);

}
