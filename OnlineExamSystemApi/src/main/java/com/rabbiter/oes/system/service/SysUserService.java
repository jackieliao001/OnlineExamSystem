package com.rabbiter.oes.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rabbiter.oes.common.resp.ApiResult;
import com.rabbiter.oes.common.resp.PageRequest;
import com.rabbiter.oes.system.entity.SysUser;
import com.rabbiter.oes.system.vo.LoginUserInfo;

/**
 * 系统用户表(SysUser)表服务接口
 *
 * @author jackie liao
 * @since 2024-05-17 15:16:01
 */
public interface SysUserService extends IService<SysUser> {

    String generateNewAccount(String phone);

    /**
     * 分页查询
     *
     * @param sysUser     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    IPage<SysUser> queryByPage(SysUser sysUser, PageRequest pageRequest);


    /**
     * 校验用户名称是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    boolean checkAccountUnique(SysUser user);

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    boolean checkPhoneUnique(SysUser user);

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    boolean checkEmailUnique(SysUser user);

    /**
     * 修改用户头像
     *
     * @param account 账号
     * @param avatar  头像地址
     * @return 结果
     */
    int updateUserAvatar(String account, String avatar);


    /**
     * 重置用户密码
     *
     * @param user 用户信息
     * @return 结果
     */
    int resetPwd(SysUser user);

    /**
     * 重置用户密码
     *
     * @param account  账号
     * @param password 密码
     * @return 结果
     */
    int resetUserPwd(String account, String password);

    /**
     * 获取当前登录用户信息
     *
     * @return 当前登录用户信息
     */
    ApiResult<LoginUserInfo> getCurrUserInfo();
}
