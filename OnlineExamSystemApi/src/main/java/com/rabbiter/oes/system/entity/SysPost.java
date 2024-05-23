package com.rabbiter.oes.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rabbiter.oes.common.domain.BaseEntity;
import lombok.Data;

/**
 * 岗位信息表
 * TableName sys_post
 */
@Data
@TableName("sys_post")
public class SysPost extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 岗位ID
     */
    @TableId(type = IdType.AUTO)
    private Long postId;

    /**
     * 岗位编码
     */
    private String postCode;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 显示顺序
     */
    private Integer sortNum;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

}