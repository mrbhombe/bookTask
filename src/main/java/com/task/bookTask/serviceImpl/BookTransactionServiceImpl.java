package com.task.bookTask.serviceImpl;

import com.task.bookTask.dao.BookMastDao;
import com.task.bookTask.dao.BookTransactionDao;
import com.task.bookTask.dao.UserMastDao;
import com.task.bookTask.model.book.BookMast;
import com.task.bookTask.model.bookTransaction.BookTransaction;
import com.task.bookTask.model.bookTransaction.request.IssueBook;
import com.task.bookTask.model.user.UserMast;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookTransactionServiceImpl")
public class BookTransactionServiceImpl {

    @Autowired
    UserMastDao userMastDao;

    @Autowired
    BookMastDao bookMastDao;

    @Autowired
    BookTransactionDao bookTransactionDao;


    public void issueBookRecord(IssueBook record) throws Exception {

        //check weather the record is exist or not

        UserMast userMastExist = userMastDao.getById(record.getUserId());

        if(userMastExist == null)
            throw new Exception("User data not found");

        BookMast bookMastExist = bookMastDao.findById(record.getBookId()).orElse(null);
        if(bookMastExist == null)
            throw new Exception("Book data not found");


        BookTransaction bookTransaction = new BookTransaction(record,bookMastExist,userMastExist);
        bookTransactionDao.save(bookTransaction);

        //deduct the qty
        bookMastExist.setQty(bookMastExist.getQty()-1);
        bookMastDao.save(bookMastExist);

    }

    public List<BookTransaction> getAllBookTransaction() {
        return bookTransactionDao.findAll();
    }


    public void returnBookRecord(IssueBook record) throws Exception {
        //check the Latest transaction by user is exist or not with user
        BookTransaction bookTransactionExist = bookTransactionDao.getLatestBookTransactionByIdAndUserIdAndBookId(record.getId(),record.getUserId(),record.getBookId());

        if(bookTransactionExist==null)
            throw new Exception("No record found");

        BookMast bookMast = bookMastDao.getById(record.getBookId());
        bookMast.setQty(bookMast.getQty()+1);
        bookMastDao.save(bookMast);
        //do the transaction complete
        bookTransactionExist = new BookTransaction(bookTransactionExist,record);

        bookTransactionDao.save(bookTransactionExist);

    }

    public List<BookTransaction> getAllBookTransactionByUserId(Long id) {
        return bookTransactionDao.findByUserMastId(id);
    }
}
