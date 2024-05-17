package com.rabbiter.oes.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbiter.oes.system.entity.SysUserDtl;
import com.rabbiter.oes.system.mapper.SysUserDtlMapper;
import com.rabbiter.oes.system.service.SysUserDtlService;
import org.springframework.stereotype.Service;

/**
 * 系统用户详情表(SysUserDtl)表服务实现类
 *
 * @author jackie liao
 * @since 2024-05-17 15:20:27
 */
@Service("sysUserDtlService")
public class SysUserDtlServiceImpl extends ServiceImpl<SysUserDtlMapper, SysUserDtl> implements SysUserDtlService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public SysUserDtl queryByUserId(Long userId) {
        return this.baseMapper.queryByUserId(userId);
    }


    /**
     * 新增数据
     *
     * @param SysUserDtl 实例对象
     * @return 实例对象
     */
    @Override
    public SysUserDtl saveByUserId(SysUserDtl SysUserDtl) {
        this.baseMapper.saveByUserId(SysUserDtl);
        return SysUserDtl;
    }

    /**
     * 修改数据
     *
     * @param SysUserDtl 实例对象
     * @return 实例对象
     */
    @Override
    public SysUserDtl updateByUserId(SysUserDtl SysUserDtl) {
        return baseMapper.updateByUserId(SysUserDtl);
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteByUserId(Long userId) {
        return this.baseMapper.deleteByUserId(userId) > 0;
    }
}
