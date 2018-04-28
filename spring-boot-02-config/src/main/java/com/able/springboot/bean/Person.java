package com.able.springboot.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 将配置文件中的每一个属性的值 映射到每个组件中
 * @ConfigurationProperties: 将本类中的所有属性和配置文件中的相关的配置进行绑定
 *  prefix="person":配置文件中哪个下面的所有属性进行一一映射
 *  只有这个组件是容器的组件 才能使用容器提供的@ConfigurationProperties功能
 *  默认从全局配置文件中获取值
 *
 *  <bean class="Person">
 *      <property name="lastName" value="字面量/${key}从环境变量,配置文件中获取值/#{Spel}"/>
 *  </bean>
 */
@ConfigurationProperties(prefix = "person")
/**
 * 加载指定的配置文件
 */
@PropertySource({"classpath:person.properties"})
@Component
public class Person {
    //@Value("${person.last-name}")
    private String lastName;
    //@Value("#{11*2}")
    private Integer age;
   // @Value("true")
    private Boolean boss;
    private Date birth;

    private Map<String,Object> map;

    private List<Object> list;

    private Dog dog;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getBoss() {
        return boss;
    }

    public void setBoss(Boolean boss) {
        this.boss = boss;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birth=" + birth +
                ", map=" + map +
                ", list=" + list +
                ", dog=" + dog +
                '}';
    }
}
