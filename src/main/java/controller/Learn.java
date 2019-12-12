package controller;

import io.swagger.annotations.ApiOperation;
import learning.Extend.TestClassA;
import learning.InnerAndOutClass.OutClassAndInnerClass;
import learning.InnerAndOutClass.TestOutClass;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/learn")
public class Learn {

    @ApiOperation(value = "学习用-内部类外部类")
    @RequestMapping(value = "/output", method = RequestMethod.GET)
    @ResponseBody
    public String Output()
    {
        OutClassAndInnerClass outandinner = new OutClassAndInnerClass();
        //OutClassAndInnerClass.InnerClass inner = new OutClassAndInnerClass.InnerClass();//内部类public static class InnerClass
        OutClassAndInnerClass.InnerClass inner1 = outandinner.new InnerClass();//内部类public class InnerClass
        System.out.println(inner1.InnerOutput());
        //System.out.println(inner.Output());
        System.out.println(outandinner.Output());
        TestOutClass testOutClass = new TestOutClass();
        TestOutClass.TestInnerClass testInnerClass = testOutClass.new TestInnerClass();
        testOutClass.write();
        testInnerClass.write();
        return outandinner.Output();
    }

    @ApiOperation(value = "学习用-i++和++i和+=和=+")
    @RequestMapping(value = "/learn_iaddoraddi", method = RequestMethod.GET)
    @ResponseBody
    public int learn_iaddoraddi()
    {
        int i = 0;
        System.out.println("i++：" + i++);
        System.out.println("i：" + i);
        System.out.println("++i：" + ++i);
        System.out.println("i：" + i);
        int b = 0;
        System.out.println("++b：" + ++b);
        System.out.println("b：" + b);
        System.out.println("b++：" + b++);
        System.out.println("b：" + b);
        int j = 0;
        j+=1;
        System.out.println("j+=：" + j);
        j = 0;
        j = j + 1;
        System.out.println("j=j+1：" + j);
        int c = 0;
        c=+1;
        System.out.println("c=+：" + c);
        c = 0 ;
        c = c + 1;
        System.out.println("c=c+1：" + c);
        return 1;
    }

    @ApiOperation(value = "学习用-接口集成接口")
    @RequestMapping(value = "/learn_Innerface", method = RequestMethod.GET)
    public String learn_Innerface()
    {
        TestClassA testClassA = new TestClassA();
        testClassA.InterfaceATest();
        testClassA.InterfaceBTest();
        testClassA.InterfaceCTest();
        return "正常";
    }
}
