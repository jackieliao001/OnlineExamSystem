package com.rabbiter.oes.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbiter.oes.common.resp.PageRequest;
import com.rabbiter.oes.system.entity.SysRole;
import com.rabbiter.oes.system.mapper.SysRoleMapper;
import com.rabbiter.oes.system.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
 * 系统角色表(SysRole)表服务实现类
 *
 * @author jackie liao
 * @since 2024-05-17 15:20:26
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService{


    /**
     * 分页查询
     *
     * @param sysRole     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public IPage<SysRole> queryByPage(SysRole sysRole, PageRequest pageRequest) {
        IPage<SysRole> page = Page.of(pageRequest.getCurrent(),pageRequest.getSize());
        return baseMapper.queryAllByLimit(page, sysRole);
    }
}
