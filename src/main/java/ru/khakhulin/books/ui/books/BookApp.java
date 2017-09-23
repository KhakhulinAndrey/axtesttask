package ru.khakhulin.books.ui.books;

import ru.khakhulin.books.dao.BookDAO;
import ru.khakhulin.books.model.Book;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 1 on 20.09.2017.
 */
public class BookApp extends JFrame {

    private BookApp bookApp;
    private JButton btnAdd, btnEdit, btnRem;
    private JScrollPane scrollPane;
    private JTable table;
    protected BookTableModel model;
    private Long auth_id;

    private BookDAO bookDAO;

    public BookApp(Long auth_id) {
        try {
            bookDAO = new BookDAO();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        this.auth_id = auth_id;
        this.bookApp = this;

        setTitle("Книги");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setSize(500, 500);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setVisible(true);


        scrollPane = new JScrollPane();
        add(scrollPane);

        if (auth_id == null)
            model = new BookTableModel(bookDAO.getAll());
        else model = new BookTableModel(bookDAO.getAllByAuthId(auth_id));
        table = new JTable();
        table.setModel(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //можно выделять только 1 строку
        scrollPane.setViewportView(table);

        btnAdd = new JButton("Добавить");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookEdit authorEdit = new BookEdit(null, bookApp);
                authorEdit.setVisible(true);
            }
        });

        btnEdit = new JButton("Изменить");
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow(); //номер выделенной строки
                if (row == -1) {
                    JOptionPane.showMessageDialog(BookApp.this, "Выберите книгу", "Подсказка", JOptionPane.INFORMATION_MESSAGE);
                }
                BookEdit authorEdit = new BookEdit(model.getBook(row), bookApp);
            }
        });

        btnRem = new JButton("Удалить");
        btnRem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow(); //номер выделенной строки
                if (row == -1) {
                    JOptionPane.showMessageDialog(BookApp.this, "Выделите книгу", "Подсказка",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                Book author = model.getBook(row);

                bookDAO.delete(author.getId());
                model.updateData(bookDAO.getAll()); //обновляем таблицу
            }
        });

        add(btnAdd);
        add(btnEdit);
        add(btnRem);


    }
}
