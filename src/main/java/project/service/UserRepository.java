package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserRepository implements IRestRepository<User>{

    @Override
    public List<User> select() throws SQLException {
        Connection connection = DatabaseHandler.getDbConnection();

        PreparedStatement statement = connection.
                prepareStatement("SELECT * FROM usr");

        ResultSet resultSet = statement.executeQuery();

        ArrayList<User> users = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("user_id");
            String email = resultSet.getString("user_email");
            String password = resultSet.getString("user_password");
            String phone = resultSet.getString("user_phone");
            boolean agreement = resultSet.getBoolean("user_agreement");

            users.add(new User(id, email, password, phone, agreement));
        }

        connection.close();
        return users;
    }

    @Override
    public User select(Integer id) throws SQLException {
        Connection connection = DatabaseHandler.getDbConnection();
        User user = null;

        PreparedStatement statement = connection.
                prepareStatement("SELECT * FROM usr WHERE user_id = cast(? as integer)");
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();


        while (resultSet.next()) {
            id = resultSet.getInt("user_id");
            String email = resultSet.getString("user_email");
            String password = resultSet.getString("user_password");
            String phone = resultSet.getString("user_phone");
            boolean agreement = resultSet.getBoolean("user_agreement");

            user = new User(id, email, password, phone, agreement);
        }

        connection.close();

        return user;
    }

    @Override
    public User insert(User entity) throws SQLException {
        Connection connection = DatabaseHandler.getDbConnection();

        PreparedStatement statement = connection.
                prepareStatement("INSERT INTO usr(user_email, user_password, user_phone, user_agreement) VALUES " +
                        "(cast(? as VARCHAR), cast(? as VARCHAR), cast(? as VARCHAR), cast(? as boolean))" +
                        "RETURNING user_id, user_email, user_password, user_phone, user_agreement");
        statement.setString(1, entity.getEmail());
        statement.setString(2, entity.getPassword());
        statement.setString(3, entity.getPhone());
        statement.setBoolean(4, entity.isAgreement());

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            entity.setId(resultSet.getInt("user_id"));
            entity.setEmail(resultSet.getString("user_email"));
            entity.setPassword(resultSet.getString("user_password"));
            entity.setPhone(resultSet.getString("user_phone"));
            entity.setAgreement(resultSet.getBoolean("user_agreement"));
        }

        connection.close();
        return entity;
    }

    @Override
    public User update(Integer id, User entity) throws SQLException {
        Connection connection = DatabaseHandler.getDbConnection();

        PreparedStatement statement = connection.
                prepareStatement("UPDATE usr SET " +
                        "user_email = cast(? as varchar), " +
                        "user_password = cast(? as varchar)," +
                        "user_phone = cast(? as varchar)," +
                        "user_agreement = cast(? as boolean)" +
                        "WHERE user_id = cast(? as integer)" +
                        "RETURNING user_email, user_password, user_phone, user_agreement");
        statement.setString(1, entity.getEmail());
        statement.setString(2, entity.getPassword());
        statement.setString(3, entity.getPhone());
        statement.setBoolean(4, entity.isAgreement());
        statement.setInt(5, id);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            entity.setEmail(resultSet.getString("user_email"));
            entity.setPassword(resultSet.getString("user_password"));
            entity.setPhone(resultSet.getString("user_phone"));
            entity.setAgreement(resultSet.getBoolean("user_agreement"));
        }

        connection.close();
        return entity;
    }

    @Override
    public User delete(Integer id) throws SQLException {
        Connection connection = DatabaseHandler.getDbConnection();

        PreparedStatement statement = connection.
                prepareStatement("DELETE FROM usr WHERE user_id = cast(? as integer) RETURNING " +
                        "RETURNING user_email, user_password, user_phone, user_agreement");
        statement.setInt(1, id);

        User entity = new User(id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            entity.setEmail(resultSet.getString("user_email"));
            entity.setPassword(resultSet.getString("user_password"));
            entity.setPhone(resultSet.getString("user_phone"));
            entity.setAgreement(resultSet.getBoolean("user_agreement"));
        }

        connection.close();
        return entity;
    }



}
