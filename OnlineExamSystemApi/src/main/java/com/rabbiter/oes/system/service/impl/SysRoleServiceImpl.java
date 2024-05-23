package com.rabbiter.oes.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbiter.oes.common.resp.PageRequest;
import com.rabbiter.oes.system.entity.SysRole;
import com.rabbiter.oes.system.mapper.SysRoleMapper;
import com.rabbiter.oes.system.service.SysRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 系统角色表(SysRole)表服务实现类
 *
 * @author jackie liao
 * @since 2024-05-17 15:20:26
 */
@Service("sysRoleService")
@AllArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    /**
     * 分页查询
     *
     * @param sysRole     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public IPage<SysRole> queryByPage(SysRole sysRole, PageRequest pageRequest) {
        IPage<SysRole> page = Page.of(pageRequest.getCurrent(), pageRequest.getSize());
        return baseMapper.queryAllByLimit(page, sysRole);
    }

    /**
     * 根据用户ID查询角色权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectRolePermissionByUserId(Long userId) {
        List<SysRole> perms = baseMapper.selectRolePermissionByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms) {
            permsSet.addAll(StrUtil.split(perm.getRoleKey().trim(), StrUtil.COMMA));
        }
        return permsSet;
    }

    /**
     * 根据用户ID获取角色选择框列表
     *
     * @param userId 用户ID
     * @return 选中角色ID列表
     */
    @Override
    public List<Long> selectRoleListByUserId(Long userId) {
        return baseMapper.selectRoleListByUserId(userId);
    }
}
