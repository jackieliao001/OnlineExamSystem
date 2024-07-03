package com.rabbiter.oes.exam.controller;

import com.rabbiter.oes.common.resp.ApiResult;
import com.rabbiter.oes.common.resp.ApiResultHandler;
import com.rabbiter.oes.exam.entity.Replay;
import com.rabbiter.oes.exam.service.impl.ReplayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReplayController {

    @Autowired
    private ReplayServiceImpl replayService;

    @PostMapping("/replay")
    public ApiResult add(@RequestBody Replay replay) {
        int data = replayService.add(replay);
        if (data != 0) {
            return ApiResultHandler.success();
        } else {
            return ApiResultHandler.failure();
        }
    }

    @GetMapping("/replay/{messageId}")
    public ApiResult findAllById(@PathVariable("messageId") Integer messageId) {
        List<Replay> res = replayService.findAllById(messageId);
        return ApiResultHandler.success(res);
    }
}
