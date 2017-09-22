package ru.khakhulin.books.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by 1 on 20.09.2017.
 */
public class Author {
    private Long id;
    private String name;
    private String patronymic;
    private String surname;
    private LocalDate birthday;
    private List<Book> books;

    public Author(Long id, String name, String patronymic, String surname, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
        this.birthday = birthday;
    }
    public Author(String name, String patronymic, String surname, LocalDate birthday) {
        this(null, name, patronymic, surname, birthday);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public boolean isNew(){return this.getId() == null;}

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
