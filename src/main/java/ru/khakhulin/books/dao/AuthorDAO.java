package ru.khakhulin.books.dao;

import ru.khakhulin.books.model.Author;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 20.09.2017.
 */
public class AuthorDAO {

    Connection connection = DBConnection.getConnetion();

    public Author get(Long id) {
        String selectSQL = "SELECT * FROM authors WHERE id=?";
        Author author = null;
        try (PreparedStatement statement = connection.prepareStatement(selectSQL);) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String patronymic = resultSet.getString("patronymic");
                String surname = resultSet.getString("surname");
                Date birthday = resultSet.getDate("birthday");
                author = new Author(id, name, patronymic, surname, birthday.toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

        public List<Author> getAll(){
            List<Author> authors = new ArrayList<>();
            try(Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM authors ORDER BY birthday");)
            {
                while (resultSet.next()){
                    Long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String patronymic = resultSet.getString("patronymic");
                    String surname = resultSet.getString("surname");
                    Date birthday = resultSet.getDate("birthday");
                    authors.add(new Author(id, name, patronymic, surname, birthday.toLocalDate()));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return authors;
        }

    public void delete(Long id){
        String deleteSQL = "DELETE FROM authors WHERE id=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            System.out.println("Author is deleted!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void save(Author author){
        String updateSQL = "UPDATE authors SET name=?, patronymic=?, surname=?, birthday=? WHERE id=?";
        String insertSQL = "INSERT INTO authors (name, patronymic, surname, birthday) VALUES(?,?,?,?)";
        if (author.isNew())
        {
            try(PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);) {
                preparedStatement.setString(1, author.getName());
                preparedStatement.setString(2, author.getPatronymic());
                preparedStatement.setString(3, author.getSurname());
                preparedStatement.setDate(4, Date.valueOf(author.getBirthday()));
                preparedStatement.executeUpdate();

                System.out.println("Author is inserted!");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            try(PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);) {
                preparedStatement.setString(1, author.getName());
                preparedStatement.setString(2, author.getPatronymic());
                preparedStatement.setString(3, author.getSurname());
                preparedStatement.setDate(4, Date.valueOf(author.getBirthday()));
                preparedStatement.setLong(5, author.getId());
                preparedStatement.executeUpdate();

                System.out.println("Author is updated!");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }


}
