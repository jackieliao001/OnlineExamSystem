package com.rabbiter.oes.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rabbiter.oes.system.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统角色表(SysRole)表数据库访问层
 *
 * @author jackie liao
 * @since 2024-05-17 15:20:25
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 查询指定行数据
     *
     * @param page 分页对象
     * @param sysRole  查询条件
     * @return 对象列表
     */
    IPage<SysRole> queryAllByLimit(IPage<SysRole> page, SysRole sysRole);

    /**
     * 统计总行数
     *
     * @param sysRole 查询条件
     * @return 总行数
     */
    long count(SysRole sysRole);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysRole> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysRole> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysRole> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysRole> entities);
}

