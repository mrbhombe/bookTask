package com.task.bookTask.model.bookTransaction;

import com.task.bookTask.model.book.BookMast;
import com.task.bookTask.model.bookTransaction.request.IssueBook;
import com.task.bookTask.model.user.UserMast;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.Calendar;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BookTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Date issueDate;
    Date returnDate;
    Date actualReturnDate;
    Date createdDate;
    Date updatedDate;
    Boolean returnStatus;//default false // else true is the book is returned

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.REFRESH })
    @JoinColumn(name="user_id", referencedColumnName = "id", insertable = true, updatable = true)
    UserMast userMast;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.REFRESH })
    @JoinColumn(name="book_id", referencedColumnName = "id", insertable = true, updatable = true)
    BookMast book;

    public BookTransaction(IssueBook record, BookMast bookMastExist, UserMast userMastExist) {
        this.book = bookMastExist;
        this.userMast = userMastExist;
        this.issueDate = record.getIssueDate();
        this.returnDate = record.getReturnDate()==null?getSevenDaysTimeFromCurrent():record.getReturnDate();
        this.returnStatus = false;
    }

    //for return book
    public BookTransaction(BookTransaction bookTransactionExist, IssueBook record) {
        this.id = bookTransactionExist.getId();
        this.returnStatus = true;
        this.returnDate = bookTransactionExist.getReturnDate();
        this.actualReturnDate = record.getActualReturnDate();
        this.issueDate = bookTransactionExist.getIssueDate();
        this.userMast = bookTransactionExist.getUserMast();
        this.book = bookTransactionExist.getBook();
        this.createdDate = bookTransactionExist.getCreatedDate();
        this.updatedDate = bookTransactionExist.getUpdatedDate();

    }

    private Date getSevenDaysTimeFromCurrent() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 7);
        return cal.getTime();
    }


    @PrePersist
    public void create()
    {
        this.createdDate = new Date();
    }

    @PreUpdate
    public void update()
    {
        this.updatedDate = new Date();
    }



}
