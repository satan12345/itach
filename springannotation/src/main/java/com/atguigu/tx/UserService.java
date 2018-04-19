package com.atguigu.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    UserDao userDao;


    @Transactional
    public  void insetUser(){
        userDao.insert();
        System.out.println("插入完成");
        int i=10/0;
    }
}
