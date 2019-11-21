package service;

import bean.User;

import java.util.List;

public interface UserService {
    //获取所有user表的数据
    List<User> userlist();
    //根据id获得user表对应数据
    List<User> selectUser(int id);
    //增加单个user
    int insertUser(User user);
    //修改单个user
    int updateUser(User user);
    //增加多个User信息到数据库
    int insertUserList(List<User> userlist);
}
