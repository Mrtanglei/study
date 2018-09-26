package com.lei.tang.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author tanglei
 * @date 18/9/25
 */
@Getter
@Setter
public class User {

    private String name;

    private int age;

    private Date date;

    private String desc;
}
