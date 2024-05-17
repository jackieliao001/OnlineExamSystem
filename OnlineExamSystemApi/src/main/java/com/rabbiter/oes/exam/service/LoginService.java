package com.rabbiter.oes.exam.service;

import com.rabbiter.oes.exam.entity.Admin;
import com.rabbiter.oes.exam.entity.Student;
import com.rabbiter.oes.exam.entity.Teacher;

public interface LoginService {

    public Admin adminLogin(Integer username, String password);

    public Teacher teacherLogin(Integer username, String password);

    public Student studentLogin(Integer username, String password);
}
