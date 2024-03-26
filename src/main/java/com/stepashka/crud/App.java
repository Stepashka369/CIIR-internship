package com.stepashka.crud;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;

import com.stepashka.crud.entity.Good;
import com.stepashka.crud.entity.Manufacturer;
import com.stepashka.crud.repository.GoodDao;
import com.stepashka.crud.repository.ManufacturerDao;

public class App {
	public static void main(String[] args) {
		PropertyConfigurator.configure("G:\\studies\\CIIR\\CIIR-internship\\src\\main\\resources\\logging.properties");
		ManufacturerDao repository = new ManufacturerDao();
		List<Manufacturer> manufacturers;
		try {
			manufacturers = repository.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
//		GoodDao repository = new GoodDao();
//		Good good = new Good();
//		good.setDescription("gaming");
//		good.setName("laptop");
//		good.setModel("asus-456");
//		good.setPrice(768.453f);
//		good.setGuarantee(13);
//		good.setManufacturer(new Manufacturer());
//		good.getManufacturer().setId(2);
//		//List<Good> goods;
//		try {
//			int res = repository.save(good);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		// Menu menu = new Menu();
		// menu.mainMenu();
	}
}
