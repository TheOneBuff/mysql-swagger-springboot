package dao;

import bean.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CustomerDao {
    //新增一个客户信息到数据库
    int insertCustomer(Customer customer);

    //查询所有客户信息
    @Select("select name,password,ip,mac,update_time from customer")
    List<Customer> selectAll();

    //更新数据库
    int updateCustomer(Customer customer);
}
