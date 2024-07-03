package com.rabbiter.oes.system.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbiter.oes.common.resp.ApiResult;
import com.rabbiter.oes.common.resp.PageRequest;
import com.rabbiter.oes.common.util.SecurityUtils;
import com.rabbiter.oes.system.entity.SysUser;
import com.rabbiter.oes.system.mapper.SysUserMapper;
import com.rabbiter.oes.system.service.SysUserService;
import com.rabbiter.oes.system.vo.LoginUserInfo;
import org.springframework.stereotype.Service;

/**
 * 系统用户表(SysUser)表服务实现类
 *
 * @author jackie liao
 * @since 2024-05-17 15:16:01
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Override
    public String generateNewAccount(String phone) {
        String account;

        // 校验是否可用
        boolean exists;
        do {
            account = "DM_" + (StrUtil.isBlank(phone) ? IdUtil.nanoId(16) : phone);
            exists = checkAccountUnique(account);
        } while (exists);
        return account;
    }

    public boolean checkAccountUnique(String account) {
        // 校验是否可用
        return baseMapper.exists(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getAccount, account));
    }
    /**
     * 分页查询
     *
     * @param sysUser     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public IPage<SysUser> queryByPage(SysUser sysUser, PageRequest pageRequest) {
        Page<SysUser> page = Page.of(pageRequest.getCurrent(), pageRequest.getSize());
        return baseMapper.queryAllByLimit(page, sysUser);
    }

    @Override
    public boolean checkAccountUnique(SysUser user) {
        long userId = null == user.getUserId() ? -1L : user.getUserId();
        SysUser info = baseMapper.checkAccountUnique(user.getAccount());
        return null == info || info.getUserId() == userId;
    }

    @Override
    public boolean checkPhoneUnique(SysUser user) {
        long userId = null == user.getUserId() ? -1L : user.getUserId();
        SysUser info = baseMapper.checkPhoneUnique(user.getAccount());
        return null == info || info.getUserId() == userId;
    }

    @Override
    public boolean checkEmailUnique(SysUser user) {
        long userId = null == user.getUserId() ? -1L : user.getUserId();
        SysUser info = baseMapper.checkEmailUnique(user.getAccount());
        return null == info || info.getUserId() == userId;
    }

    @Override
    public int updateUserAvatar(String userName, String avatar) {
        return baseMapper.updateUserAvatar(userName, avatar);
    }

    @Override
    public int resetPwd(SysUser user) {
        return baseMapper.update(lambdaUpdate().set(SysUser::getPwd, user.getPwd()).eq(SysUser::getUserId, user.getUserId()));
    }

    @Override
    public int resetUserPwd(String account, String password) {
        return baseMapper.update(lambdaUpdate().set(SysUser::getPwd, password).eq(SysUser::getAccount, account));
    }

    @Override
    public ApiResult<LoginUserInfo> getCurrUserInfo() {
        SecurityUtils.getCurrentUser();
        return null;
    }

}
