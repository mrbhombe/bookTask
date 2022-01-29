package com.task.bookTask.model;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Date;

@NoArgsConstructor
@Data
public class GeneralResponse <T>{

    T data;
    String msg;
    Boolean status;
    int statusCode;
    Long timeStamp;

    public GeneralResponse(T data, String msg, Boolean status, HttpStatus statusCode, Date timeStamp) {
        this.data = data;
        this.msg = msg;
        this.status = status;
        this.statusCode = statusCode.value();
        this.timeStamp = timeStamp.getTime();
    }
}
