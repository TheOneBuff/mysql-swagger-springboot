package bean;

import org.springframework.stereotype.Component;

/**
*@Author by wanghaopeng on 2019/11/22 8:39
*@Comment Customer对应的bean
**/
@Component
public class Customer {
    public String name;
    public String password;
    public String ip;
    public String mac;
    //这2个字段没什么用，数据更新的时候自动更新update_time，create_time插入的时候更新
    public String update_time;
    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String create_time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

}
