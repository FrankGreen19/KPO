package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.exception.NotFoundException;
import project.model.Article;
import project.model.ArticleType;
import project.model.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticleRepository implements IRestRepository<Article> {

    @Override
    public List<Article> select() throws SQLException {
        Connection connection = DatabaseHandler.getDbConnection();

        PreparedStatement statement = connection.
                prepareStatement("SELECT * FROM article");

        ResultSet resultSet = statement.executeQuery();

        ArrayList<Article> articles = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("article_id");
            int type = resultSet.getInt("article_type_id");
            int author = resultSet.getInt("author_id");
            String header = resultSet.getString("article_header");
            String main = resultSet.getString("article_main");
            Date date = resultSet.getDate("article_date");
            boolean priority  = resultSet.getBoolean("article_priority");

            articles.add(new Article(id, type, author, header, main, date, priority));
        }

        connection.close();
        return articles;
    }

    @Override
    public Article select(Integer id) throws SQLException {
        Connection connection = DatabaseHandler.getDbConnection();
        Article article = null;

        PreparedStatement statement = connection.
                prepareStatement("SELECT * FROM article WHERE article_id = cast(? as integer)");
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int type = resultSet.getInt("article_type_id");
            int author = resultSet.getInt("author_id");
            String header = resultSet.getString("article_header");
            String main = resultSet.getString("article_main");
            Date date = resultSet.getDate("article_date");
            boolean priority  = resultSet.getBoolean("article_priority");

            article = new Article(id, type, author, header, main, date, priority);
        }

        connection.close();
        return article;
    }

    @Override
    public Article insert(Article entity) throws SQLException {

        ArticleTypeRepository articleTypeRepository = new ArticleTypeRepository();
        AuthorRepository authorRepository = new AuthorRepository();

        if (articleTypeRepository.select(entity.getArticleTypeId()) != null) {
            if (authorRepository.select(entity.getAuthorId()) != null) {

                Connection connection = DatabaseHandler.getDbConnection();

                PreparedStatement statement = connection.
                        prepareStatement("INSERT INTO article(article_type_id, author_id, article_header, article_main, article_date, article_priority) VALUES " +
                                "(cast(? as integer ), cast(? as integer), cast(? as VARCHAR), cast(? as VARCHAR), cast(? as date), cast(? as boolean))");
                statement.setInt(1, entity.getArticleTypeId());
                statement.setInt(2, entity.getAuthorId());
                statement.setString(3, entity.getHeader());
                statement.setString(4, entity.getMain());
                statement.setString(5, String.valueOf(entity.getDate()));
                statement.setBoolean(6, entity.isPriority());

                statement.executeUpdate();

                connection.close();
                return entity;
            } else
                throw new NotFoundException();
        } else
            throw new NotFoundException();
    }

    @Override
    public Article update(Integer id, Article entity) throws SQLException {
        Connection connection = DatabaseHandler.getDbConnection();

        PreparedStatement statement = connection.
                prepareStatement("UPDATE article SET " +
                        "article_type_id = cast(? as integer), " +
                        "author_id = cast(? as integer)," +
                        "article_header = cast(? as varchar), " +
                        "article_main = cast(? as varchar), " +
                        "article_date = cast(? as date), " +
                        "article_priority = cast(? as boolean) " +
                        "WHERE article_id = cast(? as integer)");
        statement.setInt(1, entity.getArticleTypeId());
        statement.setInt(2, entity.getAuthorId());
        statement.setString(3, entity.getHeader());
        statement.setString(4, entity.getMain());
        statement.setString(5, String.valueOf(entity.getDate()));
        statement.setBoolean(6, entity.isPriority());
        statement.setInt(7, id);

        System.out.println(statement.execute());

        connection.close();
        return entity;
    }

    @Override
    public Article delete(Integer id) throws SQLException {
        Connection connection = DatabaseHandler.getDbConnection();

        Article article = this.select(id);

        PreparedStatement statement = connection.
                prepareStatement("DELETE FROM article WHERE article_id = cast(? as integer)");
        statement.setInt(1, id);

        statement.execute();

        connection.close();
        return article;
    }
}
