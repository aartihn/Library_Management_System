package ui;

import javax.swing.*;
import java.awt.*;
import controllers.BookController;
import models.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private BookController bookController = new BookController();

    public MainFrame() {
        setTitle("Library Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeUI();
    }

    private void initializeUI() {
        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(e -> addBook());

        JButton viewButton = new JButton("View Books");
        viewButton.addActionListener(e -> viewBooks());

        JButton deleteButton = new JButton("Delete Book");
        deleteButton.addActionListener(e -> deleteBook());

        JPanel panel = new JPanel();
        panel.add(addButton);
        panel.add(viewButton);
        panel.add(deleteButton);
        add(panel, BorderLayout.CENTER);
    }

    private void addBook() {
        String title = JOptionPane.showInputDialog(this, "Enter Book Title");

    // Check if the title dialog was canceled or closed
    if (title == null) {
        return; // Exit the method if the title dialog is canceled
    }

    try {
        String yearStr = JOptionPane.showInputDialog(this, "Enter Publication Year");
        
        // Check if the year dialog was canceled or closed
        if (yearStr == null) {
            return; // Exit the method if the year dialog is canceled
        }
        
        int year = Integer.parseInt(yearStr);
        
        Book book = new Book(bookController.getAllBooks().size() + 1, title, new ArrayList<>(), year, null, new ArrayList<>());
        bookController.addBook(book);

        JOptionPane.showMessageDialog(this, "Book Added Successfully!");
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid Year. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }

    private void viewBooks() {
        StringBuilder bookList = new StringBuilder();
        for (Book book : bookController.getAllBooks()) {
            bookList.append(book.getId()).append(". ").append(book.getTitle()).append(" (").append(book.getYear()).append(")\n");
        }
        JOptionPane.showMessageDialog(this, bookList.toString(), "All Books", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteBook() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Book ID to Delete");

    // Check if the ID dialog was canceled or closed
    if (idStr == null) {
        return; // Exit the method if the ID dialog is canceled or closed
    }

    try {
        int id = Integer.parseInt(idStr);
        bookController.deleteBook(id);
        JOptionPane.showMessageDialog(this, "Book Deleted Successfully!");
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid ID. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
}
