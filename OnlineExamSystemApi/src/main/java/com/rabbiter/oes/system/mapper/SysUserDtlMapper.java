package com.rabbiter.oes.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rabbiter.oes.system.entity.SysUserDtl;
import org.apache.ibatis.annotations.Param;

/**
 * 系统用户详情表(SysUserDtl)表数据库访问层
 *
 * @author jackie liao
 * @since 2024-05-17 15:20:27
 */
public interface SysUserDtlMapper extends BaseMapper<SysUserDtl> {

    SysUserDtl queryByUserId(@Param("userId") Long userId);

    boolean saveByUserId(SysUserDtl sysUserDtl);

    SysUserDtl updateByUserId(SysUserDtl sysUserDtl);

    int deleteByUserId(@Param("userId") Long userId);
}