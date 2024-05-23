package com.rabbiter.oes.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rabbiter.oes.system.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author JackieLiao
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Mapper @see com.rabbiter.oes.system.entity.SysMenu
 * @since 2024-05-22 10:23:59
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<String> selectMenuPermsByUserId(@Param("userId") Long userId);

    List<String> selectMenuPermsByRoleId(@Param("roleId") Long roleId);
}