package com.task.bookTask.serviceImpl;

import com.task.bookTask.dao.BookMastDao;
import com.task.bookTask.model.book.BookMast;
import com.task.bookTask.model.book.request.BookFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookMastServiceImpl")
public class BookMastServiceImpl {

    @Autowired
    BookMastDao bookMastDao;

    public void addBookMastRecord(BookMast record) throws Exception {

        //check book is exist with isbn number

        BookMast bookMastExist = bookMastDao.getBookMastISBNNo(record.getIsbnNo());

        if(bookMastExist!=null)
            throw new Exception("Book already exist with ISBN number");


        bookMastDao.save(record);
    }

    public void updateBookMastRecord(BookMast record) throws Exception {
        //check record is exist
        BookMast bookMastExist = bookMastDao.getById(record.getId());
        if(bookMastExist==null)
            throw new Exception("Data not found");

        bookMastDao.save(record);
    }

    public BookMast getBookMastById(Long id) {
        return bookMastDao.getById(id);
    }

    public List<BookMast> getAllBookMast() {
        return bookMastDao.findAll();
    }

    public List<BookMast> getAllBookMastByBookFilter(BookFilter record) {
        List<BookMast> list = null;
        if(record.getIsAnd()==true)
        {
            list = bookMastDao.findByBookNameAndAuthorNameWithAndFlagTrue(record.getBookName(),record.getAuthorName());
        }
        else
        {
            list = bookMastDao.findByBookNameAndAuthorNameWithAndFlagFalse(record.getBookName(),record.getAuthorName());
        }
        return list;


    }
}
