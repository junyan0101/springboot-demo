package com.example.demo.bootstrap;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Optional;

import org.h2.tools.Csv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import com.example.demo.domain.Bug;
import com.example.demo.domain.Device;
import com.example.demo.domain.Tester;
import com.example.demo.repositories.BugRepository;
import com.example.demo.repositories.DeviceRepository;
import com.example.demo.repositories.TesterRepository;

@Component
public class BootStrapData implements CommandLineRunner{
	private final TesterRepository testerRepo;
	private final DeviceRepository deviceRepo;
	private final BugRepository bugRepo;

     @Value("${apllause.data.location}")
    private String dataLocation;  


	public BootStrapData(TesterRepository testerRepo, DeviceRepository deviceRepo,
			BugRepository bugRepo) {
		super();
		this.testerRepo = testerRepo;
		this.deviceRepo = deviceRepo;
		this.bugRepo = bugRepo;
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("started in bootstrap");

		
        ResultSet rsTester = new Csv().read(dataLocation + "testers.csv", null, null);
        ResultSetMetaData metaTester = rsTester.getMetaData();
        while (rsTester.next()) {
        	/*
            for (int i = 0; i < metaTester.getColumnCount(); i++) {
                System.out.println(
                		metaTester.getColumnLabel(i + 1) + ": " +
                    		rsTester.getString(i + 1));
                    
               
            }
            System.out.println();
            */
            Tester tester = new Tester();
            tester.setTesterId(Integer.parseInt(rsTester.getString(1)));
            tester.setFirstName(rsTester.getString(2));
            tester.setLastName(rsTester.getString(3));
            tester.setCountry(rsTester.getString(4));
            tester.setLastLogin(rsTester.getString(5));
            testerRepo.save(tester);
        }
        rsTester.close();	
        
        ResultSet rsDevice = new Csv().read(dataLocation + "devices.csv", null, null);

        while (rsDevice.next()) {
            Device device = new Device();
            device.setDeviceId(Integer.parseInt(rsDevice.getString(1)));
            device.setDescription(rsDevice.getString(2));

            deviceRepo.save(device);
        }
        rsDevice.close();
        
        
        ResultSet rsTesterDevice = new Csv().read(dataLocation + "tester_device.csv", null, null);
        ResultSetMetaData metaTesterDevice = rsTesterDevice.getMetaData();
        Integer testerId, deviceId;
        
        while (rsTesterDevice.next()) {
        	/*
            for (int i = 0; i < metaTesterDevice.getColumnCount(); i++) {
                System.out.println(
                		metaTesterDevice.getColumnLabel(i + 1) + ": " +
                				rsTesterDevice.getString(i + 1));
                    
               
            }
            System.out.println();
            */
            testerId = Integer.parseInt(rsTesterDevice.getString(1));
            deviceId = Integer.parseInt(rsTesterDevice.getString(2));
            Optional<Device> deviceOpt = deviceRepo.findById(deviceId);
            Optional<Tester> testerOpt = testerRepo.findById(testerId);
            deviceOpt.ifPresent(device -> {
                testerOpt.ifPresent(tester -> {
                    tester.getDevices().add(device);
                    testerRepo.save(tester);

                });
            });
        }
        rsTesterDevice.close();
        
        ResultSet rs = new Csv().read(dataLocation + "bugs.csv", null, null);
        ResultSetMetaData meta = rs.getMetaData();

        while (rs.next()) {
        	/*
            for (int i = 0; i < meta.getColumnCount(); i++) {
                System.out.println(
                    meta.getColumnLabel(i + 1) + ": " +
                    rs.getString(i + 1));
                    
               
            }
            System.out.println();
            */
            Bug bug = new Bug();
            bug.setBugId(Integer.parseInt(rs.getString(1)));
            bug.setDeviceId(Integer.parseInt(rs.getString(2)));
            bug.setTesterId(Integer.parseInt(rs.getString(3)));
            bugRepo.save(bug);
        }
        rs.close();
        /*
        Iterable<Device> devices = deviceRepo.findAll();
        for (Device d : devices) {
        	
            System.out.println(d.getDeviceId() + "has " + d.getTesters().size() + " testers");
        }
        
        Iterable<Tester> testers = testerRepo.findAll();
        for (Tester t : testers) {
        	
            System.out.println(t.getTesterId() + "has " + t.getDevices().size() + " devices");
        }
        */
	}


}
