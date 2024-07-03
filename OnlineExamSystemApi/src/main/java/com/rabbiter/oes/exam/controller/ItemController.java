package com.rabbiter.oes.exam.controller;

import com.rabbiter.oes.common.enums.ResponseCode;
import com.rabbiter.oes.common.resp.ApiResult;
import com.rabbiter.oes.common.resp.ApiResultHandler;
import com.rabbiter.oes.exam.entity.PaperManage;
import com.rabbiter.oes.exam.service.PaperService;
import com.rabbiter.oes.exam.service.impl.FillQuestionServiceImpl;
import com.rabbiter.oes.exam.service.impl.JudgeQuestionServiceImpl;
import com.rabbiter.oes.exam.service.impl.MultiQuestionServiceImpl;
import com.rabbiter.oes.exam.vo.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ItemController {

    @Autowired
    MultiQuestionServiceImpl multiQuestionService;

    @Autowired
    FillQuestionServiceImpl fillQuestionService;

    @Autowired
    JudgeQuestionServiceImpl judgeQuestionService;

    @Autowired
    PaperService paperService;

    @PostMapping("/item")
    public ApiResult ItemController(@RequestBody Item item) {
        // 选择题
        Integer changeNumber = item.getChangeNumber();
        // 填空题
        Integer fillNumber = item.getFillNumber();
        // 判断题
        Integer judgeNumber = item.getJudgeNumber();
        //出卷id
        Integer paperId = item.getPaperId();

        // 数据库获取数据
        List<Integer> changeNumbers = multiQuestionService.findBySubject(item.getSubject(), changeNumber);
        List<Integer> fills = fillQuestionService.findBySubject(item.getSubject(), fillNumber);
        List<Integer> judges = judgeQuestionService.findBySubject(item.getSubject(), judgeNumber);

        if (changeNumbers == null || changeNumbers.size() != changeNumber) {
            log.warn("科目【{}】题库【选择题】题目数量不足【{}】，组卷失败", item.getSubject(), changeNumber);
            return ApiResultHandler.failure(ResponseCode.GROUP_QUESTION_FAIL);
        }
        if (fills == null || fills.size() != fillNumber) {
            log.warn("科目【{}】题库【填空题】题目数量不足【{}】，组卷失败", item.getSubject(), fillNumber);
            return ApiResultHandler.failure(ResponseCode.GROUP_QUESTION_FAIL);
        }
        if (judges == null || judges.size() != judgeNumber) {
            log.warn("科目【{}】题库【判断题】题目数量不足【{}】，组卷失败", item.getSubject(), judgeNumber);
            return ApiResultHandler.failure(ResponseCode.GROUP_QUESTION_FAIL);
        }

        // 符合组题条件，执行组题
        // 选择题
        for (Integer number : changeNumbers) {
            PaperManage paperManage = new PaperManage(paperId, 1, number);
            int index = paperService.add(paperManage);
            if (index == 0) {
                log.warn("选择题组卷保存失败.questionId:{}", number);
                return ApiResultHandler.failure(ResponseCode.GROUP_QUESTION_FAIL);
            }
        }

        // 填空题
        for (Integer fillNum : fills) {
            PaperManage paperManage = new PaperManage(paperId, 2, fillNum);
            int index = paperService.add(paperManage);
            if (index == 0) {
                log.warn("填空题题组卷保存失败.questionId:{}", fillNum);
                return ApiResultHandler.failure(ResponseCode.GROUP_QUESTION_FAIL);
            }
        }
        // 判断题
        for (Integer judge : judges) {
            PaperManage paperManage = new PaperManage(paperId, 3, judge);
            int index = paperService.add(paperManage);
            if (index == 0) {
                log.warn("判断题题组卷保存失败.questionId:{}", judge);
                return ApiResultHandler.failure(ResponseCode.GROUP_QUESTION_FAIL);
            }
        }

        return ApiResultHandler.success();
    }
}
