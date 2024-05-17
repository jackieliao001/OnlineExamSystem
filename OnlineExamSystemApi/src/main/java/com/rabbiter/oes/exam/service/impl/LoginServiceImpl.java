package com.rabbiter.oes.exam.service.impl;

import com.rabbiter.oes.exam.entity.Admin;
import com.rabbiter.oes.exam.entity.Student;
import com.rabbiter.oes.exam.entity.Teacher;
import com.rabbiter.oes.exam.mapper.LoginMapper;
import com.rabbiter.oes.exam.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Admin adminLogin(Integer username, String password) {
        return loginMapper.adminLogin(username,password);
    }

    @Override
    public Teacher teacherLogin(Integer username, String password) {
        return loginMapper.teacherLogin(username,password);
    }

    @Override
    public Student studentLogin(Integer username, String password) {
        return loginMapper.studentLogin(username,password);
    }
}
