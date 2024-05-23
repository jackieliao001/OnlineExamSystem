package com.rabbiter.oes.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbiter.oes.system.entity.SysUserPost;
import com.rabbiter.oes.system.mapper.SysUserPostMapper;
import com.rabbiter.oes.system.service.SysUserPostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author JackieLiao
 * @description 针对表【sys_user_post(用户与岗位关联表)】的数据库操作Service实现
 * @since 2024-05-22 10:23:59
 */
@Service
@AllArgsConstructor
public class SysUserPostServiceImpl extends ServiceImpl<SysUserPostMapper, SysUserPost> implements SysUserPostService {

}




