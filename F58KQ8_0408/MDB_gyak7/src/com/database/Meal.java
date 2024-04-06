package com.database;

public class Meal {
	private int MID;	
	private String Name;
	private int Price;
	private String Category;
	
	
	public int getMID() {
		return MID;
	}
	public void setMID(int mID) {
		MID = mID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
}
