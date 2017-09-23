package ru.khakhulin.books.ui.authors;

import ru.khakhulin.books.dao.AuthorDAO;
import ru.khakhulin.books.model.Author;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;


/**
 * Created by 1 on 23.09.2017.
 */
public class AuthorEdit extends JFrame {
    private JTextField nameTF, patronymicTF, surnameTF;
    private JFormattedTextField birthdayTF;
    private JButton btnOk, btnCancel;
    private Author author;
    private AuthorApp authorApp;

    private AuthorDAO authorDAO;

    public AuthorEdit(Author author, AuthorApp authorApp) {
        this.author = author;
        this.authorApp = authorApp;
        try {
            authorDAO = new AuthorDAO();
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
        }

        setTitle("Авторы");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setSize(200, 200);
        setLayout(new FlowLayout());
        setVisible(true);

        JLabel lblName = new JLabel("Имя");
        JLabel lblPart = new JLabel("Фамилия");
        JLabel lblSurn = new JLabel("Отчество");
        JLabel lblBirth = new JLabel("Дата рождения");

        nameTF = new JTextField();
        nameTF.setColumns(10);
        patronymicTF = new JTextField();
        patronymicTF.setColumns(10);
        surnameTF = new JTextField();
        surnameTF.setColumns(10);

        MaskFormatter mask = null; //маска ввода даты
        try {
            mask = new MaskFormatter("####-##-##");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        birthdayTF = new JFormattedTextField(mask);
        birthdayTF.setColumns(10);
        if (author != null) {
            nameTF.setText(author.getName());
            patronymicTF.setText(author.getPatronymic());
            surnameTF.setText(author.getSurname());
            birthdayTF.setText(author.getBirthday().toString());
        }

        add(lblName);
        add(nameTF);
        add(lblPart);
        add(patronymicTF);
        add(lblSurn);
        add(surnameTF);
        add(lblBirth);
        add(birthdayTF);

        btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTF.getText();
                String patronymic = patronymicTF.getText();
                String surname = surnameTF.getText();
                LocalDate birthday = null;
                try {
                    birthday = LocalDate.parse(birthdayTF.getText());
                } catch (DateTimeException e2) {
                    JOptionPane.showMessageDialog(AuthorEdit.this, "Неверный формат даты", "Подсказка",
                            JOptionPane.INFORMATION_MESSAGE);
                }

                if (author == null)//добавление нового автора
                    authorDAO.save(new Author(name, patronymic, surname, birthday));
                //редактирование существующего
                else {
                    author.setName(name);
                    author.setPatronymic(patronymic);
                    author.setSurname(surname);
                    author.setBirthday(birthday);
                    authorDAO.save(author);
                }

                authorApp.model.updateData(authorDAO.getAll());//обновляем таблицу

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
