package web.dao;

import web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    //выводим всех users
    List<User> getAllUsers();

    //добавляем user
    void addUser(String name, String lastName, int age, String password);

    //поиск пользователя по id
    User searchUser(int id);

    //изменяем user
    void updateUser(int id, User user);

    //удаляем user
    void deleteUserById(int id);

}
