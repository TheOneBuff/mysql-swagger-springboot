package service.impl;

import bean.User;
import dao.UserDao;
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
    public List<User> selectUser(int id) {
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

    @Override
    public int insertUserList(List<User> userlist) {
        int count = 0;
        for (User user : userlist)
        {
            count = userDao.insertUser(user) + count;
        }
        return count;
    }
}
