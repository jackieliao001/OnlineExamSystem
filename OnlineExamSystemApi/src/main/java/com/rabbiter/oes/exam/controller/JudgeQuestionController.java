package com.rabbiter.oes.exam.controller;

import com.rabbiter.oes.common.resp.ApiResult;
import com.rabbiter.oes.exam.entity.JudgeQuestion;
import com.rabbiter.oes.exam.service.impl.JudgeQuestionServiceImpl;
import com.rabbiter.oes.common.resp.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JudgeQuestionController {

    @Autowired
    private JudgeQuestionServiceImpl judgeQuestionService;

    @PostMapping("/judgeQuestion")
    public ApiResult add(@RequestBody JudgeQuestion judgeQuestion) {
        int res = judgeQuestionService.add(judgeQuestion);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"添加成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"添加失败",res);
    }

    @GetMapping("/judgeQuestionId")
    public ApiResult findOnlyQuestionId() {
        JudgeQuestion res = judgeQuestionService.findOnlyQuestionId();
        return  ApiResultHandler.buildApiResult(200,"查询成功",res);
    }

    @PostMapping("/editJudgeQuestion")
    public ApiResult edit(@RequestBody JudgeQuestion judgeQuestion) {
        int res = judgeQuestionService.edit(judgeQuestion);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"修改成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"修改失败",res);
    }
}
