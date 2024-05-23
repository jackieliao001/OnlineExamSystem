package com.rabbiter.oes.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rabbiter.oes.common.resp.ApiResult;
import com.rabbiter.oes.common.resp.ApiResultHandler;
import com.rabbiter.oes.common.resp.PageRequest;
import com.rabbiter.oes.system.entity.SysRole;
import com.rabbiter.oes.system.service.SysRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 系统角色表(SysRole)表控制层
 *
 * @author jackie liao
 * @since 2024-05-17 15:20:25
 */
@RestController
@RequestMapping("/api/system/role")
public class SysRoleController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleService sysRoleService;

    /**
     * 分页查询
     *
     * @param sysRole     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ApiResult<IPage<SysRole>> queryByPage(SysRole sysRole, PageRequest pageRequest) {
        return ApiResultHandler.success(this.sysRoleService.queryByPage(sysRole, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ApiResult<SysRole> queryById(@PathVariable("id") Long id) {
        return ApiResultHandler.success(this.sysRoleService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysRole 实体
     * @return 新增结果
     */
    @PostMapping
    public ApiResult<Boolean> add(SysRole sysRole) {
        return ApiResultHandler.success(this.sysRoleService.save(sysRole));
    }

    /**
     * 编辑数据
     *
     * @param sysRole 实体
     * @return 编辑结果
     */
    @PutMapping
    public ApiResult<Boolean> edit(SysRole sysRole) {
        return ApiResultHandler.success(this.sysRoleService.updateById(sysRole));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ApiResult<Boolean> deleteById(Long id) {
        return ApiResultHandler.success(this.sysRoleService.removeById(id));
    }

}

