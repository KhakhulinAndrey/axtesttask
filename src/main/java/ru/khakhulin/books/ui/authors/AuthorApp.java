package ru.khakhulin.books.ui.authors;

import ru.khakhulin.books.dao.AuthorDAO;
import ru.khakhulin.books.model.Author;
import ru.khakhulin.books.ui.books.BookApp;

import javax.swing.*;

import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
/**
 * Created by Admin on 22.09.2017.
 */
public class AuthorApp extends JFrame {

    private AuthorApp authorApp;
    private JTextField lastNameTextField, subjectTextField;
    private JButton btnSearch, btnSearch2;
    private JButton allBooks, athBooks, btnAdd, btnEdit, btnRem;
    private JScrollPane scrollPane;
    private JTable table;
    protected AuthorTableModel model;

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
        this.authorApp = this;

        setTitle("Авторы");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setSize(600, 550);
        setLayout(new FlowLayout());

        JLabel lblEnterLastName = new JLabel("Фамилия");
        add(lblEnterLastName);

        lastNameTextField = new JTextField();
        lastNameTextField.setColumns(10);
        add(lastNameTextField);


        btnSearch = new JButton("Поиск"); //по фамилии
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String surname = lastNameTextField.getText();
                    List<Author> authors = null;

                    if (surname != null && surname.trim().length() > 0) {
                        model.updateData(authorDAO.getBySurname(surname));
                    } else {
                        model.updateData(authorDAO.getAll());
                    }
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(AuthorApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
                }


            }
        });
        add(btnSearch);

        JLabel lblEnterSubject = new JLabel("Тематика книги");
        add(lblEnterSubject);

        subjectTextField = new JTextField();
        subjectTextField.setColumns(10);
        add(subjectTextField);

        btnSearch2 = new JButton("Поиск");//по тематике книг
        btnSearch2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String subject = subjectTextField.getText();
                    List<Author> authors = null;

                    if (subject != null && subject.trim().length() > 0) {
                        model.updateData(authorDAO.getBySubject(subject));
                    } else {
                        model.updateData(authorDAO.getAll());
                    }

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(AuthorApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        add(btnSearch2);

        scrollPane = new JScrollPane();
        add(scrollPane);

        model = new AuthorTableModel(authorDAO.getAll());
        table = new JTable();
        table.setModel(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //можно выделять только 1 строку

        scrollPane.setViewportView(table);

        allBooks = new JButton("Все книги");
        allBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookApp bookApp = new BookApp(null);
                bookApp.setVisible(true) ;
            }
        });
        add(allBooks);

        athBooks = new JButton("Книги автора");
        athBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow(); //номер выделенной строки
                if (row == -1){
                    JOptionPane.showMessageDialog(AuthorApp.this, "Выберите автора", "Подсказка",
                            JOptionPane.INFORMATION_MESSAGE);
                };
                BookApp bookApp = new BookApp(model.getAuthor(row).getId());
                bookApp.setVisible(true) ;
            }
        });
        add(athBooks);

        btnAdd = new JButton("Добавить");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AuthorEdit authorEdit = new AuthorEdit(null, authorApp);
                authorEdit.setVisible(true);
            }
        });
        btnEdit = new JButton("Изменить");
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow(); //номер выделенной строки
                if (row == -1){
                    JOptionPane.showMessageDialog(AuthorApp.this, "Выделите автора", "Подсказка", JOptionPane.INFORMATION_MESSAGE);
                }
                AuthorEdit authorEdit = new AuthorEdit(model.getAuthor(row), authorApp);
            }
        });

        btnRem = new JButton("Удалить");
        btnRem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow(); //номер выделенной строки
                if (row == -1){
                    JOptionPane.showMessageDialog(AuthorApp.this, "Выделите автора", "Подсказка",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                Author author = model.getAuthor(row);
                try {
                    authorDAO.delete(author.getId());
                }
                catch (SQLException sqlE){

                    JOptionPane.showMessageDialog(AuthorApp.this, "Запрещено удалять автора с книгами", "Подсказка",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                model.updateData(authorDAO.getAll()); //обновляем таблицу
            }
        });
        add(btnAdd);
        add(btnEdit);
        add(btnRem);

    }
}
