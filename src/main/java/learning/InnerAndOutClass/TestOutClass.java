package learning.InnerAndOutClass;

/**
*@Author by wanghaopeng on 2019/12/13 8:49
*@Comment 学习内部类
**/
public class TestOutClass extends ClassA {
    private String Outstr = "这是外部类";
    public void write()
    {
        System.out.println(Outstr);
        System.out.println(super.str);
    }

    public class TestInnerClass extends ClassB {
        private String innerStr = "这是一个内部类";
        public void write()
        {
            System.out.println(str);
            System.out.println(super.str);
            System.out.println(innerStr);
        }
    }
}
