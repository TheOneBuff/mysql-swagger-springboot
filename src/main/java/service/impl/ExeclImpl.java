package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ExeclService;
import tool.ExeclOutput;

import java.io.IOException;


/**
 *@Author by wanghaopeng on 2019/11/8 8:48
 *@Comment Execld的实现类
 **/
@Service
public class ExeclImpl implements ExeclService {

    @Autowired
    ExeclOutput execlOutput;

    @Override
    public void OutputExecl(String path, String sheetname) throws IOException {
        execlOutput.execlInit();
    }
}
