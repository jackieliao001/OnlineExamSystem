package com.rabbiter.oes.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.oes.exam.entity.JudgeQuestion;

import java.util.List;

public interface JudgeQuestionService {

    List<JudgeQuestion> findByIdAndType(Long paperId);

    IPage<JudgeQuestion> findAll(Page<JudgeQuestion> page);

    JudgeQuestion findOnlyQuestionId();

    int add(JudgeQuestion judgeQuestion);

    List<Integer> findBySubject(String subject,Integer pageNo);

    int edit(JudgeQuestion judgeQuestion);
}
