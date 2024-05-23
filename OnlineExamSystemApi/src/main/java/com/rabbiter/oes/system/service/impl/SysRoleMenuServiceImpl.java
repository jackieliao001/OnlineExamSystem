package com.rabbiter.oes.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbiter.oes.system.entity.SysRoleMenu;
import com.rabbiter.oes.system.mapper.SysRoleMenuMapper;
import com.rabbiter.oes.system.service.SysRoleMenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author JackieLiao
 * @description 针对表【sys_role_menu(角色和菜单关联表)】的数据库操作Service实现
 * @since 2024-05-22 10:23:59
 */
@Service
@AllArgsConstructor
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

}




