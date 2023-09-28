package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import data_structures.ArrayList;
import data_structures.DoublyLinkedList;
import data_structures.SinglyLinkedList;
import interfaces.FilterFunction;
import interfaces.List;

public class LibraryCatalog {
	
		
	public LibraryCatalog() throws IOException {
		
	}
	private List<Book> getBooksFromFiles() throws IOException {
		return null;
	}
	
	private List<User> getUsersFromFiles() throws IOException {
		return null;
	}
	public List<Book> getBookCatalog() {
		return null;
	}
	public List<User> getUsers() {
		return null;
	}
	public void addBook(String title, String author, String genre) {
		return;
	}
	public void removeBook(int id) {
		return;
	}	
	
	public boolean checkOutBook(int id) {
		return true;
	}
	public boolean returnBook(int id) {
		return true;
	}
	
	public boolean getBookAvailability(int id) {
		return true;
	}
	public int bookCount(String title) {
		return 1000;
	}
	public void generateReport() throws IOException {
		
		String output = "\t\t\t\tREPORT\n\n";
		output += "\t\tSUMMARY OF BOOKS\n";
		output += "GENRE\t\t\t\t\t\tAMOUNT\n";
		/*
		 * In this section you will print the amount of books per category.
		 * 
		 * Place in each parenthesis the specified count. 
		 * 
		 * Note this is NOT a fixed number, you have to calculate it because depending on the 
		 * input data we use the numbers will differ.
		 * 
		 * How you do the count is up to you. You can make a method, use the searchForBooks()
		 * function or just do the count right here.
		 */
		output += "Adventure\t\t\t\t\t" + (/*Place here the amount of adventure books*/) + "\n";
		output += "Fiction\t\t\t\t\t\t" + (/*Place here the amount of fiction books*/) + "\n";
		output += "Classics\t\t\t\t\t" + (/*Place here the amount of classics books*/) + "\n";
		output += "Mystery\t\t\t\t\t\t" + (/*Place here the amount of mystery books*/) + "\n";
		output += "Science Fiction\t\t\t\t\t" + (/*Place here the amount of science fiction books*/) + "\n";
		output += "====================================================\n";
		output += "\t\t\tTOTAL AMOUNT OF BOOKS\t" + (/*Place here the total number of books*/) + "\n\n";
		
		/*
		 * This part prints the books that are currently checked out
		 */
		output += "\t\t\tBOOKS CURRENTLY CHECKED OUT\n\n";
		/*
		 * Here you will print each individual book that is checked out.
		 * 
		 * Remember that the book has a toString() method. 
		 * Notice if it was implemented correctly it should print the books in the 
		 * expected format.
		 * 
		 * PLACE CODE HERE
		 */
		
		
		output += "====================================================\n";
		output += "\t\t\tTOTAL AMOUNT OF BOOKS\t" (/*Place here the total number of books that are checked out*/) + "\n\n";
		
		
		/*
		 * Here we will print the users the owe money.
		 */
		output += "\n\n\t\tUSERS THAT OWE BOOK FEES\n\n";
		/*
		 * Here you will print all the users that owe money.
		 * The amount will be calculating taking into account 
		 * all the books that have late fees.
		 * 
		 * For example if user Jane Doe has 3 books and 2 of them have late fees.
		 * Say book 1 has $10 in fees and book 2 has $78 in fees.
		 * 
		 * You would print: Jane Doe\t\t\t\t\t$88.00
		 * 
		 * Notice that we place 5 tabs between the name and fee and 
		 * the fee should have 2 decimal places.
		 * 
		 * PLACE CODE HERE!
		 */

			
		output += "====================================================\n";
		output += "\t\t\t\tTOTAL DUE\t$" + (/*Place here the total amount of money owed to the library.*/) + "\n\n\n";
		output += "\n\n";
		System.out.println(output);// You can use this for testing to see if the report is as expected.
		
		/*
		 * Here we will write to the file.
		 * 
		 * The variable output has all the content we need to write to the report file.
		 * 
		 * PLACE CODE HERE!!
		 */
		
	}
	
	/*
	 * BONUS Methods
	 * 
	 * You are not required to implement these, but they can be useful for
	 * other parts of the project.
	 */
	public List<Book> searchForBook(FilterFunction<Book> func) {
		return null;
	}
	
	public List<User> searchForUsers(FilterFunction<User> func) {
		return null;
	}
	
}
