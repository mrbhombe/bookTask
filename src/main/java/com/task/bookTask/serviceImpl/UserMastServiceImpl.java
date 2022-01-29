package com.task.bookTask.serviceImpl;

import com.task.bookTask.dao.UserMastDao;
import com.task.bookTask.model.user.UserMast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserMastServiceImpl")
public class UserMastServiceImpl {

    @Autowired
    UserMastDao userMastDao;

    public void addUserMastRecord(UserMast record) {
        userMastDao.save(record);
    }

    public List<UserMast> getAllUserMast() {
        return userMastDao.findAll();
    }
}
