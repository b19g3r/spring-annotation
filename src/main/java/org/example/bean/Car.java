package org.example.bean;

public class Car {

    private String name;
    private Integer age;

    public Car(String name, Integer age) {
        super();
        this.name = name;
        this.age = age;
    }

    public Car() {
        super();
        // TODO Auto-generated constructor stub
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

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }

}
