package ru.khakhulin.books.model;

/**
 * Created by 1 on 20.09.2017.
 */
public class Book {
    private Long id;
    private Long auth_id;
    private String name;
    private String isbn;
    private String subject;

    public Book(Long id, Long auth_id, String name, String isbn, String subject) {
        this.id = id;
        this.auth_id = auth_id;
        this.name = name;
        this.isbn = isbn;
        this.subject = subject;
    }

    public Book(Long auth_id, String name, String isbn, String subject) {
        this(null, auth_id, name, isbn, subject);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(Long auth_id) {
        this.auth_id = auth_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isNew(){
        return this.getId() == null;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", auth_id=" + auth_id +
                ", name='" + name + '\'' +
                ", isbn='" + isbn + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
