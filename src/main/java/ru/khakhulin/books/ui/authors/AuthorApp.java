package ru.khakhulin.books.ui.authors;

import ru.khakhulin.books.dao.AuthorDAO;
import ru.khakhulin.books.model.Author;

import javax.swing.*;

import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * Created by Admin on 22.09.2017.
 */
public class AuthorApp extends JFrame {

    private JTextField lastNameTextField;
    private JButton btnSearch;
    private JButton allBooks;
    private JScrollPane scrollPane;
    private JTable table;

    private AuthorDAO authorDAO;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AuthorApp frame = new AuthorApp();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AuthorApp(){
        try {
            authorDAO = new AuthorDAO();
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
        }

        setTitle("Авторы");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setSize(500, 400);
        setLayout(new FlowLayout());





        JLabel lblEnterLastName = new JLabel("Введите фамилию");
        add(lblEnterLastName);

        lastNameTextField = new JTextField();
        add(lastNameTextField);
        lastNameTextField.setColumns(10);

        btnSearch = new JButton("Поиск");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String surname = lastNameTextField.getText();
                    List<Author> authors = null;

                    if (surname != null && surname.trim().length() > 0) {
                        authors = authorDAO.getBySurname(surname);
                    } else {
                        authors = authorDAO.getAll();
                    }

                    AbstractTableModel model = new AuthorTableModel(authors);
                    table.setModel(model);

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(AuthorApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        add(btnSearch);

        allBooks = new JButton("Все книги");
        add(allBooks);

        scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);

        AbstractTableModel model = new AuthorTableModel(authorDAO.getAll());
        table = new JTable();
        table.setModel(model);
        scrollPane.setViewportView(table);

    }
}
