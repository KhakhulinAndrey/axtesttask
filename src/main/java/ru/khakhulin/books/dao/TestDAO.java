package ru.khakhulin.books.dao;

import ru.khakhulin.books.model.Author;
import ru.khakhulin.books.model.Book;

import java.time.LocalDate;

/**
 * Created by 1 on 20.09.2017.
 */
public class TestDAO {
    public static void main(String[] args) {
        /*BookDAO bookDAO = new BookDAO();
        System.out.println(bookDAO.getAll().toString());

       // bookDAO.delete(Long.valueOf(100004L));


        System.out.println(bookDAO.get(100005L));

        bookDAO.save(new Book(100001L, "ss", "ss", "ss"));
        System.out.println(bookDAO.getAll().toString());*/

        AuthorDAO authorDAO = new AuthorDAO();
        System.out.println(authorDAO.get(100000L));

        //authorDAO.delete(100003L);
        Author author = authorDAO.get(100000L);
        author.setName("NEW1");
        authorDAO.save(author);
        //System.out.println(authorDAO.getAll().toString());
        System.out.println(authorDAO.getBySubject("Pov").toString());


    }
}
