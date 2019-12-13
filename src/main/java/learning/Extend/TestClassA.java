package learning.Extend;

/**
*@Author by wanghaopeng on 2019/12/13 8:49
*@Comment 学习接口相关的东西
**/
public class TestClassA implements InterfaceC {
    @Override
    public void InterfaceCTest() {
        System.out.println(InterfaceC.interfaceA);
        System.out.println(InterfaceC.interfaceB);
        System.out.println(InterfaceC.interfaceC);
    }

    @Override
    public void InterfaceATest() {
        System.out.println(InterfaceA.interfaceA);
    }

    @Override
    public void InterfaceBTest() {
        System.out.println(InterfaceB.interfaceA);
        System.out.println(InterfaceB.interfaceB);
    }
}
