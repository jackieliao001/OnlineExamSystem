package com.rabbiter.oes.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rabbiter.oes.common.resp.PageRequest;
import com.rabbiter.oes.system.entity.SysPermission;

/**
 * 系统权限表(SysPermission)表服务接口
 *
 * @author jackie liao
 * @since 2024-05-17 15:20:25
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 分页查询
     *
     * @param sysPermission 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    IPage<SysPermission> queryByPage(SysPermission sysPermission, PageRequest pageRequest);

}
