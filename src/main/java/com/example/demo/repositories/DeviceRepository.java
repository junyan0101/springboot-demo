package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Device;


public interface DeviceRepository extends CrudRepository<Device, Integer> {

    @Query("SELECT d FROM Device d WHERE d.description = :description")
	 public List<Device> findDeviceByType(@Param("description") String description);
}
