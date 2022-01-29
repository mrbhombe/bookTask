package com.task.bookTask.dao;

import com.task.bookTask.model.bookTransaction.BookTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookTransactionDao extends JpaRepository<BookTransaction,Long> {

    @Query(value = "select * from book_transaction where book_id = :bookId AND user_id = :userId AND id = :id order by id DESC",nativeQuery = true)
    BookTransaction getLatestBookTransactionByIdAndUserIdAndBookId(@Param("id") Long id,@Param("userId") Long userId,@Param("bookId") Long bookId);

    @Query("select bt from BookTransaction bt where bt.userMast.id=:id")
    List<BookTransaction> findByUserMastId(Long id);
}
