package com.rabbiter.oes.exam.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;

/**
 * 考试管理表(ExamManage)实体类
 *
 * @author jackie liao
 * @since 2024-05-17 15:25:15
 */
@Getter
@Setter
@TableName("t_exam_manage")
public class ExamManage implements Serializable {
    private static final long serialVersionUID = 617804218250916983L;
    /**
     * 考试ID
     */
    private Long examId;
    /**
     * 关联试卷ID
     */
    private Long paperId;
    /**
     * 考试名称
     */
    private String name;
    /**
     * 考试类型
     */
    private String type;
    /**
     * 考试介绍
     */
    private String description;
    /**
     * 考试开始日期
     */
    private Date startDate;
    /**
     * 考试截止日期
     */
    private Date endDate;
    /**
     * 考试时长，分钟
     */
    private Integer totalTime;
    /**
     * 总分值
     */
    private Integer totalScore;
    /**
     * 考试须知
     */
    private String tips;
    /**
     * 年级
     */
    private String grade;
    /**
     * 学期
     */
    private String term;
    /**
     * 专业
     */
    private String major;
    /**
     * 学院
     */
    private String institute;
    /**
     * 是否已删除
     */
    private Boolean hasDelete;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人
     */
    private String modifiedBy;
    /**
     * 修改时间
     */
    private Date modifiedTime;

}

