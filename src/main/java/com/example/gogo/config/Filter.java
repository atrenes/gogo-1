package com.example.gogo.config;

import com.example.gogo.util.TokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
public class Filter extends GenericFilterBean {
    private final String AUTHORIZATION = "Authorization";

    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = ((HttpServletRequest)servletRequest).getHeader(AUTHORIZATION);
        if (token != null && tokenUtil.validateToken(token)) {
            String userLogin = tokenUtil.getLoginFromToken(token);
            UserDetails account = customUserDetailService.loadUserByUsername(userLogin);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(account, null, account.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
