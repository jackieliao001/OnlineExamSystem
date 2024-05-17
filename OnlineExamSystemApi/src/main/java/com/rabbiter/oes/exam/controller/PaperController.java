package com.rabbiter.oes.exam.controller;

import com.rabbiter.oes.exam.entity.*;
import com.rabbiter.oes.exam.service.impl.FillQuestionServiceImpl;
import com.rabbiter.oes.exam.service.impl.JudgeQuestionServiceImpl;
import com.rabbiter.oes.exam.service.impl.MultiQuestionServiceImpl;
import com.rabbiter.oes.exam.service.impl.PaperServiceImpl;
import com.rabbiter.oes.common.resp.ApiResult;
import com.rabbiter.oes.common.resp.ApiResultHandler;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class PaperController {
    private final PaperServiceImpl paperService;
    private final JudgeQuestionServiceImpl judgeQuestionService;
    private final MultiQuestionServiceImpl multiQuestionService;
    private final FillQuestionServiceImpl fillQuestionService;

    @GetMapping("/papers")
    public ApiResult<List<PaperManage>> findAll() {
        List<PaperManage> all = paperService.findAll();
        return ApiResultHandler.buildApiResult(200, "请求成功", all);
    }

    @GetMapping("/paper/{paperId}")
    public Map<Integer, List<?>> findById(@PathVariable("paperId") Long paperId) {
        List<MultiQuestion> multiQuestionRes = multiQuestionService.findByIdAndType(paperId);   //选择题题库 1
        List<FillQuestion> fillQuestionsRes = fillQuestionService.findByIdAndType(paperId);     //填空题题库 2
        List<JudgeQuestion> judgeQuestionRes = judgeQuestionService.findByIdAndType(paperId);   //判断题题库 3
        Map<Integer, List<?>> map = new HashMap<>();
        map.put(1, multiQuestionRes);
        map.put(2, fillQuestionsRes);
        map.put(3, judgeQuestionRes);
        return map;
    }

    @PostMapping("/paperManage")
    public ApiResult<Integer> add(@RequestBody PaperManage paperManage) {
        int res = paperService.add(paperManage);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200, "添加成功", res);
        }
        return ApiResultHandler.buildApiResult(400, "添加失败", res);
    }

    /**
     * 删除试卷中的某条试题
     *
     * @param paperId    试卷id
     * @param type       题目类型。1选择，2填空，3判断
     * @param questionId 题目id
     */
    @GetMapping("/paper/delete/{paperId}/{type}/{questionId}")
    public ApiResult<Object> delete(
            @PathVariable("paperId") String paperId,
            @PathVariable("type") String type,
            @PathVariable("questionId") String questionId
    ) {
        paperService.delete(paperId, type, questionId);
        return ApiResultHandler.buildApiResult(200, "删除成功", null);
    }
}
