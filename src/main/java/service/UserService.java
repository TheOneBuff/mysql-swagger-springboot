package service;

import bean.User;

import java.util.List;

public interface UserService {
    //获取所有user表的数据
    List<User> userlist();
    //根据id获得user表对应数据
    User selectUser(int id);
}
