package cn.etu.jxust.service.impl;

import cn.etu.jxust.service.IAccountService;

import java.util.Date;

/**
 * @author ddh
 * @date 2019/8/25 19:36
 * @description
 **/
public class AccountServiceImpl2 implements IAccountService {

    private String name;
    private Integer age;
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "AccountServiceImpl2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", date=" + date +
                '}';
    }

    public void findAll() {
        System.out.println(toString());
    }
}
