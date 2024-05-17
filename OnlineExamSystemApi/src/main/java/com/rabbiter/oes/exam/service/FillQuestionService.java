package com.rabbiter.oes.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.oes.exam.entity.FillQuestion;

import java.util.List;

public interface FillQuestionService {

    List<FillQuestion> findByIdAndType(Long paperId);

    IPage<FillQuestion> findAll(Page<FillQuestion> page);

    FillQuestion findOnlyQuestionId();

    int add(FillQuestion fillQuestion);

    List<Integer> findBySubject(String subject,Integer pageNo);

    int edit(FillQuestion fillQuestion);
}
