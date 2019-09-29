package com.app.firebasechatdemo.data.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatModel implements Serializable {

    public String id;
    public String text;
    public String chatId;
    public String date;

}