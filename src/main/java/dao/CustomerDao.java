package dao;

import bean.Customer;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CustomerDao {
    //新增一个客户信息到数据库
    int insertCustomer(Customer customer);

    //查询所有客户信息
    @Select("select name,password,ip,mac,update_time,create_time from customer")
    List<Customer> selectAll();

    //更新数据库
    int updateCustomer(Customer customer);

    //锁数据库行
    //@Select("select name,password from customer where name = #{name} and password = #{password}")
    //Customer selectCustomer(@Param("name") String name, @Param("password") String password);
    Customer selectCustomer(String name, String password);

    //查询单个客户信息
    @Select("select name,ip,mac,update_time,create_time from customer where name = #{name}")
    Customer selectOneCustomer(String name);
}
