package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();

    @Override
    public void createUsersTable() {
        userDao.createUsersTable(); // Вызываем метод DAO
        System.out.println("Таблица пользователей создана.");
    }

    @Override
    public void dropUsersTable() {
        userDao.dropUsersTable(); // Вызываем метод DAO
        System.out.println("Таблица пользователей удалена.");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age); // Вызываем метод DAO
        System.out.println("Пользователь " + name + " " + lastName + " добавлен в базу данных.");
    }

    @Override
    public void removeUserById(long id) {
        userDao.removeUserById(id); // Вызываем метод DAO
        System.out.println("Пользователь с ID " + id + " удалён из базы данных.");
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userDao.getAllUsers(); // Вызываем метод DAO
        System.out.println("Список пользователей получен.");
        return users;
    }

    @Override
    public void cleanUsersTable() {
        userDao.cleanUsersTable(); // Вызываем метод DAO
        System.out.println("Таблица пользователей очищена.");
    }
}
