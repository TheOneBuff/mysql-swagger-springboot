package dao;

import bean.Customer;
import org.apache.ibatis.annotations.Insert;

public interface CustomerDao {
    //新增一个客户信息到数据库
    int insertCustomer(Customer customer);
}
