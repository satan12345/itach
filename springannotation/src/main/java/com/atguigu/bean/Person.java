package com.atguigu.bean;

import org.springframework.beans.factory.annotation.Value;

public class Person {
    /**
     * 使用@Value赋值
     * 1 基本数值
     * 2可以写spel  #{}
     * 3 可以用${}  取出配置文件中的值（在运行环境变量中的值）
     */
    @Value("旗木卡卡西")
    private String name;
    @Value("#{3*7}")
    private Integer age;
    @Value("${person.os}")
    private String os;


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


    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    public Person() {

    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", os='" + os + '\'' +
                '}';
    }
}
