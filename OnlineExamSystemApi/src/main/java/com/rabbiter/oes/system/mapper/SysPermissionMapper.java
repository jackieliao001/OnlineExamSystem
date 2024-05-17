package com.rabbiter.oes.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rabbiter.oes.system.entity.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统权限表(SysPermission)表数据库访问层
 *
 * @author jackie liao
 * @since 2024-05-17 15:20:25
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 查询指定行数据
     *
     * @param page      分页对象
     * @param sysPermission 查询条件
     * @return 对象列表
     */
    IPage<SysPermission> queryAllByLimit(IPage<SysPermission> page, SysPermission sysPermission);

    /**
     * 统计总行数
     *
     * @param sysPermission 查询条件
     * @return 总行数
     */
    long count(SysPermission sysPermission);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysPermission> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysPermission> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysPermission> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysPermission> entities);
}

