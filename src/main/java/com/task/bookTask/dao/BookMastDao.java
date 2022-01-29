package com.task.bookTask.dao;

import com.task.bookTask.model.book.BookMast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BookMastDao extends JpaRepository<BookMast,Long> {

    @Query("Select bm from BookMast bm where bm.isbnNo=:isbnNo")
    BookMast getBookMastISBNNo(String isbnNo);

    @Query("select bm from BookMast bm where (:bookName IS NULL OR bm.name LIKE :bookName%) AND (:authorName IS NULL OR bm.authorName LIKE :authorName% )")
    List<BookMast> findByBookNameAndAuthorNameWithAndFlagTrue(String bookName, String authorName);

    @Query("select bm from BookMast bm where (:bookName IS NULL OR bm.name LIKE :bookName%) OR (:authorName IS NULL OR bm.authorName LIKE :authorName% )")
    List<BookMast> findByBookNameAndAuthorNameWithAndFlagFalse(String bookName, String authorName);
}
