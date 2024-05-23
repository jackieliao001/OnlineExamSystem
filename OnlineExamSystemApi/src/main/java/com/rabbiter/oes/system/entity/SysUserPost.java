package com.rabbiter.oes.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户与岗位关联表
 * TableName sys_user_post
 */
@Data
@TableName(value = "sys_user_post")
public class SysUserPost implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 岗位ID
     */
    private Long postId;

}