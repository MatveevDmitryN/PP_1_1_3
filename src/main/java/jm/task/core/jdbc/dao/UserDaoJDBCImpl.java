package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50), lastName VARCHAR(50), age TINYINT)";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при создании таблицы users", e);
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES ('" +
                name + "', '" + lastName + "', " + age + ")";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = " + id;
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();
             var resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}