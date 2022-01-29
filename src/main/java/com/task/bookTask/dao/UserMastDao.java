package com.task.bookTask.dao;

import com.task.bookTask.model.user.UserMast;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserMastDao extends JpaRepository<UserMast,Long> {
}
