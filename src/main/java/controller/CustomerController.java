package controller;

import bean.Customer;
import globalvariables.GlobalVariables;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import service.CustomerService;
import tool.EncryptAndDecrypt;
import tool.GetIpAndMac;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
*@Author by wanghaopeng on 2019/11/22 8:26
*@Comment 对客户信息操作
**/
@RestController
@RequestMapping("/login")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    @Autowired
    EncryptAndDecrypt encryptAndDecrypt;

    @Autowired
    GetIpAndMac getIpAndMac;

    @ApiOperation(value = "新增客户信息",notes = "新增客户信息")
    @RequestMapping(value = "/insertCustomer",method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)//事务
    public String insertCustomer(@RequestBody Customer customerinfo, HttpServletRequest request) throws Exception {

        logger.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "开始执行");
        List<Customer> customerList = customerService.selectAll();
        for (Customer customer : customerList) {
            if (customerinfo.getName().equals(customer.name))
            {
                return GlobalVariables.register_occupy;
            }
        }
        //获得当前时间
        //Date date = new Date();
        //DateFormat sdf = new SimpleDateFormat(GlobalVariables.time_format);
        //customerinfo.setUpdate_time(sdf.format(date));
        //对密码进行加密 把加密后的密码放到客户实体中
        customerinfo.setPassword_new(encryptAndDecrypt.encrypt(customerinfo.getPassword_new()));
        //获取本地IP和mac
        //getIpAndMac.getipmac(customerinfo);
        //request.getRemoteAddr()获取请求中的ip
        customerinfo.setIp(request.getRemoteAddr());
        int count = customerService.insertCustomer(customerinfo);
        if (count != 0)
        {
            logger.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "结束执行");
            return GlobalVariables.register_success;
        }
        else {
            logger.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "结束执行");
            return GlobalVariables.register_fail;
         }
    }

    @ApiOperation(value = "修改客户密码",notes = "修改客户密码")
    @RequestMapping(value = "/updateCustomer",method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)//事务
    public String updateCustomer(@RequestBody Customer customer) throws Exception {

        if (customerService.selectCustomer(customer.getName(), ("".equals(customer.getPassword_old()) ? "" : encryptAndDecrypt.encrypt(customer.getPassword_old()))) != null)
        {
            //对密码进行加密 把加密后的密码放到客户实体中
            customer.setPassword_new("".equals(customer.getPassword_new()) ? "" : encryptAndDecrypt.encrypt(customer.getPassword_new()));
            int count = customerService.updateCustomer(customer);
            //int i = 1/0 为了验证事务是否成功开启
            if (count != 0)
            {
                return GlobalVariables.change_success;
            }
            else {
                return GlobalVariables.change_fail;
            }
        }
        return GlobalVariables.data_match;
    }

    @ApiOperation(value = "查询全部客户信息",notes = "查询全部客户信息")
    @RequestMapping(value = "/selectAll",method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> selectAll()
    {
        return customerService.selectAll();
    }

    @ApiOperation(value = "查询单个客户信息",notes = "查询单个客户信息")
    @RequestMapping(value = "/selectOneCustomer",method = RequestMethod.GET)
    @ResponseBody
    public Customer selectOneCustomer(@RequestParam(value = "name") String name)
    {
        return customerService.selectOneCustomer(name);
    }

}
