package com.rabbiter.oes.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rabbiter.oes.common.resp.ApiResult;
import com.rabbiter.oes.common.resp.ApiResultHandler;
import com.rabbiter.oes.common.resp.PageRequest;
import com.rabbiter.oes.system.entity.SysUser;
import com.rabbiter.oes.system.service.SysUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 系统用户表(SysUser)表控制层
 *
 * @author jackie liao
 * @since 2024-05-17 15:15:45
 */
@RestController
@RequestMapping("/api/system/user")
@AllArgsConstructor
public class SysUserController {
    private final SysUserService sysUserService;

    /**
     * 分页查询
     *
     * @param sysUser 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ApiResult<IPage<SysUser>> queryByPage(SysUser sysUser, PageRequest pageRequest) {
        return ApiResultHandler.success(sysUserService.queryByPage(sysUser, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ApiResult<SysUser> queryById(@PathVariable("id") Long id) {
        return ApiResultHandler.success(sysUserService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysUser 实体
     * @return 新增结果
     */
    @PostMapping
    public ApiResult<Boolean> add(SysUser sysUser) {
        return ApiResultHandler.success(sysUserService.save(sysUser));
    }

    /**
     * 编辑数据
     *
     * @param sysUser 实体
     * @return 编辑结果
     */
    @PutMapping
    public ApiResult<Boolean> edit(SysUser sysUser) {
        return ApiResultHandler.success(sysUserService.updateById(sysUser));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ApiResult<Boolean> deleteById(Long id) {
        return ApiResultHandler.success(sysUserService.removeById(id));
    }

}

