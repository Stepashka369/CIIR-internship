package com.stepashka.crud;

import java.sql.SQLException;
import java.util.Scanner;
import org.apache.log4j.Logger;
import com.stepashka.crud.entity.Manufacturer;
import com.stepashka.crud.entity.Storehouse;
import com.stepashka.crud.repository.AbstractDao;
import com.stepashka.crud.repository.ManufacturerDao;
import com.stepashka.crud.repository.StorehouseDao;
import com.stepashka.crud.utils.ManufacturerActions;
import com.stepashka.crud.utils.StorehouseActions;

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
			System.out.println(">> 1 - Storehouse.\n>> 2 - Manufacturer.\n>> 3 - Good.\n>> 4 - Exit.");
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
						StorehouseActions.createStorehouse(storehouseRep, scanner);
						break;
					case "2":
						StorehouseActions.editStorehouse(storehouseRep, scanner);
						break;
					case "3":
						StorehouseActions.printStorehouseList(storehouseRep);
						break;
					case "4":
						StorehouseActions.deleteStorehouse(storehouseRep, scanner);
						break;
					case "5":
						isStorehouseMenuCycle = false;
						break;
					default:
						break;		
				}
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
		
		while(isManufacturerMenuCycle) {
			try {
				System.out.print(">> 1 - Add manufacturer.\n>> 2 - Update manufacturer info.\n>> 3 - Show manufacturer list.\n>> 4 - Delete manufacturer.\n>> 5 - Show manufacturer's goods.\n>> 6 - Exit.\n->");
				choice = scanner.nextLine();
				switch(choice) {
					case "1":
						ManufacturerActions.createManufacturer(manufacturerRep, scanner);
						break;
					case "2":
						ManufacturerActions.editManufacturer(manufacturerRep, scanner);
						break;
					case "3":
						ManufacturerActions.printManufacturerList(manufacturerRep);
						break;
					case "4":
						ManufacturerActions.deleteManufacturer(manufacturerRep, scanner);
						break;
					case "5":	
						ManufacturerActions.printManufacturerGoods(manufacturerRep, scanner);
						break;
					case "6":
						isManufacturerMenuCycle = false;
						break;
					default:
						break;		
				}
			} catch(NumberFormatException exception) {
				System.out.print(">>incorrect input(not number)\n");
			} catch(SQLException exception) {
				logger.error(exception.getMessage());	
			}
		}
	}
}
