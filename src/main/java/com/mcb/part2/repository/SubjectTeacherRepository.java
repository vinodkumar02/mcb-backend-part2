package com.mcb.part2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcb.part2.entity.SubjectTeacherEntrity;
import com.mcb.part2.entity.SubjectTeacherKey;


@Repository
public interface SubjectTeacherRepository extends JpaRepository<SubjectTeacherEntrity, SubjectTeacherKey> {

	
}
