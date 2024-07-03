package com.rabbiter.oes.common.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;

/**
 * spring security 配置
 *
 * @author JackieLiao
 * @description Spring Security 5.7.0-M2后弃用了 WebSecurityConfigurerAdapter ，转向使用基于组件的安全配置
 * @package com.rabbiter.oes.common.core.config
 * @since 2024/7/1
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)// 开启权限认证功能
public class SecurityConfig {
    @Resource
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Resource
    private AccessDeniedHandler accessDeniedHandler;
    @Resource
    private LoginSuccessHandler loginSuccessHandler;
    @Resource
    private LoginFailureHandler loginFailureHandler;
    @Resource
    private LogoutSuccessHandler logoutSuccessHandler;


    /**
     * 密码明文加密方式配置
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 权限认证对象[AuthenticationManager]注册到容器里面，其他类可以取到
     *
     * @return
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * 配置 HttpSecurity
     *
     * @param http http
     * @return SecurityFilterChain
     * @throws Exception e
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                // 禁用session  sessionCreationPolicy session创建策略
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests((auth) -> {
                    // 对于登录接口 允许匿名访问
                    auth.antMatchers("/login", "/logout", "/register", "/captcha")
                            .permitAll()
                            // 除上面外的所有请求全部需要鉴权认证
                            .anyRequest()
                            .authenticated();
                }).formLogin((form) -> {
                            form.successHandler(loginSuccessHandler).failureHandler(loginFailureHandler);
                        }
                ).logout((logout) -> {
                            //配置注销成功处理器
                            logout.logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
                        }
                ).exceptionHandling((ex) -> { //配置异常处理器
                    //认证失败处理器
                    ex.authenticationEntryPoint(authenticationEntryPoint)
                            //授权失败处理器
                            .accessDeniedHandler(accessDeniedHandler);
                })
                //跨域
                .cors();


//        http.formLogin().successHandler(loginSuccessHandler).failureHandler(loginFailureHandler);
        //配置注销成功处理器
/*        http.logout()
                .logoutUrl("/system/logout")
                .logoutSuccessHandler(logoutSuccessHandler);*/

        //添加过滤器
//        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //配置异常处理器
/*        http.exceptionHandling()
                //配置认证失败处理器
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);*/


//        http.cors();
        return http.build();
    }

}