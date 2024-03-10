package com.stepashka.crud.utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import com.stepashka.crud.entity.Good;
import com.stepashka.crud.entity.Manufacturer;
import com.stepashka.crud.repository.AbstractDao;


public class ManufacturerActions {
	
	private ManufacturerActions() {
		throw new IllegalStateException("Utility class");
	}
	
	public static Integer createManufacturer(AbstractDao<Manufacturer> repository, Scanner scanner) throws SQLException {
		Manufacturer manufacturer = new Manufacturer();
			
		System.out.print("input manufacturer's name\n->");
		manufacturer.setName(scanner.nextLine());
		System.out.print("input manufacturer's country\n->");
		manufacturer.setCountry(scanner.nextLine());
		
		return repository.save(manufacturer);
	}
	
	public static List<Manufacturer> printManufacturerList(AbstractDao<Manufacturer> repository) throws SQLException{
		List<Manufacturer> list = repository.findAll();
		
		if(!list.isEmpty()) {
			System.out.printf("%-10s %-25s %-25s%n", "№", "NAME", "COUNTRY");
			for(Manufacturer manufacturer : list) {
				System.out.printf("%-10d %-25s %-25s\n", manufacturer.getId(), manufacturer.getName(), manufacturer.getCountry());
			}
		} 
		else {
			System.out.println(">>manufacturer list is empty\n");
		}
		
		return list;
	}
	
	public static void editManufacturer(AbstractDao<Manufacturer> repository, Scanner scanner) throws SQLException, NumberFormatException{
		List<Manufacturer> list = ManufacturerActions.printManufacturerList(repository);
		
		if(!list.isEmpty()) {
			System.out.print("select manufacturer you want to edit(input №)\n->");
			Integer manufacturerId =  Integer.parseInt(scanner.nextLine());
			Optional<Manufacturer> manufacturer = list.stream().filter(x -> x.getId().equals(manufacturerId)).findFirst();
			if(manufacturer.isPresent()) {
				System.out.print("edit name(y/n)\n->");
				String choice = scanner.nextLine();
				if((choice.toCharArray()[0] == 'Y' || choice.toCharArray()[0] == 'y') && choice.length() == 1) {
					System.out.print("input manufacturer's name\n->");
					manufacturer.get().setName(scanner.nextLine());
				}
				System.out.print("edit country(y/n)\n->");
				choice = scanner.nextLine();
				if((choice.toCharArray()[0] == 'Y' || choice.toCharArray()[0] == 'y') && choice.length() == 1) {
					System.out.print("input country\n->");
					manufacturer.get().setCountry(scanner.nextLine());
				}
				repository.update(manufacturer.get());
			}	
			else {
				System.out.print(">>manufacturer not found\n");
			}
		}
	}	
	
	public static Integer deleteManufacturer(AbstractDao<Manufacturer> repository, Scanner scanner) throws SQLException, NumberFormatException {
		ManufacturerActions.printManufacturerList(repository);
		System.out.print("select manufacturer you want to remove(input №)\n->");
		Integer manufacturerId = Integer.parseInt(scanner.nextLine());
		
		return repository.delete(manufacturerId);
	}
	
	public static void printManufacturerGoods(AbstractDao<Manufacturer> repository, Scanner scanner) throws SQLException, NumberFormatException {
		List<Manufacturer> list = ManufacturerActions.printManufacturerList(repository);
		
		System.out.print("select manufacturer you want to see goods(input №)\n->");
		Integer manufactureId = Integer.parseInt(scanner.nextLine());
		Optional<Manufacturer> manufacturer = list.stream().filter(x -> x.getId().equals(manufactureId)).findFirst();
		if(manufacturer.isPresent()) {
			Set<Good> goods = repository.findById(manufactureId).getGoods();
			if(!goods.isEmpty()) {
				System.out.printf("%-10s %-15s %-15s %-15s %-15s%n", "№", "NAME", "MODEL", "GUARANTEE", "PRICE");
				for(Good good : goods) {
					System.out.printf("%-10d %-15s %-15s %-15d %-15.2f%n", good.getId(), good.getName(), good.getModel(), good.getGuarantee(), good.getPrice());
				}
			}
			else {
				System.out.print(">>manufacturer has no goods\n");
			}
		}
		else {
			System.out.print(">>manufacturer not found\n");
		}
	}
}
