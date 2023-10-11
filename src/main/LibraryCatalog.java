package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import javax.xml.catalog.Catalog;

import data_structures.ArrayList;
import data_structures.DoublyLinkedList;
import data_structures.SinglyLinkedList;
import interfaces.FilterFunction;
import interfaces.List;

public class LibraryCatalog {
	
		
	public LibraryCatalog() throws IOException {
		
	}

	List<Book> bookCatalog = getBookCatalog(); // get the initial data from csv bookCatalog
	List<User> userList = getUsers();  // get the initial data from csv User

	private List<Book> getBooksFromFiles() throws IOException {
        List<Book> books = new ArrayList<>();
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader("data/catalog.csv"))) {
            br.readLine(); // skip the header line

            while ((line = br.readLine()) != null) {
                String[] values = line.split(csvSplitBy);

                int bookId = Integer.parseInt(values[0].trim());
                String title = values[1].trim();
                String author = values[2].trim();
                String genre = values[3].trim();
                LocalDate lastCheckOut = LocalDate.parse(values[4].trim());
                boolean isCheckedOut = Boolean.parseBoolean(values[5].trim());

                books.add(new Book(bookId, title, author, genre, lastCheckOut, isCheckedOut));
			}
		} catch (IOException e) {
				e.printStackTrace();
				throw e; 
		}



		return books;
	}
	
	private List<User> getUsersFromFiles() throws IOException {
		List<User> users = new ArrayList<>();
		String csvFile = "data/user.csv"; // Relative Path
		String line;
		String csvSplitBy = ",";

		try(BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			br.readLine(); //skip the first line 

			while ((line = br.readLine()) != null) {
				String[] values = line.split(csvSplitBy);

				int userId = Integer.parseInt(values[0].trim());
				String userName = values[1].trim();
				List<Book> userBooks = new ArrayList<>();

				//add books 

				// check inside the curly braces and verify that is not empty
				if(values.length >2 && !values[2].trim().isEmpty()) {
					String[] bookIds = values[2].trim().replace("{", "").replace("}", "").split("\\s+"); // simplify this
					

					for (String bookId: bookIds){
						for(Book book: bookCatalog){
							if(book.getId() == Integer.parseInt(bookId)){
								userBooks.add(book);
							}
						}
						//userBooks.add(new Book(Integer.parseInt(bookId)));
					}
				} 

				users.add(new User(userId, userName, userBooks));

			}
			
		} catch (IOException e) {
				e.printStackTrace();
				throw e; 
		}



		return users;
	}


	public List<Book> getBookCatalog() {
		try {
			return getBooksFromFiles();
		} catch (IOException e){
			e.printStackTrace();
			return new ArrayList<>();
		}
		
	}

	public List<User> getUsers() {
		try {
			return getUsersFromFiles();
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public void addBook(String title, String author, String genre) {
		int id;
		int catalogSize = getBookCatalog().size();
		LocalDate lastCheckOut = LocalDate.parse("2023-09-15");

		// In case that we violate the initial 50 book, and avoid repeating the ID
		if(catalogSize < 50){
			id = catalogSize + 50;
			return ;
		}

		id = catalogSize + 1;

		bookCatalog.add(new Book(id, title, author, genre, lastCheckOut, false));

		return ;
	}

	public void removeBook(int id) {
		for(Book book: bookCatalog){
			if(book.getId() == id){
				bookCatalog.remove(book);
			}
		}
		return ;
	}	
	
	public boolean checkOutBook(int id) {

		for(Book book: bookCatalog){
			if(book.getId() == id){
				if(!book.isCheckedOut()){
					book.setCheckedOut(true);
					book.setLastCheckOut(LocalDate.parse("2023-09-15"));
					return true;
				}
				if(book.isCheckedOut())
					return false;
			}
		} return false;

	}

	public boolean returnBook(int id) {
		for(Book book: bookCatalog){
			if(book.getId() == id){

				if(book.isCheckedOut()){
					book.setCheckedOut(false);
					return true;
				}
		
				if(!book.isCheckedOut())
					return false;
			}
		}
		return false;
	}
	
	public boolean getBookAvailability(int id) {
		for(Book book: bookCatalog){
			if(book.getId() == id && !(book.isCheckedOut()))
				return true;
		}
		return false;
	}

	public int bookCount(String title) {
		int counter = 0;
		for(Book book: bookCatalog){
			if(book.getTitle().equals(title))
				counter++;
		}
		return counter;
	}

	
	public void generateReport() throws IOException {

		String output = "\t\t\t\tREPORT\n\n";
		output += "\t\tSUMMARY OF BOOKS\n";
		output += "GENRE\t\t\t\t\t\tAMOUNT\n";

		int countAdeventure = 0;
		int countFiction = 0;
		int countClassics = 0;
		int countMystery = 0;
		int countScienceFiction = 0;
		int totalBook = 0;

		for(Book book: bookCatalog){
			if(book.getGenre().equals("Adventure")){
				countAdeventure++;
				totalBook++;
			}
			else if(book.getGenre().equals("Fiction")){
				countFiction++;
				totalBook++;
			}
			else if(book.getGenre().equals("Classics")){
				countClassics++;
				totalBook++;
			}
			else if(book.getGenre().equals("Mystery")){
				countMystery++;
				totalBook++;
			}
			else if(book.getGenre().equals("Science Fiction")){
				countScienceFiction++;
				totalBook++;
			}
			
		}

		output += "Adventure\t\t\t\t\t" + (countAdeventure) + "\n";
		output += "Fiction\t\t\t\t\t\t" + (countFiction) + "\n";
		output += "Classics\t\t\t\t\t" + (countClassics) + "\n";
		output += "Mystery\t\t\t\t\t\t" + (countMystery) + "\n";
		output += "Science Fiction\t\t\t\t\t" + (countScienceFiction) + "\n";
		output += "====================================================\n";
		output += "\t\t\tTOTAL AMOUNT OF BOOKS\t" + (totalBook) + "\n\n";

		
		output += "\t\t\tBOOKS CURRENTLY CHECKED OUT\n\n";
		int bookChecked = 0;
		for(Book book: bookCatalog){
			if(book.isCheckedOut()){
				output += book.toString() + "\n";
				bookChecked++;
			}
		}

//		
		output += "====================================================\n";
		output += "\t\t\tTOTAL AMOUNT OF BOOKS\t" + (bookChecked) + "\n\n";
		
		

		output += "\n\n\t\tUSERS THAT OWE BOOK FEES\n\n";


		float totalDueGeneral = 0;
		float totalDueUser = 0;
		

		for(User user: userList){
			if(!user.getCheckedOutList().isEmpty()){
				for(Book book: user.getCheckedOutList()){
					totalDueUser += book.calculateFees();
				}
				output +=user.getName() +"\t\t\t\t\t"+ totalDueUser + "\n";
				totalDueGeneral += totalDueUser;
				totalDueUser = 0;
			}
		}

		
	

			
		output += "====================================================\n";
		output += "\t\t\t\tTOTAL DUE\t$" + (totalDueGeneral) + "\n\n\n";
		output += "\n\n";
		System.out.println(output);// You can use this for testing to see if the report is as expected.
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter("report/expected_report.txt"))){
			writer.write(output);
			writer.close();
		}catch (IOException e) {
			System.err.println("An IOException occurred: " + e.getMessage());
		}

		
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
