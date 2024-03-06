package com.stepashka.crud.utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import com.stepashka.crud.entity.Storehouse;
import com.stepashka.crud.repository.AbstractDao;

public class StorehouseActions {
	
	public static Integer createStorehouse(AbstractDao<Storehouse> repository, Scanner scanner) throws SQLException, NumberFormatException {
		Storehouse storehouse = new Storehouse();
			
		System.out.print("input storehouse address\n->");
		storehouse.setAddress(scanner.nextLine());
		System.out.print("input storehouse square\n->");
		storehouse.setSquare(Float.parseFloat(scanner.nextLine()));

		return repository.save(storehouse);
	}
	
	public static List<Storehouse> printStorehouseList(AbstractDao<Storehouse> repository) throws SQLException{
		List<Storehouse> list = repository.findAll();
		
		if(!list.isEmpty()) {
			System.out.printf("%-10s %-25s %-25s%n", "№", "ADDRESS", "SQUARE");
			for(Storehouse storehouse : list) {
				System.out.printf("%-10d %-25s %-25s%n", storehouse.getId(), storehouse.getAddress(), storehouse.getSquare());
			}
		} 
		else {
			System.out.println(">>storehouse list is empty\n");
		}
		
		return list;
	}
	
	public static void editStorehouse(AbstractDao<Storehouse> repository, Scanner scanner) throws SQLException, NumberFormatException{
		List<Storehouse> list = StorehouseActions.printStorehouseList(repository);
		
		if(!list.isEmpty()) {
			System.out.print("select storehouse you want to edit(input №)\n->");
			Integer storehouseId =  Integer.parseInt(scanner.nextLine());
			Optional<Storehouse> storehouse = list.stream().filter(x -> x.getId().equals(storehouseId)).findFirst();
			if(storehouse.isPresent()) {
				System.out.print("edit address(y/n)\n->");
				String choice = scanner.nextLine();
				if((choice.toCharArray()[0] == 'Y' || choice.toCharArray()[0] == 'y') && choice.length() == 1) {
					System.out.print("input storehouse address\n->");
					storehouse.get().setAddress(scanner.nextLine());
				}
				System.out.print("edit square(y/n)\n->");
				choice = scanner.nextLine();
				if((choice.toCharArray()[0] == 'Y' || choice.toCharArray()[0] == 'y') && choice.length() == 1) {
					System.out.print("input square\n->");
					storehouse.get().setSquare(Float.parseFloat(scanner.nextLine()));
				}
				repository.update(storehouse.get());
			}	
			else {
				System.out.print(">>storehouse not found\n");
			}
		}
	}	
	
	public static Integer deleteStorehouse(AbstractDao<Storehouse> repository, Scanner scanner) throws SQLException, NumberFormatException {
		StorehouseActions.printStorehouseList(repository);
		System.out.print("select storehouse you want to remove(input №)\n->");
		Integer storehouseId = Integer.parseInt(scanner.nextLine());
		
		return repository.delete(storehouseId);
	}
}
