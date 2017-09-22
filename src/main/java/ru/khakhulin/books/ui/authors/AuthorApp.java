package ru.khakhulin.books.ui.authors;

import ru.khakhulin.books.dao.AuthorDAO;
import ru.khakhulin.books.model.Author;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * Created by Admin on 22.09.2017.
 */
public class AuthorApp extends JFrame {

    private JPanel contentPane;
    private JTextField lastNameTextField;
    private JButton btnSearch;
    private JScrollPane scrollPane;
    private JTable table;

    private AuthorDAO authorDAO;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AuthorApp frame = new AuthorApp();
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
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        contentPane.add(panel, BorderLayout.NORTH);

        JLabel lblEnterLastName = new JLabel("Введите фамилию");
        panel.add(lblEnterLastName);

        lastNameTextField = new JTextField();
        panel.add(lastNameTextField);
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

                    // create the model and update the "table"
                    AbstractTableModel model = new AuthorTableModel(authors);

                    table.setModel(model);

					/*
					for (Employee temp : employees) {
						System.out.println(temp);
					}
					*/
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(AuthorApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        panel.add(btnSearch);

        scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        scrollPane.setViewportView(table);
    }
}
