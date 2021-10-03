package com.mcb.part2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcb.part2.entity.GroupEntity;

@Repository
public interface GroupsRepository extends JpaRepository<GroupEntity, Integer> {

	
  

}
