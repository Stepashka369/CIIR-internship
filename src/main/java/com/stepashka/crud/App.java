package com.stepashka.crud;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;

import com.stepashka.crud.entity.Manufacturer;
import com.stepashka.crud.entity.Storehouse;
import com.stepashka.crud.repository.AbstractDao;
import com.stepashka.crud.repository.ManufacturerDao;
import com.stepashka.crud.repository.StorehouseDao;

public class App {
	 public static void main( String[] args ) {
		PropertyConfigurator.configure("G:\\studies\\CIIR\\CIIR-internship\\src\\main\\resources\\logging.properties");
		Menu menu = new Menu();
		menu.mainMenu();
//	    Storehouse entity = new Storehouse();
//	    entity.setId(11);
//	    entity.setAddress("gomel");
//	    entity.setSquare(100.128f);
//	    AbstractDao<Storehouse> storehouseRepository = new StorehouseDao();
//	    AbstractDao<Manufacturer> manufacturerRepository = new ManufacturerDao();
//	    try{
//	    	//storehouseRepository.save(storehouse);
//	    	//storehouseRepository.delete(7);
//	    	//storehouseRepository.delete(8);
//	    	storehouseRepository.update(entity);
//	    	manufacturerRepository.findById(3);
//	    	
//	    	List<Storehouse> list = storehouseRepository.findAll();
//	    	for(Storehouse storehouse : list) {
//	    		System.out.println("id: " + storehouse.getId() + "  address: " + storehouse.getAddress() + "  square: " + storehouse.getSquare());
//	    	}
//	    } catch(SQLException exception) {
//	    	
//	    } 
	    
	 }
}
