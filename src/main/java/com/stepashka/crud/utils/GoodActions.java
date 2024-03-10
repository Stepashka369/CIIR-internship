package com.stepashka.crud.utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import com.stepashka.crud.entity.Good;
import com.stepashka.crud.entity.Manufacturer;
import com.stepashka.crud.entity.Storehouse;
import com.stepashka.crud.repository.AbstractDao;
import com.stepashka.crud.repository.ManufacturerDao;

public class GoodActions {
	
	private GoodActions() {
		throw new IllegalStateException("Utility class");
	}
	
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
	
	public static void editGood(AbstractDao<Good> repository, Scanner scanner) throws SQLException, NumberFormatException{
		List<Good> list = GoodActions.printGoodList(repository);
		
		if(!list.isEmpty()) {
			System.out.print("select good you want to edit(input №)\n->");
			Integer goodId =  Integer.parseInt(scanner.nextLine());
			Optional<Good> good = list.stream().filter(x -> x.getId().equals(goodId)).findFirst();
			if(good.isPresent()) {
				System.out.print("edit name(y/n)\n->");
				String choice = scanner.nextLine();
				if((choice.toCharArray()[0] == 'Y' || choice.toCharArray()[0] == 'y') && choice.length() == 1) {
					System.out.print("input good's name\n->");
					good.get().setName(scanner.nextLine());
				}
				System.out.print("edit model(y/n)\n->");
				choice = scanner.nextLine();
				if((choice.toCharArray()[0] == 'Y' || choice.toCharArray()[0] == 'y') && choice.length() == 1) {
					System.out.print("input model\n->");
					good.get().setModel(scanner.nextLine());
				}
				System.out.print("edit guarantee(y/n)\n->");
				choice = scanner.nextLine();
				if((choice.toCharArray()[0] == 'Y' || choice.toCharArray()[0] == 'y') && choice.length() == 1) {
					System.out.print("input guarantee\n->");
					good.get().setGuarantee(Integer.parseInt(scanner.nextLine()));
				}
				System.out.print("edit price(y/n)\n->");
				choice = scanner.nextLine();
				if((choice.toCharArray()[0] == 'Y' || choice.toCharArray()[0] == 'y') && choice.length() == 1) {
					System.out.print("input price\n->");
					good.get().setPrice(Float.parseFloat(scanner.nextLine()));
				}
				System.out.print("edit description(y/n)\n->");
				choice = scanner.nextLine();
				if((choice.toCharArray()[0] == 'Y' || choice.toCharArray()[0] == 'y') && choice.length() == 1) {
					System.out.print("input description\n->");
					good.get().setDescription(scanner.nextLine());
				}
				repository.update(good.get());
			}	
			else {
				System.out.print(">>good not found\n");
			}
		}
	}
	
	public static void showStockAvailability(AbstractDao<Good> repository, Scanner scanner) throws SQLException, NumberFormatException{
		List<Good> list = GoodActions.printGoodList(repository);
		
		if(!list.isEmpty()) {
			System.out.print("select good you want to see stock info(input №)\n->");
			Integer goodId = Integer.parseInt(scanner.nextLine());
			Optional<Good> goodExistense = list.stream().filter(x -> x.getId().equals(goodId)).findFirst();
			if(goodExistense.isPresent()) {
				Good good = repository.findById(goodId);
				if(!good.getStorehouses().isEmpty()){
					System.out.printf("%-10s %-15s %-10s%n", "№", "ADDRESS", "CUANTITY");
					for(Map.Entry<Storehouse, Integer> entry : good.getStorehouses().entrySet()) {
						System.out.printf("%-10d %-15s %-10d%n", entry.getKey().getId(), entry.getKey().getAddress(), entry.getValue());
					}
				}
				else {
					System.out.print(">>there is no good in stocks\n");
				}
			}
			else {
				System.out.print(">>good not found\n");
			}
		}
	}
}



