package controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.ExeclService;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping(value = "/outputexecl")
public class OutPutExeclController {

    private static final Logger logger = LoggerFactory.getLogger(OutPutExeclController.class);

    @Autowired
    ExeclService execlService;

    @ApiOperation(value = "输出user表数据到EXECL",notes = "输出user表数据到EXECL")
    @RequestMapping(value = "/useroutput",method = RequestMethod.POST)
    public void OutputExecl(@RequestParam (value = "path")String path,@RequestParam(value = "sheetname")String sheetname) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        execlService.OutputExecl(path,sheetname);
    }

    @ApiOperation(value = "读取EXECL",notes = "读取EXECL")
    @RequestMapping(value = "/userintput",method = RequestMethod.POST)
    public void InputExecl() throws IOException {
        execlService.IntputExecl();
    }

}
