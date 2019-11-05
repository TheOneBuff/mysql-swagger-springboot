package dao;

import bean.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {
    //获得所有的user信息
    @Select("select * from user")
    List<User> userlist();
    //根据ID获得对应的user信息
    List<User> selectUser(int id);
    //增加单个User信息到数据库
    int insertUser(User user);
    //修改单个User信息
    int updateUser(User user);
}
