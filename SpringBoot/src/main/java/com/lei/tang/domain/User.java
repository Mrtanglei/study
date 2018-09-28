package com.lei.tang.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tanglei
 * @date 18/9/25
 */
@Getter
@Setter
public class User implements Serializable {

    private String name;

    private int age;

    private Date date;

    private String desc;

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", age=" + age + ", date=" + date + ", desc='" + desc + '\'' + '}';
    }
}
