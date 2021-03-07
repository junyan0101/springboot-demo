package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Tester;

public interface TesterRepository extends JpaRepository<Tester, Integer>{
	
     @Query("SELECT DISTINCT t FROM Tester t JOIN t.devices d WHERE t.country = :country and d.description = :device")
	 public List<Tester> matchTestersByCountry(@Param("country") String country, @Param("device") String device);
 
     @Query("SELECT t FROM Tester t WHERE t.country = :country")
	 public List<Tester> matchTestersByCountry1(@Param("country") String country);

     @Query("SELECT DISTINCT t FROM Tester t JOIN t.devices d WHERE t.country IN :country and d.description IN :device")
	 public List<Tester> matchTestersByCountryList(@Param("country") List<String> countryList, @Param("device") List<String> deviceList);

     
     @Query("SELECT t FROM Tester t WHERE t.country IN :country")
	 public List<Tester> matchTestersByCountryListOnly(@Param("country") List<String> countryList);
     
     @Query("SELECT DISTINCT t FROM Tester t JOIN t.devices d WHERE d.description IN :device")
	 public List<Tester> matchTestersByDeviceListOnly(@Param("device") List<String> deviceList);

}
