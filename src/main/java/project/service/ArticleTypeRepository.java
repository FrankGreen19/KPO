package project.service;

import org.springframework.stereotype.Service;
import project.exception.NotFoundException;
import project.model.ArticleType;
import project.model.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleTypeRepository implements IRestRepository<ArticleType> {
    @Override
    public List<ArticleType> select() throws SQLException {
        Connection connection = DatabaseHandler.getDbConnection();

        PreparedStatement statement = connection.
                prepareStatement("SELECT * FROM article_type");

        ResultSet resultSet = statement.executeQuery();

        ArrayList<ArticleType> articleTypes = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("article_type_id");
            String name = resultSet.getString("article_type_name");

            articleTypes.add(new ArticleType(id, name));
        }

        connection.close();
        return articleTypes;
    }

    @Override
    public ArticleType select(Integer id) throws SQLException {
        Connection connection = DatabaseHandler.getDbConnection();
        ArticleType articleType = null;

        PreparedStatement statement = connection.
                prepareStatement("SELECT * FROM article_type WHERE article_type_id = cast(? as integer)");
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            id = resultSet.getInt("article_type_id");
            String name = resultSet.getString("article_type_name");

            articleType = new ArticleType(id, name);
        }

        connection.close();
        return articleType;
    }

    @Override
    public ArticleType insert(ArticleType entity) throws SQLException {
        Connection connection = DatabaseHandler.getDbConnection();

        PreparedStatement statement = connection.
                prepareStatement("INSERT INTO article_type(article_type_name) VALUES " +
                        "(cast(? as VARCHAR))");
        statement.setString(1, entity.getName());
        System.out.println(statement.executeUpdate());

        connection.close();
        return entity;
    }

    @Override
    public ArticleType update(Integer id, ArticleType entity) throws SQLException {
        Connection connection = DatabaseHandler.getDbConnection();

        PreparedStatement statement = connection.
                prepareStatement("UPDATE article_type SET " +
                        "article_type_name = cast(? as varchar)" +
                        "WHERE article_type_id = cast(? as integer)");
        statement.setString(1, entity.getName());
        statement.setInt(2, id);

        System.out.println(statement.execute());

        connection.close();
        return entity;
    }

    @Override
    public ArticleType delete(Integer id) throws SQLException {
        Connection connection = DatabaseHandler.getDbConnection();

        ArticleType articleType = this.select(id);

        PreparedStatement statement = connection.
                prepareStatement("DELETE FROM article_type WHERE article_type_id = cast(? as integer)");
        statement.setInt(1, id);

        statement.execute();

        connection.close();
        return articleType;
    }
}
