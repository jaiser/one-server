/**
 * Copyright (C), 2019, 中电福富信息科技有限公司
 * FileName: JWTAuthenticationFilter
 * Author:   xujiajun
 * Date:     2019/9/6 19:15
 */
package com.jaiser.oneserver.security.jwt;

import com.jaiser.oneserver.utils.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Description:
 * @Author xujiajun
 * @Date 2019/9/6  19:15
 */
public class JWTAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain)
        throws IOException, ServletException {
        Authentication authentication = JwtUtil
            .getAuthentication((HttpServletRequest)request);
        SecurityContextHolder.getContext()
            .setAuthentication(authentication);
        filterChain.doFilter(request,response);
    }

}
