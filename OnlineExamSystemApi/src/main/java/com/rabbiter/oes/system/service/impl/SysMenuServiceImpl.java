package com.rabbiter.oes.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbiter.oes.system.entity.SysMenu;
import com.rabbiter.oes.system.mapper.SysMenuMapper;
import com.rabbiter.oes.system.service.SysMenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author JackieLiao
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
 * @since 2024-05-22 10:23:59
 */
@Service
@AllArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Override
    public Set<String> selectMenuPermsByUserId(Long userId) {
        List<String> perms = baseMapper.selectMenuPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StrUtil.isNotBlank(perm)) {
                permsSet.addAll(StrUtil.split(perm.trim(), StrUtil.COMMA));
            }
        }
        return permsSet;
    }

    @Override
    public Set<String> selectMenuPermsByRoleId(Long roleId) {
        List<String> perms = baseMapper.selectMenuPermsByRoleId(roleId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StrUtil.isNotBlank(perm)) {
                permsSet.addAll(StrUtil.split(perm.trim(), StrUtil.COMMA));
            }
        }
        return permsSet;
    }
}




