package main;

import java.time.LocalDate;
import java.util.function.ToIntFunction;

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
		String printify = this.getTitle() + " By " + this.getAuthor();
		printify = printify.toUpperCase();
		return printify;
	}


    /**
     * Calculates the fees due for the book if it has been checked out for more than
     * a specified period.
     *
     * @return The fee due for the book, or 0 if the book is not checked out or is within
     * the allowed checkout period
     */
	public float calculateFees() {
		if(!isCheckedOut())
			return 0;

		float fee = 0.0f;
		LocalDate today = LocalDate.parse("2023-09-15");
		float daysPassed = today.toEpochDay() - getLastCheckOut().toEpochDay();

		if(daysPassed >= 31 && isCheckedOut()){
			fee = (float) (10 + (1.50 * (daysPassed - 31)));
		}
		return fee;
	}
}
