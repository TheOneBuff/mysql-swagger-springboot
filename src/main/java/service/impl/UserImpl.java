package service.impl;

import bean.User;
import dao.UserDao;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

import java.util.List;

@Service
public class UserImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Override
    public List<User> userlist() {
        return userDao.userlist();
    }

    @Override
    public User selectUser(int id) {
        return userDao.selectUser(id);
    }

    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }
}
