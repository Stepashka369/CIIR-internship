package com.stepashka.crud;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.apache.log4j.Logger;
import com.stepashka.crud.entity.Manufacturer;
import com.stepashka.crud.entity.Storehouse;
import com.stepashka.crud.repository.AbstractDao;
import com.stepashka.crud.repository.ManufacturerDao;
import com.stepashka.crud.repository.StorehouseDao;

public class Menu {
	private static final Logger logger = Logger.getLogger(Menu.class);
	private Scanner scanner;
	private AbstractDao<Storehouse> storehouseRep = new StorehouseDao();
	private AbstractDao<Manufacturer> manufacturerRep = new ManufacturerDao();
	
	public void mainMenu() {
		boolean isMainMenuCycle = true;
		String choice = "";
		scanner = new Scanner(System.in); 
		
		while(isMainMenuCycle) {
			System.out.println(">> 1 - Admin.\n>> 2 - Client.\n>> 3 - Exit.");
			choice = scanner.nextLine();
			switch(choice) {
				case "1":
					adminMenu();
					break;
				case "2":
					
					break;
				case "3":
					isMainMenuCycle = false;
					break;
				default:
					
					break;	
			}
		}
		
		scanner.close();
	} 

	
	private void adminMenu() {
		boolean isAdminMenuCycle = true;
		String choice = "";
		
		while(isAdminMenuCycle) {
			System.out.println(">> 1 - Storehouse.%n>> 2 - Manufacturer.%n>> 3 - Good.%n>> 4 - Exit.");
			choice = scanner.nextLine();
			switch(choice) {
				case "1":
					storehouseActionMenu();
					break;
				case "2":
					manufacturerActionMenu();	
					break;
				case "3":
						
					break;
				case "4":
					isAdminMenuCycle = false;
					break;
				default:
					
					break;	
			}
		}
	}
	
	private void storehouseActionMenu() {
		boolean isStorehouseMenuCycle = true;
		String choice = "";
		
		while(isStorehouseMenuCycle) {
			try {
				System.out.print(">> 1 - Add storehouse.\n>> 2 - Update storehouse info.\n>> 3 - Show storehouse list.\n>> 4 - Delete storehouse.\n>> 5 - Exit.\n->");
				choice = scanner.nextLine();
				switch(choice) {
					case "1":
						storehouseRep.save(Storehouse.createStorehouse(scanner));
						break;
					case "2":
						List<Storehouse> list = storehouseRep.findAll();
						Storehouse.printList(list);
						System.out.print("select storehouse you want to edit(input №)\n->");
						Integer storehouseId = Integer.parseInt(scanner.nextLine());
						Optional<Storehouse> storehouse = list.stream().filter(x -> x.getId().equals(storehouseId)).findFirst();
						if(storehouse.isPresent()) {
							storehouseRep.update(Storehouse.editStorehouse(storehouse.get(), scanner));
						}
						else {
							System.out.print(">>storehouse not found\n");
						}
						break;
					case "3":
						Storehouse.printList(storehouseRep.findAll());
						break;
					case "4":
						Storehouse.printList(storehouseRep.findAll());
						System.out.print("select storehouse you want to remove(input №)\n->");
						storehouseRep.delete(Integer.parseInt(scanner.nextLine()));
						break;
					case "5":
						isStorehouseMenuCycle = false;
						break;
					default:
						break;		
				}
			} catch(NullPointerException exception) {
				System.out.print(">>incorrect input(empty string)\n");
			} catch(NumberFormatException exception) {
				System.out.print(">>incorrect input(not number)\n");
			} catch(SQLException exception) {
				logger.error(exception.getMessage());	
			}
		}
	}
	
	private void manufacturerActionMenu() {
		boolean isManufacturerMenuCycle = true;
		String choice = "";
		List<Manufacturer> list;
		Optional<Manufacturer> manufacturer;
		
		while(isManufacturerMenuCycle) {
			try {
				System.out.print(">> 1 - Add manufacturer.\n>> 2 - Update manufacturer info.\n>> 3 - Show manufacturer list.\n>> 4 - Delete manufacturer.\n>> 5 - Show manufacturer's goods.\n>> 6 - Exit.\n->");
				choice = scanner.nextLine();
				switch(choice) {
					case "1":
						manufacturerRep.save(Manufacturer.createManufacturer(scanner));
						break;
					case "2":
						list = manufacturerRep.findAll();
						Manufacturer.printList(list);
						System.out.print("select manufacturer you want to edit(input №)\n->");
						Integer updateManufacturerId =  Integer.parseInt(scanner.nextLine());
						manufacturer = list.stream().filter(x -> x.getId().equals(updateManufacturerId)).findFirst();
						if(manufacturer.isPresent()) 
							manufacturerRep.update(Manufacturer.editManufacturer(manufacturer.get(), scanner));
						else 
							System.out.print(">>manufacturer not found\n");
						break;
					case "3":
						Manufacturer.printList(manufacturerRep.findAll());
						break;
					case "4":
						Manufacturer.printList(manufacturerRep.findAll());
						System.out.print("select manufacturer you want to remove(input №)\n->");
						manufacturerRep.delete(Integer.parseInt(scanner.nextLine()));
						break;
					case "5":
						list = manufacturerRep.findAll();
						Manufacturer.printList(list);
						System.out.print("select manufacturer you want to see goods(input №)\n->");
						Integer findManufactureId = Integer.parseInt(scanner.nextLine());
						manufacturer = list.stream().filter(x -> x.getId().equals(findManufactureId)).findFirst();
						if(manufacturer.isPresent()) {
							Manufacturer.printManufacturer(manufacturerRep.findById(manufacturer.get().getId()));
						}
						else {
							System.out.print(">>manufacturer not found\n");
						}
						break;
					case "6":
						isManufacturerMenuCycle = false;
						break;
					default:
						break;		
				}
			} catch(NullPointerException exception) {
				System.out.print(">>incorrect input(empty string)\n");
			} catch(NumberFormatException exception) {
				System.out.print(">>incorrect input(not number)\n");
			} catch(SQLException exception) {
				logger.error(exception.getMessage());	
			}
		}
	}
}
