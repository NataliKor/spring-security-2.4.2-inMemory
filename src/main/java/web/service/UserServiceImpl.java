package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.dao.UserDaoImpl;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    //выводим всех users
    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    //добавляем user
    @Override
    @Transactional
    public void addUser(String name, String lastName, int age, String password) {
        userDao.addUser(name, lastName, age, password);
    }

    //поиск пользователя по id
    @Override
    @Transactional
    public User searchUser(int id) {
        return userDao.searchUser(id);
    }

    //изменяем user
    @Override
    @Transactional
    public void updateUser(int id, User user) {
        userDao.updateUser(id, user);
    }

    //удаляем user
    @Override
    @Transactional
    public void deleteUserById(int id) {
        userDao.deleteUserById(id);
    }
}
