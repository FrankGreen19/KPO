package project.service;

import org.springframework.stereotype.Service;
import project.exception.NotFoundException;
import project.model.Article;
import project.model.Subscribe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SubscribeRepository implements IRestRepository<Subscribe> {

    @Override
    public List<Subscribe> select() throws SQLException {
        Connection connection = DatabaseHandler.getDbConnection();

        PreparedStatement statement = connection.
                prepareStatement("SELECT * FROM subscribe");

        ResultSet resultSet = statement.executeQuery();

        ArrayList<Subscribe> subscribes = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("subscribe_id");
            int userId = resultSet.getInt("user_id");
            int typeId = resultSet.getInt("subscribe_type_id");
            Date date = resultSet.getDate("subscribe_date_begin");

            subscribes.add(new Subscribe(id, userId, typeId, date));
        }

        connection.close();
        return subscribes;
    }

    @Override
    public Subscribe select(Integer id) throws SQLException {
        Connection connection = DatabaseHandler.getDbConnection();
        Subscribe subscribe = null;

        PreparedStatement statement = connection.
                prepareStatement("SELECT * FROM subscribe WHERE subscribe_id = cast(? as integer)");
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int userId = resultSet.getInt("user_id");
            int typeId = resultSet.getInt("subscribe_type_id");
            Date date = resultSet.getDate("subscribe_date_begin");

            subscribe = new Subscribe(id, userId, typeId, date);
        }

        connection.close();
        return subscribe;
    }

    @Override
    public Subscribe insert(Subscribe entity) throws SQLException {

        ArticleTypeRepository articleTypeRepository = new ArticleTypeRepository();
        UserRepository userRepository = new UserRepository();

        if (articleTypeRepository.select(entity.getTypeId()) != null) {
            if (userRepository.select(entity.getUserId()) != null) {
                Connection connection = DatabaseHandler.getDbConnection();

                PreparedStatement statement = connection.
                        prepareStatement("INSERT INTO subscribe(user_id, subscribe_type_id, subscribe_date_begin) VALUES " +
                                "(cast(? as integer ), cast(? as integer), cast(? as date))");
                statement.setInt(1, entity.getUserId());
                statement.setInt(2, entity.getTypeId());
                statement.setString(3, String.valueOf(entity.getSubDateBegin()));

                statement.executeUpdate();

                connection.close();
                return entity;
            } else
                throw new NotFoundException();
        } else
            throw new NotFoundException();
    }

    @Override
    public Subscribe update(Integer id, Subscribe entity) throws SQLException {
        Connection connection = DatabaseHandler.getDbConnection();

        PreparedStatement statement = connection.
                prepareStatement("UPDATE subscribe SET " +
                        "subscribe_type_id = cast(? as integer), " +
                        "user_id = cast(? as integer)," +
                        "subscribe_date_begin = cast(? as date) " +
                        "WHERE subscribe_id = cast(? as integer)");
        statement.setInt(1, entity.getTypeId());
        statement.setInt(2, entity.getUserId());
        statement.setString(3, String.valueOf(entity.getSubDateBegin()));
        statement.setInt(4, id);

        System.out.println(statement.execute());

        connection.close();
        return entity;
    }

    @Override
    public Subscribe delete(Integer id) throws SQLException {
        Connection connection = DatabaseHandler.getDbConnection();

        Subscribe subscribe = this.select(id);

        PreparedStatement statement = connection.
                prepareStatement("DELETE FROM subscribe WHERE subscribe_id = cast(? as integer)");
        statement.setInt(1, id);

        statement.execute();

        connection.close();
        return subscribe;
    }
}
