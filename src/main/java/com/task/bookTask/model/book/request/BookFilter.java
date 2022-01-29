package com.task.bookTask.model.book.request;

import lombok.Data;

@Data
public class BookFilter {
    String bookName;
    String authorName;
    Boolean isAnd; //if true the use and query else use or query
}
