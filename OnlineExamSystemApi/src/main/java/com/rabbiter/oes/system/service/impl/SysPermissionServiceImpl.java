package com.rabbiter.oes.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbiter.oes.common.resp.PageRequest;
import com.rabbiter.oes.system.entity.SysPermission;
import com.rabbiter.oes.system.mapper.SysPermissionMapper;
import com.rabbiter.oes.system.service.SysPermissionService;
import org.springframework.stereotype.Service;


/**
 * 系统权限表(SysPermission)表服务实现类
 *
 * @author jackie liao
 * @since 2024-05-17 15:20:25
 */
@Service("sysPermissionService")
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    /**
     * 分页查询
     *
     * @param sysPermission 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    @Override
    public IPage<SysPermission> queryByPage(SysPermission sysPermission, PageRequest pageRequest) {
        IPage<SysPermission> page = Page.of(pageRequest.getCurrent(), pageRequest.getSize());
        return this.baseMapper.queryAllByLimit(page, sysPermission);
    }
}
