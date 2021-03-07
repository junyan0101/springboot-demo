package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Bug;

public interface BugRepository extends CrudRepository<Bug, Integer>{
	@Query("SELECT COUNT(b.bugId) FROM Bug b WHERE b.testerId = :testerId and b.deviceId = :deviceId")
	 public int findExperience(@Param("testerId") int testerId, @Param("deviceId") int deviceId);
	
	@Query("SELECT COUNT(b.bugId) FROM Bug b WHERE b.testerId = :testerId and b.deviceId IN :deviceId")
	 public int findExperienceByDeviceList(@Param("testerId") int testerId, @Param("deviceId") List<Integer> deviceIdList);
	
	@Query("SELECT COUNT(b.bugId) FROM Bug b WHERE b.testerId = :testerId")
	 public int findExperienceTesterOnly(@Param("testerId") int testerId);

}
