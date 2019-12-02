package controller;

import bean.Customer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CustomerService;
import tool.EncryptAndDecrypt;
import tool.GetIpAndMac;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
*@Author by wanghaopeng on 2019/11/22 8:26
*@Comment 对客户信息操作
**/
@RestController
@RequestMapping("/login")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    EncryptAndDecrypt encryptAndDecrypt;

    @Autowired
    GetIpAndMac getIpAndMac;

    @ApiOperation(value = "新增客户信息",notes = "新增客户信息")
    @RequestMapping(value = "/insertCustomer",method = RequestMethod.POST)
    @ResponseBody
    public String insertCustomer(@RequestBody Customer customerinfo) throws Exception {
        List<Customer> customerList = customerService.selectAll();
        for (Customer customer : customerList) {
            if (customerinfo.getName().equals(customer.name))
            {
                return "该用户已经被注册";
            }
        }
        //获得当前时间
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        customerinfo.setUpdate_time(sdf.format(date));
        //对密码进行加密 把加密后的密码放到客户实体中
        customerinfo.setPassword(encryptAndDecrypt.encrypt(customerinfo.getPassword()));
        //获取IP和mac
        getIpAndMac.getipmac(customerinfo);
        int count = customerService.insertCustomer(customerinfo);
        if (count != 0)
        {
            return "注册成功";
        }
        else {
            return "注册失败";
         }
    }

    @ApiOperation(value = "修改客户信息",notes = "修改客户信息")
    @RequestMapping(value = "/updateCustomer",method = RequestMethod.POST)
    @ResponseBody
    public String updateCustomer(@RequestBody Customer customerinfo) throws Exception {
        //获得当前时间
        //Date date = new Date();
        //DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //customerinfo.setUpdate_time(sdf.format(date));
        //对密码进行加密 把加密后的密码放到客户实体中
        customerinfo.setPassword(encryptAndDecrypt.encrypt(customerinfo.getPassword()));
        //获取IP和mac
        getIpAndMac.getipmac(customerinfo);
        int count = customerService.updateCustomer(customerinfo);
        if (count != 0)
        {
            return "更改成功";
        }
        else {
            return "更改失败";
        }
    }
}
