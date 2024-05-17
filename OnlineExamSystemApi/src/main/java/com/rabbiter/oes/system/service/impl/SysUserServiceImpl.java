package com.rabbiter.oes.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbiter.oes.common.resp.PageRequest;
import com.rabbiter.oes.system.entity.SysUser;
import com.rabbiter.oes.system.mapper.SysUserMapper;
import com.rabbiter.oes.system.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * 系统用户表(SysUser)表服务实现类
 *
 * @author jackie liao
 * @since 2024-05-17 15:16:01
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    /**
     * 分页查询
     *
     * @param sysUser     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public IPage<SysUser> queryByPage(SysUser sysUser, PageRequest pageRequest) {
        Page<SysUser> page = Page.of(pageRequest.getCurrent(),pageRequest.getSize());
        return baseMapper.queryAllByLimit(page,sysUser);
    }

}
