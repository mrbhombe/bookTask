package com.task.bookTask.controller;


import com.task.bookTask.model.GeneralResponse;
import com.task.bookTask.model.user.UserMast;
import com.task.bookTask.serviceImpl.UserMastServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserMastServiceImpl userMastService;


    @PostMapping("/user/add")
    public GeneralResponse<Boolean> addUserMast(@RequestBody UserMast record)
    {
        GeneralResponse<Boolean> result=null;
        try{
            if(record==null)
                throw new Exception("Null record Passed");

            userMastService.addUserMastRecord(record);
            result = new GeneralResponse<>(true,"Data added successfully",true, HttpStatus.OK,new Date());
        }catch (Exception e)
        {
            result = new GeneralResponse<>(false,e.getMessage(),false, HttpStatus.BAD_REQUEST,new Date());
        }
        return result;
    }
    @GetMapping("/user/getAll")
    public GeneralResponse<List<UserMast>> getAllUserMast()
    {
        GeneralResponse<List<UserMast>> result=null;
        try{
            List<UserMast> userMasts = userMastService.getAllUserMast();
            if(userMasts.size()>0)
            result = new GeneralResponse<>(userMasts,"Data found successfully",true, HttpStatus.OK,new Date());
            else
            result = new GeneralResponse<>(userMasts,"Data not found ",true, HttpStatus.OK,new Date());
        }catch (Exception e)
        {
            result = new GeneralResponse<>(null,e.getMessage(),false, HttpStatus.BAD_REQUEST,new Date());
        }
        return result;
    }
}
