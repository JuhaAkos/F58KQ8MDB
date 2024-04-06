package com.database;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {

		AllDao dao = new AllDao();
		
        Scanner sc = new Scanner(System.in);
        
        System.out.println("----------------------------------------");
        System.out.print("Insert data for customer? (y/n): ");
        
        String decision = sc.nextLine();
        
        if(decision.contains("y")) {
		
			//insert a customer
        	
        	System.out.println("Please provide the datas below: ");
            System.out.print("CID (customer id): ");
            int cid = sc.nextInt();
            sc.nextLine();

            System.out.print("Address: ");
            String address = sc.nextLine();

            System.out.print("Phonenumber: ");
            String phonenumber = sc.nextLine();
        	
			Customer customer = new Customer();
			
			customer.setCID(cid);
			customer.setAddress(address);
			customer.setPhonenumber(phonenumber);
			dao.saveCustomer(customer);
			
        }
		
		//getAllCustomerData
		
        System.out.println("----------------------------------------");
        System.out.println("All customer data:\n");
		
		List<Customer> customers = dao.getAllCustomerData();
		for(Customer c : customers) {
			System.out.println("CID: " + c.getCID() + ", address: " + c.getAddress() +
					", phonenumber: " + c.getPhonenumber());
		}
		
		//insertMealData
		
        System.out.println("----------------------------------------");
        System.out.print("Insert meal data? (y/n): ");
        
        decision = sc.nextLine();
        
        if(decision.contains("y")) {
		        	
        	System.out.println("Please provide the datas below: ");
            System.out.print("MID: ");
            int MID = sc.nextInt();
            sc.nextLine();

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Price of the meal: ");
            int price = sc.nextInt();
            sc.nextLine();

            System.out.print("Food category: ");
            String category = sc.nextLine();
        	
			Meal dish = new Meal();
						
			dish.setMID(MID);
			dish.setName(name);
			dish.setPrice(price);
			dish.setCategory(category);
			dao.saveMeal(dish);			
        }
        
        //getAllMealData
		
        System.out.println("----------------------------------------");
        System.out.println("All dish data:\n");
		
		List<Meal> meals = dao.getAllMealData();
		for(Meal m : meals) {
			System.out.println("MID: " + m.getMID() + ", name: " + m.getName() +
					", price: " + m.getPrice() + ", category: " + m.getCategory());
		}
		
		//most and least expensive dishes on the menu
		
		System.out.println("----------------------------------------");
        System.out.println("Most expensive dish on the menu:");
        
        Meal expdish = dao.getExpensiveMeal();
        System.out.println(expdish.getPrice() + " - " + expdish.getMID() + ". name: " + expdish.getName());
        
        System.out.println("\nCheapest dish on the menu:");
        Meal cheapdish = dao.getCheapMeal();
        System.out.println(cheapdish.getPrice() + " - " + cheapdish.getMID() + ". name: " + cheapdish.getName());       
		
		
        //create mealorder		
        System.out.println("----------------------------------------");
        System.out.print("Do you want to connect ordered meals to a customer? (y/n): ");
        
        decision = sc.nextLine();
        
        if(decision.contains("y")) {
        
            System.out.print("Which meal was ordered (MID): ");
            
            int MID = sc.nextInt();
            sc.nextLine();
                        
            System.out.print("Which customer made the order (OID): ");
            
            int CID = sc.nextInt();
            sc.nextLine();
            
            MealOrder MealOrder = new MealOrder();
            MealOrder.setMID(MID);
            MealOrder.setCID(CID);
            
            dao.saveMealOrder(MealOrder);
        
        }
		
		
		//getAllMealOrder
		
        System.out.println("----------------------------------------");
        System.out.println("All connection between ordered meals and customer:\n");
		
		List<MealOrder> MealOrderList = dao.findAllCustomerRelationships();
		for (MealOrder mo : MealOrderList) {
		    if (mo != null) {
		        System.out.println("Customer number: " + mo.getCID() + ", Meal number: " + mo.getMID());
		    } else {
		        System.out.println("Encountered empty obj");
		    }
		}
		
		//all ordermeals which have at least one connection
		
		System.out.println("----------------------------------------");
        System.out.println("All customers who have still valid orders:\n");
		
		List<Customer> customer = dao.findCustomersWhoOrdered();
		for (Customer c : customer) {
			System.out.println("CID: " + c.getCID() + ", address: " + c.getAddress() +
					", phonenumber: " + c.getPhonenumber());
		}
		
		//delete customer
		
		System.out.println("----------------------------------------");
        System.out.print("Do you want to delete a customer? (y/n): ");
        
        String delete = sc.nextLine();
        
		if (delete.contains("y")) {

			System.out.println("----------------------------------------");
			System.out.print("Please write down the ID of the customer you wish to delete: ");
			int id = sc.nextInt();

			dao.deleteMealOrderForCustomer(id);
			dao.deleteCustomerById(id);

			System.out.println("Customer deleted successfully.");

			sc.close();

			// getAllFactoryData

			System.out.println("----------------------------------------");
	        System.out.println("All customer data:\n");
			
			List<Customer> customers2 = dao.getAllCustomerData();
			for(Customer c : customers2) {
				System.out.println("CID: " + c.getCID() + ", address: " + c.getAddress() +
						", phonenumber: " + c.getPhonenumber());
			}
		}

		System.out.println("----------------------------------------");
		System.out.println("Program has finished running.");

	}

}