package com.concretepage;

import java.util.List;

public class App {
	public static void main(String[] args) {
		BookDao bookDAO = new BookDao();
//insert
		Book book1 = new Book();
		book1.setIsbn("I1");
		book1.setTitle("I1 könyv");
		book1.setPrice(4000);
		bookDAO.save(book1);
//getAllData
		List<Book> books = bookDAO.getAllData();
		for (Book b : books) {
			System.out.println("isbn: " + b.getIsbn() + ", title: " + b.getTitle() + ", price: " + b.getPrice());
		}
	}
}