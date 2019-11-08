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

@RestController
@RequestMapping(value = "/outputexecl")
public class OutPutExeclController {

    private static final Logger logger = LoggerFactory.getLogger(OutPutExeclController.class);

    @Autowired
    ExeclService execlService;
    @ApiOperation(value = "输出EXECL",notes = "输出EXECL")
    @RequestMapping(value = "/output",method = RequestMethod.POST)
    public void OutputExecl(@RequestParam (value = "path")String path,@RequestParam(value = "sheetname")String sheetname) throws IOException {
        execlService.OutputExecl(path,sheetname);
    }
}
