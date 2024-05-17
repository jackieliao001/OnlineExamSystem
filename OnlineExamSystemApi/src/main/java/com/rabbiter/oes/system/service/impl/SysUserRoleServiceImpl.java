package com.rabbiter.oes.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbiter.oes.system.entity.SysUserRole;
import com.rabbiter.oes.system.mapper.SysUserRoleMapper;
import com.rabbiter.oes.system.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * 系统用户角色关联表(SysUserRole)表服务实现类
 *
 * @author jackie liao
 * @since 2024-05-17 15:20:27
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

}
