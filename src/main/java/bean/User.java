package bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
*@Author by wanghaopeng on 2019/11/22 8:37
*@Comment user表对应的bean
**/
@Component
public class User implements Serializable {
    private Integer id;
    private String name;
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
