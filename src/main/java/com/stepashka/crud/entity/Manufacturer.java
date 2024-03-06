package com.stepashka.crud.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Manufacturer {
	private Integer id;
	private String name;
	private String country;
	//OneToMany
	private Set<Good> goods = new HashSet<>();
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}

	public Set<Good> getGoods() {
		return goods;
	}

	public void setGoods(Set<Good> goods) {
		this.goods = goods;
	}
	
	public static Manufacturer createManufacturer(Scanner scanner) {
		Manufacturer manufacturer = new Manufacturer();
		
		System.out.print("input manufacturer's name\n->");
		manufacturer.setName(scanner.nextLine());
		System.out.print("input manufacturer's country\n->");
		manufacturer.setCountry(scanner.nextLine());

		return manufacturer;
	}
	
	public static Manufacturer editManufacturer(Manufacturer manufacturer, Scanner scanner) {
		String choice = "";
		
		System.out.print("edit name(y/n)\n->");
		choice = scanner.nextLine();
		if((choice.toCharArray()[0] == 'Y' || choice.toCharArray()[0] == 'y') && choice.length() == 1) {
			System.out.print("input manufacturer's name\n->");
			manufacturer.setName(scanner.nextLine());
		}
		System.out.print("edit country(y/n)\n->");
		choice = scanner.nextLine();
		if((choice.toCharArray()[0] == 'Y' || choice.toCharArray()[0] == 'y') && choice.length() == 1) {
			System.out.print("input country\n->");
			manufacturer.setCountry(scanner.nextLine());
		}
		
		return manufacturer;
	}
	
	public static void printList(List<Manufacturer> list) {
		System.out.printf("%-10s %-25s %-25s\n", "№", "NAME", "COUNTRY");
		for(Manufacturer manufacturer : list) {
			System.out.printf("%-10d %-25s %-25s\n", manufacturer.getId(), manufacturer.getName(), manufacturer.getCountry());
		}
	}
	
	public static void printManufacturer(Manufacturer manufacturer) {
		System.out.printf("%-10s %-15s %-15s %-15s %-15s\n", "№", "NAME", "MODEL", "GUARANTEE", "PRICE");
		for(Good good : manufacturer.getGoods()) {
			System.out.printf("%-10d %-15s %-15s %-15d %-15.2f\n", good.getId(), good.getName(), good.getModel(), good.getGuarantee(), good.getPrice());
		}
	}
}
