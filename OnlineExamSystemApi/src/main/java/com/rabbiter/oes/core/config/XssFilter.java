package com.rabbiter.oes.core.config;

import com.rabbiter.oes.core.xss.XssWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * xss攻击过滤器
 *
 * @author JackieLiao
 * @package com.rabbiter.oes.core.config
 * @since 2024/6/28
 */
@Slf4j
@Component
public class XssFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        log.info("uri:{}", req.getRequestURI());
        // xss 过滤
        chain.doFilter(new XssWrapper(req), resp);
    }
}
