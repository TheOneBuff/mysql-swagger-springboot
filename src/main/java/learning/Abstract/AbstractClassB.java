package learning.Abstract;

public abstract class AbstractClassB {
    public String str = "这是抽象类AbstractClassB中的非抽象方法:OutPlint";
    public void OutPlint()
    {
        System.out.println(str);
    }
    public abstract void abstractBmethond();
}
