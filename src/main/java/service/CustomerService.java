package service;

import bean.Customer;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
public interface CustomerService {
    //新增一个客户信息到数据库
    int insertCustomer(Customer customer);
    //查询所有客户信息
    List<Customer> selectAll();
    //更新数据库信息
    int updateCustomer(Customer customer);
}
