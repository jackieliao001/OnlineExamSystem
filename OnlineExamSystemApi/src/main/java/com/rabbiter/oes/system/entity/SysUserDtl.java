package com.rabbiter.oes.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.rabbiter.oes.common.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 系统用户详情表(SysUserdtl)实体类
 *
 * @author jackie liao
 * @since 2024-05-17 15:20:27
 */
@Getter
@Setter
public class SysUserDtl extends BaseEntity {
    private static final long serialVersionUID = -33358725890206389L;
    /**
     * 用户详情ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 学院
     */
    private String institute;
    /**
     * 年级
     */
    private String grade;
    /**
     * 专业
     */
    private String major;
    /**
     * 班级
     */
    private String clazz;
    /**
     * 职称
     */
    private String profession;
    /**
     * 备注
     */
    private String remark;

}

