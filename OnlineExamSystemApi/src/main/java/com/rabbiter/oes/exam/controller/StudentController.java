package com.rabbiter.oes.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.oes.common.enums.ResponseCode;
import com.rabbiter.oes.common.resp.ApiResult;
import com.rabbiter.oes.common.resp.ApiResultHandler;
import com.rabbiter.oes.exam.entity.Student;
import com.rabbiter.oes.exam.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping("/students/{page}/{size}/{name}/{grade}/{tel}/{institute}/{major}/{clazz}")
    public ApiResult findAll(@PathVariable Integer page, @PathVariable Integer size,
                             @PathVariable String name, @PathVariable String grade,
                             @PathVariable String tel, @PathVariable String institute,
                             @PathVariable String major, @PathVariable String clazz) {
        Page<Student> studentPage = new Page<>(page, size);
        IPage<Student> res = studentService.findAll(
                studentPage, name, grade, tel, institute, major, clazz
        );
        return ApiResultHandler.success(res);
    }

    @GetMapping("/student/{studentId}")
    public ApiResult findById(@PathVariable("studentId") Integer studentId) {
        Student res = studentService.findById(studentId);
        if (res != null) {
            return ApiResultHandler.success(res);
        } else {
            return ApiResultHandler.failure(ResponseCode.NOT_FOUND_KEY);
        }
    }

    @DeleteMapping("/student/{studentId}")
    public ApiResult deleteById(@PathVariable("studentId") Integer studentId) {
        return ApiResultHandler.success(studentService.deleteById(studentId));
    }

    @PutMapping("/studentPWD")
    public ApiResult updatePwd(@RequestBody Student student) {
        studentService.updatePwd(student);
        return ApiResultHandler.success();
    }

    @PutMapping("/student")
    public ApiResult update(@RequestBody Student student) {
        int res = studentService.update(student);
        if (res != 0) {
            return ApiResultHandler.success();
        }
        return ApiResultHandler.failure();
    }

    @PostMapping("/student")
    public ApiResult add(@RequestBody Student student) {
        int res = studentService.add(student);
        if (res == 1) {
            return ApiResultHandler.success();
        } else {
            return ApiResultHandler.failure();
        }
    }
}
