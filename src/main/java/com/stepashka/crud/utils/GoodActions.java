package com.stepashka.crud.utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.stepashka.crud.entity.Good;
import com.stepashka.crud.entity.Manufacturer;
import com.stepashka.crud.repository.AbstractDao;
import com.stepashka.crud.repository.ManufacturerDao;

public class GoodActions {
	public static List<Good> printGoodList(AbstractDao<Good> repository) throws SQLException{
		List<Good> list = repository.findAll();
		
		if(!list.isEmpty()) {
			System.out.printf("%-10s %-15s %-15s %-15s %-10s %-15s %-15s%n", "№", "NAME", "MODEL", "GUARANTEE", "PRICE", "MANUFACTURER", "COUNTRY");
			for(Good good : list) {
				System.out.printf("%-10d %-15s %-15s %-15d %-10.2f %-15s %-15s%n", good.getId(), good.getName(), good.getModel(),
																					good.getGuarantee(), good.getPrice(), 
																					good.getManufacturer().getName(), good.getManufacturer().getCountry());
			}
		} 
		else {
			System.out.println(">>good list is empty\n");
		}
		
		return list;
	}
	
	public static Integer createGood(AbstractDao<Good> repository, Scanner scanner) throws SQLException, NumberFormatException {
		Good good = new Good();
		
		System.out.print("input good name\n->");
		good.setName(scanner.nextLine());
		System.out.print("input good model\n->");
		good.setModel(scanner.nextLine());
		System.out.print("input good guarantee time(in days)\n->");
		good.setGuarantee(Integer.parseInt(scanner.nextLine()));
		System.out.print("input good price\n->");
		good.setPrice(Float.parseFloat(scanner.nextLine()));
		System.out.print("input good description\n->");
		good.setDescription(scanner.nextLine());
		List<Manufacturer> list = ManufacturerActions.printManufacturerList(new ManufacturerDao());
		System.out.print("select manufacturer from list(input №)\n->");
		Integer manufacturerId = Integer.parseInt(scanner.nextLine());
		Optional<Manufacturer> manufacturer = list.stream().filter(x -> x.getId().equals(manufacturerId)).findFirst();
		if(manufacturer.isPresent()) {
			good.setManufacturer(manufacturer.get());
			return repository.save(good);
		}
		else {
			System.out.print(">>manufacturer not found\n");
			return 0;
		}	
	}
	
	public static Integer deleteGood(AbstractDao<Good> repository, Scanner scanner) throws SQLException, NumberFormatException {
		GoodActions.printGoodList(repository);
		System.out.print("select good you want to remove(input №)\n->");
		Integer goodId = Integer.parseInt(scanner.nextLine());
		
		return repository.delete(goodId);
	}
}
