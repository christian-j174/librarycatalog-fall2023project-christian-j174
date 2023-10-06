package main;

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
		// this.books = checkedOutList;
	}
	
}
