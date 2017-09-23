package ru.khakhulin.books.ui.books;

import ru.khakhulin.books.dao.BookDAO;
import ru.khakhulin.books.model.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 1 on 23.09.2017.
 */
public class BookEdit extends JFrame {
    private JTextField nameTF,isbnTF, subjectTF;
    private JComboBox<Long> authCB;
    private JButton btnOk, btnCancel;
    private Book book;
    private BookApp bookApp;

    private BookDAO bookDAO;

    public BookEdit(Book book, BookApp bookApp)
    {
        this.book = book;
        this.bookApp = bookApp;
        try {
            bookDAO = new BookDAO();
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
        }

        setTitle("Книга");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setSize(200, 200);
        setLayout(new FlowLayout());
        setVisible(true);

        JLabel lblName = new JLabel("Книга");
        JLabel lblAuth = new JLabel("Автор");
        JLabel lblIsbn = new JLabel("ISBN");
        JLabel lblSub = new JLabel("Тематика");

        nameTF = new JTextField();
        nameTF.setColumns(10);
        authCB = new JComboBox<Long>(bookDAO.getAllAuthId().toArray(new Long[0]));
        isbnTF = new JTextField();
        isbnTF.setColumns(10);
        subjectTF = new JTextField();
        subjectTF.setColumns(10);

        if (book != null) {
            nameTF.setText(book.getName());
            isbnTF.setText(book.getIsbn());
            subjectTF.setText(book.getSubject());
            authCB.setSelectedItem(book.getAuth_id());
        }



        add(lblName);
        add(nameTF);
        add(lblAuth);
        add(authCB);
        add(lblIsbn);
        add(isbnTF);
        add(lblSub);
        add(subjectTF);

        btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTF.getText();
                Long authorID = authCB.getItemAt(authCB.getSelectedIndex());
                String isbn = isbnTF.getText();
                String subject = subjectTF.getText();


                if (book == null)//добавление новой книги
                    bookDAO.save(new Book(authorID, name, isbn, subject));
                    //редактирование существующей
                else {
                    book.setName(name);
                    book.setAuth_id(authorID);
                    book.setIsbn(isbn);
                    book.setSubject(subject);
                    bookDAO.save(book);
                }

                bookApp.model.updateData(bookDAO.getAll());//обновляем таблицу

                setVisible(false); //закрываем окно редактирования
                dispose();
            }
        });

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
        add(btnOk);
        add(btnCancel);
    }



}
