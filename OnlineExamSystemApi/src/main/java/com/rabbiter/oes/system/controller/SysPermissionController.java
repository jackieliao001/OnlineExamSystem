package com.rabbiter.oes.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rabbiter.oes.common.resp.ApiResult;
import com.rabbiter.oes.common.resp.ApiResultHandler;
import com.rabbiter.oes.common.resp.PageRequest;
import com.rabbiter.oes.system.entity.SysPermission;
import com.rabbiter.oes.system.service.SysPermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 系统权限表(SysPermission)表控制层
 *
 * @author jackie liao
 * @since 2024-05-17 15:20:24
 */
@RestController
@RequestMapping("sysPermission")
public class SysPermissionController {
    /**
     * 服务对象
     */
    @Resource
    private SysPermissionService sysPermissionService;

    /**
     * 分页查询
     *
     * @param sysPermission 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    @GetMapping
    public ApiResult<IPage<SysPermission>> queryByPage(SysPermission sysPermission, PageRequest pageRequest) {
        return ApiResultHandler.success(this.sysPermissionService.queryByPage(sysPermission, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ApiResult<SysPermission> queryById(@PathVariable("id") Long id) {
        return ApiResultHandler.success(this.sysPermissionService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysPermission 实体
     * @return 新增结果
     */
    @PostMapping
    public ApiResult<Boolean> add(SysPermission sysPermission) {
        return ApiResultHandler.success(this.sysPermissionService.save(sysPermission));
    }

    /**
     * 编辑数据
     *
     * @param sysPermission 实体
     * @return 编辑结果
     */
    @PutMapping
    public ApiResult<Boolean> edit(SysPermission sysPermission) {
        return ApiResultHandler.success(this.sysPermissionService.updateById(sysPermission));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ApiResult<Boolean> deleteById(Long id) {
        return ApiResultHandler.success(this.sysPermissionService.removeById(id));
    }

}

