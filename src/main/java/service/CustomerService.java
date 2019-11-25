package service;

import bean.Customer;

public interface CustomerService {
    //新增一个客户信息到数据库
    int insertCustomer(Customer customer);
}
