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

/**
 * Reperesents the catalog of the library Westside Public Library’s IT Department.
 * Its purpose is to maintaining records of books and users.
 */
public class LibraryCatalog {
	
	/**
	 * Construcr a new library Catalog, initializing books and users
	 * records from the initial data provided on the format csv
	 * @throws IOException if there is an issue reading the data files
	 */
	public LibraryCatalog() throws IOException {
		
	}

	
	List<Book> bookCatalog = getBooksFromFiles(); // get the initial data from csv bookCatalog
	List<User> userList = getUsersFromFiles();  // get the initial data from csv User


	/**
	 * Reads and returns the list of books from a csv file
	 * @return the list of books
	 * @throws IOException if there any isue reading the file
	 */
	private List<Book> getBooksFromFiles() throws IOException {
        List<Book> books = new ArrayList<>();
        String line;
        String csvSplitBy = ",";
		String csvFile = "data/catalog.csv"; // Relative Path


        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
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
	

    /**
     * Reads and returns the list of users from a csv file.
     *
     * @return the list of users
     * @throws IOException if there is an issue reading the csv file
     */
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


				// check inside the curly braces and verify that is not empty
				if(values.length >2 && !values[2].trim().isEmpty()) {
					String[] bookIds = values[2].trim().replace("{", "").replace("}", "").split("\\s+"); // simplify this
					

					for (String bookId: bookIds){
						for(Book book: bookCatalog){
							if(book.getId() == Integer.parseInt(bookId)){
								userBooks.add(book);
							}
						}
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


	/**
     * Returns the list of books in the catalog.
     *
     * @return the book catalog
     */
	public List<Book> getBookCatalog() {
		return bookCatalog;	
	}


    /**
     * Returns the list of users.
     *
     * @return the user list
     */
	public List<User> getUsers() {
		return userList;
	}


    /**
     * Adds a new book to the catalog with the given title, author, and genre.
     *
     * @param title  the title of the book
     * @param author the author of the book
     * @param genre  the genre of the book
     */
	public void addBook(String title, String author, String genre) {
		int id;
		int catalogSize = bookCatalog.size(); // fix this 
		LocalDate lastCheckOut = LocalDate.parse("2023-09-15");
		id = catalogSize + 1;

		bookCatalog.add(new Book(id, title, author, genre, lastCheckOut, false));

		return ;
	}


    /**
     * Removes a book from the catalog by its ID.
     *
     * @param id the ID of the book to be removed
     */
	public void removeBook(int id) {
		for(Book book: bookCatalog){
			if(book.getId() == id){
				bookCatalog.remove(book);
			}
		}
		return ;
	}	
	

    /**
     * Attempts to check out a book by its ID.
     *
     * @param id the ID of the book to be checked out
     * @return true if the book was successfully checked out, false otherwise
     */
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

	/**
	 * 
	 * @param id of the book to be returned to the library
	 * @return true  if it was successfully returned, false if wasn't checked out or the book doesn't exist.
	 */
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
	

	/**
	 * 
	 * @param id of the book to verify availability
	 * @return returns whether the book of the given id is available for check-out.
	 */
	public boolean getBookAvailability(int id) {
		for(Book book: bookCatalog){
			if(book.getId() == id && !(book.isCheckedOut()))
				return true;
		}
		return false;
	}

	/**
	 * 
	 * @param title of the book 
	 * @return how many books of the same title are present in the catalog.
	 */
	public int bookCount(String title) {
		int counter = 0;
		for(Book book: bookCatalog){
			if(book.getTitle().equals(title))
				counter++;
		}
		return counter;
	}


	/**
	 * 
	 * @param L list of books to search for an specific id
	 * @param id of the book to search
	 * @return the book with the specific id, else return null
	 */
	 Book findBookById(List<Book> L, int id) {
		for(Book b: L) {
			if(b.getId() == id)
				return b;
		}
		return null;
	}

    /**
     * Generates a report(.txt) of the current state of the library
	 * That includes:
	 * 1.Summary of Books
	 * 2.Books currently checked out
	 * 3.Users that owe book fees
     *
     * @throws IOException if there is an issue writing the report to a file
     */
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
			if(book.getGenre().equalsIgnoreCase("Adventure")){
				countAdeventure++;
				totalBook++;
			}
			else if(book.getGenre().equalsIgnoreCase("Fiction")){
				countFiction++;
				totalBook++;
			}
			else if(book.getGenre().equalsIgnoreCase("Classics")){
				countClassics++;
				totalBook++;
			}
			else if(book.getGenre().equalsIgnoreCase("Mystery")){
				countMystery++;
				totalBook++;
			}
			else if(book.getGenre().equalsIgnoreCase("Science Fiction")){
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
				output +=user.getName() +"\t\t\t\t\t$"+String.format("%.2f", totalDueUser) + "\n";
				totalDueGeneral += totalDueUser;
				totalDueUser = 0;
			}
		}

		
	

			
		output += "====================================================\n";
		output += "\t\t\t\tTOTAL DUE\t$" + String.format("%.2f", totalDueGeneral) + "\n\n\n";
		output += "\n\n";

	//	output += "My Own Test\n";




		System.out.println(output);
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter("report/report.txt"))){
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
