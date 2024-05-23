package com.rabbiter.oes.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbiter.oes.system.entity.SysPost;
import com.rabbiter.oes.system.mapper.SysPostMapper;
import com.rabbiter.oes.system.service.SysPostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author JackieLiao
 * @description 针对表【sys_post(岗位信息表)】的数据库操作Service实现
 * @since 2024-05-22 10:19:52
 */
@Service
@AllArgsConstructor
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPost> implements SysPostService {

}




