package com.task.bookTask.model.bookTransaction.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class IssueBook {
    Long id;
    Date issueDate;
    Date returnDate;//if date is passing then take that date else default 7 days + current date
    Date actualReturnDate;
    Long userId;
    Long bookId;
}
