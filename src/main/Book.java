package main;

import java.time.LocalDate;

public class Book {
	
	public int getId() {
		return -10;
	}
	public void setId(int id) {
		
	}
	public String getTitle() {
		return "";
	}
	public void setTitle(String title) {
		
	}
	public String getAuthor() {
		return "";
	}
	public void setAuthor(String author) {
		
	}
	public String getGenre() {
		return "";
	}
	public void setGenre(String genre) {
		
	}
	public LocalDate getLastCheckOut() {
		return null;
	}
	public void setLastCheckOut(LocalDate lastCheckOut) {
		
	}
	public boolean isCheckedOut() {
		return false;
	}
	public void setCheckedOut(boolean checkedOut) {
		
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
		return "";
	}
	public float calculateFees() {
		/*
		 * fee (if applicable) = base fee + 1.5 per additional day
		 */
		return -1000;
	}
}
