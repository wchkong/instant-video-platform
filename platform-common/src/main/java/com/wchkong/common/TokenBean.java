package com.wchkong.common;

import lombok.Data;

import java.util.Date;

@Data
public class TokenBean {
    private int id;
    private String site;
    private String token;
    private int expiresIn;
    private String refreshToken;
    private Date updatedTime;
}
