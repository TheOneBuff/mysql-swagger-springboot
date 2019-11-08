package controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.ExeclService;


@RequestMapping(value = "/outputexecl")
public class OutPutExeclController {

    private static final Logger logger = LoggerFactory.getLogger(OutPutExeclController.class);

    @Autowired
    ExeclService execlService;
    @ApiOperation(value = "输出EXECL",notes = "输出EXECL")
    @RequestMapping(value = "/output")
    public void OutputExecl(@RequestParam (value = "path")String path,@RequestParam(value = "name")String name)
    {
        execlService.OutputExecl(path,name);
    }
}
