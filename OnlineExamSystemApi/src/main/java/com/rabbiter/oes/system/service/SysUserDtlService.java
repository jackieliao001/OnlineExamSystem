package com.rabbiter.oes.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rabbiter.oes.system.entity.SysUserDtl;

/**
 * 系统用户详情表(SysUserDtl)表服务接口
 *
 * @author jackie liao
 * @since 2024-05-17 15:20:27
 */
public interface SysUserDtlService extends IService<SysUserDtl> {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 用户主键
     * @return 实例对象
     */
    SysUserDtl queryByUserId(Long userId);

    /**
     * 新增数据
     *
     * @param SysUserDtl 实例对象
     * @return 实例对象
     */
    SysUserDtl saveByUserId(SysUserDtl SysUserDtl);

    /**
     * 修改数据
     *
     * @param SysUserDtl 实例对象
     * @return 实例对象
     */
    SysUserDtl updateByUserId(SysUserDtl SysUserDtl);

    /**
     * 通过主键删除数据
     *
     * @param userId 用户主键
     * @return 是否成功
     */
    boolean deleteByUserId(Long userId);

}
