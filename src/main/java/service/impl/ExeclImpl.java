package service.impl;

import bean.User;
import dao.UserDao;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ExeclService;
import service.UserService;
import tool.ExeclInput;
import tool.ExeclOutput;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;


/**
 *@Author by wanghaopeng on 2019/11/8 8:48
 *@Comment Execld的实现类
 **/
@Service
public class ExeclImpl implements ExeclService {

    @Autowired
    ExeclOutput execlOutput;

    @Autowired
    UserDao userDao;

    @Autowired
    ExeclInput execlInput;

    @Autowired
    UserImpl execlService;

    @Override
    public void OutputExecl(String path, String sheetName) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<User> userList = userDao.userlist();
        String title[] = {"Id","名字","年龄"};
        String propertyNames[] = {"Id","Name","Age"};
        execlOutput.execlInit(userList,title,propertyNames,path,sheetName.replace('\\','/'));
    }

    @Override
    public void IntputExecl() throws IOException {
        List<User> userlist = execlInput.execlInit();
        int count = execlService.insertUserList(userlist);
    }
}
