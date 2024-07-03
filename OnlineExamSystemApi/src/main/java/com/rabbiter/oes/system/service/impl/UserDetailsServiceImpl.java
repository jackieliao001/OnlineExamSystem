package com.rabbiter.oes.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rabbiter.oes.common.domain.UserDetail;
import com.rabbiter.oes.common.enums.ResponseCode;
import com.rabbiter.oes.common.enums.UserStatusEnum;
import com.rabbiter.oes.common.exception.ApiException;
import com.rabbiter.oes.system.entity.SysUser;
import com.rabbiter.oes.system.mapper.SysUserMapper;
import com.rabbiter.oes.system.service.SysPermissionService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author JackieLiao
 * @package com.rabbiter.oes.system.service.impl
 * @since 2024/7/1
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        SysUser user = sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getAccount, username));
        //如果查询不到数据就通过抛出异常来给出提示
        if (Objects.isNull(user)) {
            throw new ApiException(ResponseCode.ACCOUNT_PASSWORD_ERROR);
        }
        if (user.getStatus().equals(UserStatusEnum.DISABLE)) {
            throw new ApiException(ResponseCode.ACCOUNT_DISABLED);
        }
        //根据用户查询权限信息 添加到LoginUser中
        Set<String> permissionKeyList = permissionService.getMenuPermission(user.getUserId());
        List<String> list = new ArrayList<>(permissionKeyList);
        //封装成UserDetails对象返回
        return new UserDetail(user, list);
    }
}
