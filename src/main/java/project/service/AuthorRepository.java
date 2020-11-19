package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.exception.NotFoundException;
import project.model.Article;
import project.model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorRepository implements IRestRepository<Author>{

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Author> select() throws SQLException {
        Connection connection = DatabaseHandler.getDbConnection();

        PreparedStatement statement = connection.
                prepareStatement("SELECT * FROM author");

        ResultSet resultSet = statement.executeQuery();

        ArrayList<Author> authors = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("author_id");
            String name = resultSet.getString("author_name");
            String surname = resultSet.getString("author_surname");
            int articles = resultSet.getInt("author_article_num");

            authors.add(new Author(id, name, surname, articles));
        }

        connection.close();
        return authors;
    }

    @Override
    public Author select(Integer id) throws SQLException {
        Connection connection = DatabaseHandler.getDbConnection();
        Author author = null;

        PreparedStatement statement = connection.
                prepareStatement("SELECT * FROM author WHERE author_id = cast(? as integer)");
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            id = resultSet.getInt("author_id");
            String name = resultSet.getString("author_name");
            String surname = resultSet.getString("author_surname");
            int articles = resultSet.getInt("author_article_num");

            author = new Author(id, name, surname, articles);
        }

        connection.close();
        return author;
    }

    @Override
    public Author insert(Author entity) throws SQLException {
        Connection connection = DatabaseHandler.getDbConnection();

        UserRepository userRepository = new UserRepository();

        if (userRepository.select(entity.getId()) != null) {
            if (this.select(entity.getId()) == null) {
                PreparedStatement statement = connection.
                        prepareStatement("INSERT INTO author(author_id, author_name, author_surname, author_article_num) VALUES " +
                                "(cast(? as integer ), cast(? as VARCHAR), cast(? as VARCHAR), cast(? as integer)) " +
                                "RETURNING author_id, author_name, author_surname, author_article_num");
                statement.setInt(1, entity.getId());
                statement.setString(2, entity.getName());
                statement.setString(3, entity.getSurname());
                statement.setInt(4, entity.getArticle_num());

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    entity.setId(resultSet.getInt("author_id"));
                    entity.setName(resultSet.getString("author_name"));
                    entity.setSurname(resultSet.getString("author_surname"));
                    entity.setArticle_num(resultSet.getInt("author_article_num"));
                }

                connection.close();
                return entity;
            } else
                throw new NotFoundException();
        } else
            throw new NotFoundException();

    }

    @Override
    public Author update(Integer id, Author entity) throws SQLException {
        Connection connection = DatabaseHandler.getDbConnection();

        PreparedStatement statement = connection.
                prepareStatement("UPDATE author SET " +
                        "author_name = cast(? as varchar), " +
                        "author_surname = cast(? as varchar)," +
                        "author_article_num = cast(? as integer) " +
                        "WHERE author_id = cast(? as integer ) RETURNING " +
                        "author_name, author_surname, author_article_num");
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getSurname());
        statement.setInt(3, entity.getArticle_num());
        statement.setInt(4, id);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            entity.setName(resultSet.getString("author_name"));
            entity.setSurname(resultSet.getString("author_surname"));
            entity.setArticle_num(resultSet.getInt("author_article_num"));
        }

        connection.close();
        return entity;
    }

    @Override
    public Author delete(Integer id) throws SQLException {
        Connection connection = DatabaseHandler.getDbConnection();

        Author entity = new Author(id);

        PreparedStatement statement = connection.
                prepareStatement("DELETE FROM author WHERE author_id = cast(? as integer) RETURNING " +
                        "author_name, author_surname, author_article_num");
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            entity.setName(resultSet.getString("author_name"));
            entity.setSurname(resultSet.getString("author_surname"));
            entity.setArticle_num(resultSet.getInt("author_article_num"));
        }

        connection.close();
        return entity;
    }

}
