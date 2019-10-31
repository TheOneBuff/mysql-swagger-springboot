package dao;

import bean.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {
    @Select("select * from user")
    List<User> userlist();

    User selectUser(int id);
}
