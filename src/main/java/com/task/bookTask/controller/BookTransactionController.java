package com.task.bookTask.controller;

import com.task.bookTask.model.GeneralResponse;
import com.task.bookTask.model.book.BookMast;
import com.task.bookTask.model.bookTransaction.BookTransaction;
import com.task.bookTask.model.bookTransaction.request.IssueBook;
import com.task.bookTask.model.user.UserMast;
import com.task.bookTask.serviceImpl.BookTransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookTransactionController {

    @Autowired
    BookTransactionServiceImpl bookTransactionService;



    @PostMapping("/book/transaction/issueBook")
    public GeneralResponse<Boolean> issueBook(@RequestBody IssueBook record)
    {
        GeneralResponse<Boolean> result=null;
        try{
            if(record==null)
                throw new Exception("Null record Passed");

            bookTransactionService.issueBookRecord(record);
            result = new GeneralResponse<>(true,"Data added successfully",true, HttpStatus.OK,new Date());
        }catch (Exception e)
        {
            result = new GeneralResponse<>(false,e.getMessage(),false, HttpStatus.BAD_REQUEST,new Date());
        }
        return result;
    }

    @PutMapping("/book/transaction/returnBook")
    public GeneralResponse<Boolean> returnBook(@RequestBody IssueBook record)
    {
        GeneralResponse<Boolean> result=null;
        try{
            if(record==null)
                throw new Exception("Null record Passed");

            bookTransactionService.returnBookRecord(record);
            result = new GeneralResponse<>(true,"Data updated successfully",true, HttpStatus.OK,new Date());
        }catch (Exception e)
        {
            result = new GeneralResponse<>(false,e.getMessage(),false, HttpStatus.BAD_REQUEST,new Date());
        }
        return result;
    }


    @GetMapping("/book/transaction/getAll")
    public GeneralResponse<List<BookTransaction>> getAllBookTransaction()
    {
        GeneralResponse<List<BookTransaction>> result=null;
        try{
            List<BookTransaction> bookTransactionList = bookTransactionService.getAllBookTransaction();
            if(bookTransactionList.size()>0)
                result = new GeneralResponse<>(bookTransactionList,"Data found successfully",true, HttpStatus.OK,new Date());
            else
                result = new GeneralResponse<>(bookTransactionList,"Data not found ",true, HttpStatus.OK,new Date());
        }catch (Exception e)
        {
            result = new GeneralResponse<>(null,e.getMessage(),false, HttpStatus.BAD_REQUEST,new Date());
        }
        return result;
    }


    @GetMapping("/book/transaction/getAllByUser")
    public GeneralResponse<List<BookTransaction>> getAllTransactionById(@RequestParam("userId") Long id)
    {
        GeneralResponse<List<BookTransaction>> result=null;
        try{
            if(id==null)
                throw new Exception("Null record Passed");

            List<BookTransaction> bookTransactionList = bookTransactionService.getAllBookTransactionByUserId(id);
            if(bookTransactionList.size() > 0)
                result = new GeneralResponse<>(bookTransactionList,"Data found successfully",true, HttpStatus.OK,new Date());
            else
                result = new GeneralResponse<>(bookTransactionList,"Data not found",true, HttpStatus.OK,new Date());
        }catch (Exception e)
        {
            result = new GeneralResponse<>(null,e.getMessage(),false, HttpStatus.BAD_REQUEST,new Date());
        }
        return result;
    }



}
