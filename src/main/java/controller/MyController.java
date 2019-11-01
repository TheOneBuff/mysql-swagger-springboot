package controller;

import bean.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.List;

@RestController
@RequestMapping("/my")
public class MyController {

    @Autowired
    UserService userService;
    //URL：http://localhost:1010/my/helloword
    @RequestMapping(value = "/helloword",method = RequestMethod.GET)
    @ApiOperation(value = "helloword学习",notes = "练习swagger")
    @ResponseBody
    public String helloword()
    {
        return "helloword";
    }

    @RequestMapping(value = "/userall",method = RequestMethod.GET)
    @ApiOperation(value = "获取user表所有数据",notes = "获取user表所有数据")
    @ResponseBody
    public List<User> userall()
    {
        return userService.userlist();
    }

    //http://localhost:1010/my/selectUser?id=1
    @RequestMapping(value = "/selectUser",method = RequestMethod.GET)
    @ApiOperation(value="根据id获得相应数据",notes = "根据id获得相应数据")
    @ResponseBody
    public User selectUser(@RequestParam(value = "id") int id)
    {
        return userService.selectUser(id);
    }

    //增加User信息到数据库
    @RequestMapping(value="/insertUser",method =RequestMethod.POST)
    @ApiOperation(value="增加User信息到数据库",notes = "增加User信息到数据库")
    @ResponseBody
    public int insertUser(@RequestBody User user)
    {
        return userService.insertUser(user);
    }

    @RequestMapping(value="/updateUser",method =RequestMethod.POST)
    @ApiOperation(value="修改User信息到数据库",notes = "修改User信息到数据库")
    @ResponseBody
    public int updateUser(@RequestBody User user)
    {
        return userService.updateUser(user);
    }
}
