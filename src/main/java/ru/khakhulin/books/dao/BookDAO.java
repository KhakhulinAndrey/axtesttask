package ru.khakhulin.books.dao;

import ru.khakhulin.books.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 20.09.2017.
 */
public class BookDAO {

    Connection connection = DBConnection.getConnetion();

    public Book get(Long id){
        String selectSQL = "SELECT * FROM books WHERE id=?";
        Book book = null;
        try(PreparedStatement statement = connection.prepareStatement(selectSQL);)
        {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                book = convertRowToBook(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;

    }

    public List<Book> getAll(){
        List<Book> books = new ArrayList<>();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books");)
        {
            while (resultSet.next()){
                books.add(convertRowToBook(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public void delete(Long id){
        String deleteSQL = "DELETE FROM books WHERE id=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            System.out.println("Book is deleted!");

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }
    }

    public void save(Book book){
        String updateSQL = "UPDATE books SET name=?, isbn=?, subject=?, auth_id=? WHERE id=?";
        String insertSQL = "INSERT INTO books (auth_id, name, isbn, subject) VALUES(?,?,?,?)";
        if (book.isNew())
        {
            try(PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);) {
                preparedStatement.setLong(1, book.getAuth_id());
                preparedStatement.setString(2, book.getName());
                preparedStatement.setString(3, book.getIsbn());
                preparedStatement.setString(4, book.getSubject());
                preparedStatement.executeUpdate();

                System.out.println("Book is inserted!");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            try(PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);) {
                preparedStatement.setString(1, book.getName());
                preparedStatement.setString(2, book.getIsbn());
                preparedStatement.setString(3, book.getSubject());
                preparedStatement.setLong(4, book.getAuth_id());
                preparedStatement.setLong(5, book.getId());
                preparedStatement.executeUpdate();

                System.out.println("Book is updated!");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Book convertRowToBook(ResultSet resultSet) throws SQLException {

        Long id = resultSet.getLong("id");
        Long auth_id = resultSet.getLong("auth_id");
        String name = resultSet.getString("name");
        String isbn = resultSet.getString("isbn");
        String subject = resultSet.getString("subject");
        Book book = new Book(id, auth_id, name, isbn, subject);

        return book;
    }
}
