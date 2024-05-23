package com.rabbiter.oes.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rabbiter.oes.system.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * 系统用户表(SysUser)表数据库访问层
 *
 * @author jackie liao
 * @since 2024-05-17 15:15:45
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 查询指定行数据
     *
     * @param page    分页对象
     * @param sysUser 查询条件
     * @return 对象列表
     */
    IPage<SysUser> queryAllByLimit(IPage<SysUser> page, SysUser sysUser);

    /**
     * 修改用户头像
     *
     * @param account 账号
     * @param avatar 头像地址
     * @return 结果
     */
    int updateUserAvatar(@Param("account") String account, @Param("avatar") String avatar);


    /**
     * 校验用户名称是否唯一
     *
     * @param account 用户名称
     * @return 结果
     */
    SysUser checkAccountUnique(@Param("account") String account);

    /**
     * 校验手机号码是否唯一
     *
     * @param tel 手机号码
     * @return 结果
     */
    SysUser checkPhoneUnique(@Param("tel") String tel);

    /**
     * 校验email是否唯一
     *
     * @param email 用户邮箱
     * @return 结果
     */
    SysUser checkEmailUnique(@Param("email") String email);
}