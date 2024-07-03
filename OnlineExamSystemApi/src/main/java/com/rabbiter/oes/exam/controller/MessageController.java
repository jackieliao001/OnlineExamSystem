package com.rabbiter.oes.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.oes.common.resp.ApiResult;
import com.rabbiter.oes.common.resp.ApiResultHandler;
import com.rabbiter.oes.exam.entity.Message;
import com.rabbiter.oes.exam.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    @Autowired
    private MessageServiceImpl messageService;

    @GetMapping("/messages/{page}/{size}")
    public ApiResult<IPage<Message>> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        Page<Message> messagePage = new Page<>(page,size);
        IPage<Message> all = messageService.findAll(messagePage);
        return ApiResultHandler.success(all);
    }

    @GetMapping("/message/{id}")
    public ApiResult<Message> findById(@PathVariable("id") Integer id) {
        Message res = messageService.findById(id);
        return ApiResultHandler.success(res);
    }

    @DeleteMapping("/message/{id}")
    public ApiResult delete(@PathVariable("id") Integer id) {
        int res = messageService.delete(id);
        if (res == 0) {
            return ApiResultHandler.failure();
        } else {
            return ApiResultHandler.success();
        }
    }

    @PostMapping("/message")
    public ApiResult add(@RequestBody Message message) {
        int res = messageService.add(message);
        if (res == 0) {
            return ApiResultHandler.failure();
        } else {
            return ApiResultHandler.success();
        }
    }
}
