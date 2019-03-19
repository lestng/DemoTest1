package com.apidemo.model;

import lombok.Data;

@Data
public class AddUserCase {
    private int id;
    private String userName;
    private String password;
    private String sex;
    private int age;
    private String permission;
    private String isDelete;
    private String expected;

}
