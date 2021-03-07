package com.example.demo.controllers;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repositories.BugRepository;
import com.example.demo.repositories.DeviceRepository;
import com.example.demo.repositories.TesterRepository;
import com.example.demo.domain.Device;
import com.example.demo.domain.Tester;
import com.example.demo.domain.TesterResponse;

@RestController
public class TesterController {
	
	@Autowired
	private TesterRepository testerRepo;
	
	@Autowired
	private DeviceRepository deviceRepo;
	
	@Autowired
	private BugRepository bugRepo;
	
	@GetMapping("/testers")
	public List<Tester> listTesters() {
 
        	 return testerRepo.findAll();
         }
	
	
	@GetMapping("/testermatcher")
	public List<TesterResponse> matchTestersList(@RequestParam(name="country", required = false) List<String> countryList, @RequestParam(name="device", required = false) List<String> deviceList) {

         Iterator iterDevices = deviceList.iterator();
         Iterator iterCountries = countryList.iterator();
         List<Integer> deviceIdList = new ArrayList<Integer>() ;
         boolean allDevice = false;
         boolean allCountry = false;
         while (iterDevices.hasNext()) {
        	String device = (String) iterDevices.next();
        	if (device.equalsIgnoreCase("ALL")) {
        		allDevice = true;
        		break;
        	}
        	deviceIdList.add(deviceRepo.findDeviceByType(device).get(0).getDeviceId()); 
         }
         while (iterCountries.hasNext()) {
        	String country = (String) iterCountries.next();
        	if (country.equalsIgnoreCase("ALL")) {
        		allCountry = true;
        		break;
        	}
 
         }
         
         List<Tester> testers = new ArrayList<Tester>();
         if (allCountry && allDevice) {
        	 testers = testerRepo.findAll();
         } else if (allDevice) {
        	 testers = testerRepo.matchTestersByCountryListOnly(countryList);
         } else if (allCountry) {
        	 testers = testerRepo.matchTestersByDeviceListOnly(deviceList);
         } else {
        	 testers = testerRepo.matchTestersByCountryList(countryList, deviceList); 
         }
         List<TesterResponse> testerResponses = new ArrayList<TesterResponse> ();
         Iterator iterTesters = testers.iterator();

         while (iterTesters.hasNext()) {
        	Tester tester = (Tester) iterTesters.next();
        	TesterResponse testerResponse = new TesterResponse();
        	testerResponse.setTesterId(tester.getTesterId());
        	testerResponse.setFirstName(tester.getFirstName());
        	testerResponse.setLastName(tester.getLastName());    
        	testerResponse.setCountry(tester.getCountry()); 
        	if (allDevice) {
        		testerResponse.setExperience(bugRepo.findExperienceTesterOnly(tester.getTesterId()));
        	} else {
        	     testerResponse.setExperience(bugRepo.findExperienceByDeviceList(tester.getTesterId(), deviceIdList));
        	}
            testerResponses.add(testerResponse);
         }
         Collections.sort(testerResponses);
         return testerResponses;
	}
	
	@GetMapping("/testercountrymatcher")
	public List<Tester> matchCountryTesters(@RequestParam(name="country", required = false) String country) {

         List<Tester> testers = testerRepo.matchTestersByCountry1(country);

         List<Tester> testersResponse = new ArrayList<Tester> ();
         Iterator iter = testers.iterator();
         while (iter.hasNext()) {
        	Tester tester = (Tester) iter.next();
            tester.setDevices(null);
            testersResponse.add(tester);
         }
         return testersResponse;
	}
	
	@GetMapping("/devices")
	public List<Device> matchTesters(@RequestParam(name="description", required = false) String description) {
         return deviceRepo.findDeviceByType(description);

	}
}
