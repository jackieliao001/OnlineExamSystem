package com.rabbiter.oes.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rabbiter.oes.common.enums.UserStatusEnum;
import com.rabbiter.oes.system.entity.SysUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户信息。继承spring security UserDetails
 *
 * @author JackieLiao
 * @package com.rabbiter.oes.common.domain
 * @since 2024/7/1
 */
@Data
@NoArgsConstructor
public class UserDetail implements UserDetails {
    //存储用户信息
    private SysUser user;
    //存储权限信息
    private List<String> permissions;
    //存储SpringSecurity所需要的权限信息的集合
    @JsonIgnore
    private List<GrantedAuthority> authorities;

    public UserDetail(SysUser user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null) {
            return authorities;
        }
        //把permissions中字符串类型的权限信息转换成GrantedAuthority对象存入authorities中
        authorities = permissions.stream().
                map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPwd();
    }

    @Override
    public String getUsername() {
        return user.getAccount();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserStatusEnum.OK.equals(user.getStatus());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !UserStatusEnum.LOCK.equals(user.getStatus());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !UserStatusEnum.DISABLE.equals(user.getStatus());
    }
}
