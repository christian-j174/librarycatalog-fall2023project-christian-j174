package main;

import java.time.LocalDate;

public class Book {
	
	//Properties
	int id;
	String title;
	String author;
	String genre;
	LocalDate lastCheckOut;
	boolean isCheckedOut;
	
	// Constructors
	
	Book(){}
	
	Book(int id, String title, String author, String genre, LocalDate lastCheckOut, boolean isCheckedOut){
		this.id = id;
		this.title = title;
		this.author = author;
		this.genre = genre; 
		this.lastCheckOut = lastCheckOut;
		this.isCheckedOut = isCheckedOut; 
		
	}
	
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return this.author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return this.genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public LocalDate getLastCheckOut() {
		return this.lastCheckOut;
	}
	public void setLastCheckOut(LocalDate lastCheckOut) {
		this.lastCheckOut = lastCheckOut;
	}
	public boolean isCheckedOut() {
		return this.isCheckedOut;
	}
	public void setCheckedOut(boolean checkedOut) {
		this.isCheckedOut = checkedOut;
	}
	
	@Override
	public String toString() {
		/*
		 * This is supposed to follow the format
		 * 
		 * {TITLE} By {AUTHOR}
		 * 
		 * Both the title and author are in uppercase.
		 */
		String printify = this.getTitle() + " By " + this.getAuthor();
		printify = printify.toUpperCase();
		return printify;
	}
	public float calculateFees() {
		/*
		 * fee (if applicable) = base fee + 1.5 per additional day
		 */
		return -1000;
	}
}
