package com.rabbiter.oes.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbiter.oes.system.entity.SysRolePermission;
import com.rabbiter.oes.system.mapper.SysRolePermissionMapper;
import com.rabbiter.oes.system.service.SysRolePermissionService;
import org.springframework.stereotype.Service;

/**
 * 系统角色权限关联表(SysRolePermission)表服务实现类
 *
 * @author jackie liao
 * @since 2024-05-17 15:20:26
 */
@Service("sysRolePermissionService")
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

}
