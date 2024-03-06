package com.stepashka.crud.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Storehouse {
	private Integer id;
	private String address;
	private Float square;
	//ManyToMany
	private Set<Good> goods = new HashSet<>();
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Float getSquare() {
		return square;
	}
	
	public void setSquare(Float square) {
		this.square = square;
	}

	public Set<Good> getGoods() {
		return goods;
	}

	public void setGoods(Set<Good> goods) {
		this.goods = goods;
	}
	
	public static Storehouse createStorehouse(Scanner scanner) throws NullPointerException, NumberFormatException{
		Storehouse storehouse = new Storehouse();
		
		System.out.print("input storehouse address\n->");
		storehouse.setAddress(scanner.nextLine());
		System.out.print("input storehouse square\n->");
		storehouse.setSquare(Float.parseFloat(scanner.nextLine()));

		return storehouse;
	}
	
	public static Storehouse editStorehouse(Storehouse storehouse, Scanner scanner) throws NullPointerException, NumberFormatException{
		String choice = "";
		
		System.out.print("edit address(y/n)\n->");
		choice = scanner.nextLine();
		if((choice.toCharArray()[0] == 'Y' || choice.toCharArray()[0] == 'y') && choice.length() == 1) {
			System.out.print("input storehouse address\n->");
			storehouse.setAddress(scanner.nextLine());
		}
		System.out.print("edit square(y/n)\n->");
		choice = scanner.nextLine();
		if((choice.toCharArray()[0] == 'Y' || choice.toCharArray()[0] == 'y') && choice.length() == 1) {
			System.out.print("input square\n->");
			storehouse.setSquare(Float.parseFloat(scanner.nextLine()));
		}
		
		return storehouse;
	}
	
	public static void printList(List<Storehouse> list) {
		System.out.printf("%-10s %-25s %-25s%n", "№", "ADDRESS", "SQUARE");
		for(Storehouse storehouse : list) {
			System.out.printf("%-10d %-25s %-25s%n", storehouse.getId(), storehouse.getAddress(), storehouse.getSquare());
		}
	}
}
