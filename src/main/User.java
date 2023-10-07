package main;

import data_structures.ArrayList;
import interfaces.List;

public class User {
	
	//properties
	
	int id;
	String name;
	List<Book> books;
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getCheckedOutList() {
		return books;
	}

	public void setCheckedOutList(List<Book> checkedOutList) {
		if (checkedOutList == null) {
			throw new IllegalArgumentException("The checkedOutList cannot be null");
		}
		
        this.books = new ArrayList<>();
		for(Book book: checkedOutList)
			books.add(book);
	}
	
}
