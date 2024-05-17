package com.rabbiter.oes.exam.service.impl;

import com.rabbiter.oes.exam.entity.FillQuestion;
import com.rabbiter.oes.exam.entity.JudgeQuestion;
import com.rabbiter.oes.exam.entity.MultiQuestion;
import com.rabbiter.oes.exam.entity.PaperManage;
import com.rabbiter.oes.exam.mapper.PaperMapper;
import com.rabbiter.oes.exam.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperMapper paperMapper;

    @Autowired
    private JudgeQuestionServiceImpl judgeQuestionService;

    @Autowired
    private MultiQuestionServiceImpl multiQuestionService;

    @Autowired
    private FillQuestionServiceImpl fillQuestionService;

    @Override
    public List<PaperManage> findAll() {
        return paperMapper.findAll();
    }

    @Override
    public List<PaperManage> findById(Integer paperId) {
        return paperMapper.findById(paperId);
    }

    @Override
    public int add(PaperManage paperManage) {
        return paperMapper.add(paperManage);
    }

    @Override
    public Integer getMaxScore(Long paperId) {
        List<MultiQuestion> multiQuestionRes = multiQuestionService.findByIdAndType(paperId);   //选择题题库 1
        List<FillQuestion> fillQuestionsRes = fillQuestionService.findByIdAndType(paperId);     //填空题题库 2
        List<JudgeQuestion> judgeQuestionRes = judgeQuestionService.findByIdAndType(paperId);   //判断题题库 3
        return 2 * (multiQuestionRes.size() + fillQuestionsRes.size() + judgeQuestionRes.size());
    }

    @Override
    public void delete(String paperId, String type, String questionId) {
        paperMapper.delete(paperId, type, questionId);
    }

    @Override
    public void deleteByPaperId(Long paperId) {
        paperMapper.deleteByPaperId(paperId);
    }

}
