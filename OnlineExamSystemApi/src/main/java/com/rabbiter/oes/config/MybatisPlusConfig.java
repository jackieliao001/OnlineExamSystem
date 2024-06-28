package com.rabbiter.oes.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.rabbiter.oes.*.mapper")
public class MybatisPlusConfig {

    /**
     * 添加MybatisPlus插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); // 分页
//        interceptor.addInnerInterceptor(new IllegalSQLInnerInterceptor()); // 非法SQL拦截
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor()); // 防止全表更新与删除
        return interceptor;
    }
}