package learning.InnerAndOutClass;

/**
*@Author by wanghaopeng on 2019/12/9 15:53
*@Comment 内部类的使用
**/
public class OutClassAndInnerClass {

    private String OutString = "外部类使用变量";
    public String Output()
    {
        OutClassAndInnerClass.InnerClass inner = new InnerClass();
        //return this.OutString + inner.InnerString;
        return inner.InnerOutput();
    }

    public class InnerClass
    {
        private String InnerString = "内部类使用变量";

        public String InnerOutput()
        {
            OutClassAndInnerClass outclass = new OutClassAndInnerClass();
            return this.InnerString + outclass.OutString;
        }
    }

}
