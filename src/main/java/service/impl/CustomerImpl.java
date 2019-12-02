package service.impl;

import bean.Customer;
import dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    CustomerDao customerDao;

    /**
     * @Comment 插入单个客户信息到数据库
     * @param [customer]
     * @return int
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertCustomer(Customer customer) {
        int count = 0;
        try{
            count = customerDao.insertCustomer(customer);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return -1;
        }
        return count;
    }

    @Override
    public List<Customer> selectAll() {
        return customerDao.selectAll();
    }

    @Override
    public int updateCustomer(Customer customer) {
        return customerDao.updateCustomer(customer);
    }
}
