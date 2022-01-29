package com.task.bookTask.controller;

import com.task.bookTask.model.book.BookMast;
import com.task.bookTask.model.book.request.BookFilter;
import com.task.bookTask.model.GeneralResponse;
import com.task.bookTask.serviceImpl.BookMastServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BooKMastController {


    @Autowired
    BookMastServiceImpl bookMastService;


    @PostMapping("/book/add")
    public GeneralResponse<Boolean> addBookMast(@RequestBody BookMast record)
    {
        GeneralResponse<Boolean> result=null;
        try{
            if(record==null)
                throw new Exception("Null record Passed");

            bookMastService.addBookMastRecord(record);
            result = new GeneralResponse<>(true,"Data added successfully",true, HttpStatus.OK,new Date());
        }catch (Exception e)
        {
            result = new GeneralResponse<>(false,e.getMessage(),false, HttpStatus.BAD_REQUEST,new Date());
        }
        return result;
    }
    @PutMapping("/book/update")
    public GeneralResponse<Boolean> updateBookMast(@RequestBody BookMast record)
    {
        GeneralResponse<Boolean> result=null;
        try{
            if(record==null)
                throw new Exception("Null record Passed");

            bookMastService.updateBookMastRecord(record);
            result = new GeneralResponse<>(true,"Data update successfully",true, HttpStatus.OK,new Date());
        }catch (Exception e)
        {
            result = new GeneralResponse<>(false,e.getMessage(),false, HttpStatus.BAD_REQUEST,new Date());
        }
        return result;
    }

    @GetMapping("/book/get")
    public GeneralResponse<BookMast> getBookMastById(@RequestParam(name = "id") Long id)
    {
        GeneralResponse<BookMast> result=null;
        try{
            if(id==null)
                throw new Exception("Null record Passed");

            BookMast bookMast = bookMastService.getBookMastById(id);
            if(bookMast!=null)
                result = new GeneralResponse<>(bookMast,"Data found successfully",true, HttpStatus.OK,new Date());
            else
                result = new GeneralResponse<>(bookMast,"Data not found",true, HttpStatus.OK,new Date());

        }catch (Exception e)
        {
            result = new GeneralResponse<>(null,e.getMessage(),false, HttpStatus.BAD_REQUEST,new Date());
        }
        return result;
    }

    @GetMapping("/book/getAll")
    public GeneralResponse<List<BookMast>> getAllBookMast()
    {
        GeneralResponse<List<BookMast>> result=null;
        try{
            System.out.println(new Date());
            List<BookMast> list = bookMastService.getAllBookMast();
            if(list.size() > 0)
                result = new GeneralResponse<>(list,"Data found successfully",true, HttpStatus.OK,new Date());
            else
                result = new GeneralResponse<>(list,"Data not found",true, HttpStatus.OK,new Date());

        }catch (Exception e)
        {
            result = new GeneralResponse<>(null,e.getMessage(),false, HttpStatus.BAD_REQUEST,new Date());
        }
        return result;
    }

    @PostMapping("/book/getBookByFilter")
    public GeneralResponse<List<BookMast>> getAllBookMastByFilter(@RequestBody BookFilter record)
    {
        GeneralResponse<List<BookMast>> result=null;
        try{
            List<BookMast> list = bookMastService.getAllBookMastByBookFilter(record);
            if(list.size() > 0)
                result = new GeneralResponse<>(list,"Data found successfully",true, HttpStatus.OK,new Date());
            else
                result = new GeneralResponse<>(list,"Data not found",true, HttpStatus.OK,new Date());

        }catch (Exception e)
        {
            result = new GeneralResponse<>(null,e.getMessage(),false, HttpStatus.BAD_REQUEST,new Date());
        }
        return result;
    }

}
