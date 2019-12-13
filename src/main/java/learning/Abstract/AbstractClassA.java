package learning.Abstract;

public abstract class AbstractClassA {
    public String str = "这是抽象类AbstractClassA中的非抽象方法:OutPlint";

    public void OutPlint()
    {
        System.out.println(str);
    }

    /**
    * @Comment 这是一个抽象类中的抽象方法
    * @param
    * @return
    **/
    public abstract void abstractAmethond();

}
