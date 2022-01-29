package com.task.bookTask.model.book;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class BookMast {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(unique=true)
    String isbnNo;
    String name;
    String authorName;
    Long qty;
    Long pages;

}
