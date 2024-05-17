package com.rabbiter.oes.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rabbiter.oes.common.resp.PageRequest;
import com.rabbiter.oes.system.entity.SysUser;

/**
 * 系统用户表(SysUser)表服务接口
 *
 * @author jackie liao
 * @since 2024-05-17 15:16:01
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 分页查询
     *
     * @param sysUser     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    IPage<SysUser> queryByPage(SysUser sysUser, PageRequest pageRequest);
}
