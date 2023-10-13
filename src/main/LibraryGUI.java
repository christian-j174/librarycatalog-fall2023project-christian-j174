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
        try {
            libraryCatalog = new LibraryCatalog();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading catalog", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        setTitle("Library Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        add(panel, BorderLayout.NORTH);


        JButton addButton = new JButton("Add Book");
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

        JButton removeButton = new JButton("Remove Book");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedId = Integer.parseInt(JOptionPane.showInputDialog("Enter the book ID to remove:"));
                libraryCatalog.removeBook(selectedId);
                updateBookList();
            }
        });
        panel.add(removeButton);

        add(new JScrollPane(bookList), BorderLayout.CENTER);

        updateBookList();
    }

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
