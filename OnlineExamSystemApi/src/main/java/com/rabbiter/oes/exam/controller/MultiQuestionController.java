package com.rabbiter.oes.exam.controller;

import com.rabbiter.oes.common.resp.ApiResult;
import com.rabbiter.oes.exam.entity.MultiQuestion;
import com.rabbiter.oes.exam.service.impl.MultiQuestionServiceImpl;
import com.rabbiter.oes.common.resp.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiQuestionController {

    @Autowired
    private MultiQuestionServiceImpl multiQuestionService;

    @GetMapping("/multiQuestionId")
    public ApiResult findOnlyQuestion() {
        MultiQuestion res = multiQuestionService.findOnlyQuestionId();
        return ApiResultHandler.buildApiResult(200,"查询成功",res);
    }

    @PostMapping("/MultiQuestion")
    public ApiResult add(@RequestBody MultiQuestion multiQuestion) {
        int res = multiQuestionService.add(multiQuestion);
        if (res != 0) {

            return ApiResultHandler.buildApiResult(200,"添加成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"添加失败",res);
    }

    @PostMapping("/editMultiQuestion")
    public ApiResult edit(@RequestBody MultiQuestion multiQuestion) {
        int res = multiQuestionService.edit(multiQuestion);
        if (res != 0) {

            return ApiResultHandler.buildApiResult(200,"修改成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"修改失败",res);
    }
}
