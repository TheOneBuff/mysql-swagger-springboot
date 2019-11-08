package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ExeclService;
import tool.ExeclTool;

import java.io.IOException;


/**
 *@Author by wanghaopeng on 2019/11/8 8:48
 *@Comment Execld的实现类
 **/
@Service
public class ExeclImpl implements ExeclService {

    ExeclTool execlTool;

    //构造函数，由于要实例化的类的构造函数抛出了异常所以这里要捕获
    public ExeclImpl() {
        try {
            execlTool = new ExeclTool("", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @Author by wanghaopeng on 2019/11/7 18:31
     * @param [str]
     * @return void
     **/
    public void Str(String str)
    {


    }

    @Override
    public void OutputExecl(String path, String name) {

    }
}
