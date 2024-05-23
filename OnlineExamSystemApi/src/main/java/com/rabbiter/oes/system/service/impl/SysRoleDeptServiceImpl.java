package com.rabbiter.oes.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbiter.oes.system.entity.SysRoleDept;
import com.rabbiter.oes.system.mapper.SysRoleDeptMapper;
import com.rabbiter.oes.system.service.SysRoleDeptService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author JackieLiao
 * @description 针对表【sys_role_dept(角色和部门关联表)】的数据库操作Service实现
 * @since 2024-05-22 10:23:59
 */
@Service
@AllArgsConstructor
public class SysRoleDeptServiceImpl extends ServiceImpl<SysRoleDeptMapper, SysRoleDept> implements SysRoleDeptService {

}




