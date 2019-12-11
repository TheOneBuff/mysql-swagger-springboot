package service.impl;

import bean.Customer;
import controller.CustomerController;
import dao.CustomerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import service.CustomerService;

import java.util.List;

/**
*@Author by wanghaopeng on 2019/11/25 8:56
*@Comment
**/
@Service
public class CustomerImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerImpl.class);

    @Autowired
    CustomerDao customerDao;

    /**
     * @Comment 插入单个客户信息到数据库
     * @param [customer]
     * @return int
     **/
    @Override
    public int insertCustomer(Customer customer) {
        return customerDao.insertCustomer(customer);
    }

    @Override
    public List<Customer> selectAll() {
        return customerDao.selectAll();
    }

    @Override
    public int updateCustomer(Customer customer) {
        return customerDao.updateCustomer(customer);
    }

    @Override
    public Customer selectCustomer(String name, String password) {
        logger.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "开始执行");
        return customerDao.selectCustomer(name, password);
    }

    @Override
    public Customer selectOneCustomer(String name) {
        return customerDao.selectOneCustomer(name);
    }
}
