package com.apidemo.model;

import lombok.Data;

@Data
public class UpdateUserInfoCase {
    private int id;
    private int userId;
    private String userName;
    private int age;
    private String sex;
    private String permission;
    private String isDelete;
    private String expected;
}
