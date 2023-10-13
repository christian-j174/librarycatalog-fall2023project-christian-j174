package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LibraryGUI extends JFrame {
    private LibraryCatalog libraryCatalog;
    private final DefaultListModel<String> bookListModel = new DefaultListModel<>();
    private final JList<String> bookList = new JList<>(bookListModel);

    public LibraryGUI() {
        
        //Create a new book catalog
        try {
            libraryCatalog = new LibraryCatalog();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading catalog", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        
        // Add title, Set background, and dimensions
        setTitle("Westside Public Library’s IT Department");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(70, 130, 180));

        JPanel panel = new JPanel();
        panel.setBackground(new Color(100, 149, 237));
        add(panel, BorderLayout.NORTH);



        // Add Books 
        JButton addButton = new JButton("Add Book");
        addButton.setBackground(new Color(50, 205, 50));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter book title:");
                String author = JOptionPane.showInputDialog("Enter book author:");
                String genre = JOptionPane.showInputDialog("Enter book genre:");
                libraryCatalog.addBook(title, author, genre);
                updateBookList();
            }
        });
        panel.add(addButton);


        // Remove books
        JButton removeButton = new JButton("Remove Book");
        removeButton.setBackground(new Color(220, 20, 60));
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedId = Integer.parseInt(JOptionPane.showInputDialog("Enter the book ID to remove:"));
                libraryCatalog.removeBook(selectedId);
                updateBookList();
            }
        });
        panel.add(removeButton);

//---------------------------------------------------------------------
        // Updates and run the program
        add(new JScrollPane(bookList), BorderLayout.CENTER);
        updateBookList();
    }

	/**
	 * Gets the books from the catalog, and update the bookListModel
	 */
    private void updateBookList() {
        bookListModel.clear();
        bookListModel.addElement("BOOK ID: \tBOOK TITLE");
        for (Book book : libraryCatalog.getBookCatalog()) {
            bookListModel.addElement("#"+book.getId() + ":" + book.getTitle());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LibraryGUI().setVisible(true);
            }
        });
    }
}
