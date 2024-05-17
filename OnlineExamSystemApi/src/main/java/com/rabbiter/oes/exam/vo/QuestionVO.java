package com.rabbiter.oes.exam.vo;

import com.rabbiter.oes.exam.entity.FillQuestion;
import com.rabbiter.oes.exam.entity.JudgeQuestion;
import com.rabbiter.oes.exam.entity.MultiQuestion;

public class QuestionVO {
    private String type;

    private FillQuestion fillQuestion;

    private JudgeQuestion judgeQuestion;

    private MultiQuestion multiQuestion;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FillQuestion getFillQuestion() {
        return fillQuestion;
    }

    public void setFillQuestion(FillQuestion fillQuestion) {
        this.fillQuestion = fillQuestion;
    }

    public JudgeQuestion getJudgeQuestion() {
        return judgeQuestion;
    }

    public void setJudgeQuestion(JudgeQuestion judgeQuestion) {
        this.judgeQuestion = judgeQuestion;
    }

    public MultiQuestion getMultiQuestion() {
        return multiQuestion;
    }

    public void setMultiQuestion(MultiQuestion multiQuestion) {
        this.multiQuestion = multiQuestion;
    }
}
