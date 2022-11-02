package com.zx.myssm.filter;

import com.zx.myssm.Utils.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName CharacterEncodingFilter
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/20 20:59
 * @Version 1.0
 */
@WebFilter(urlPatterns = "*.do", initParams = {@WebInitParam(name = "encoding", value = "utf-8")})
public class CharacterEncodingFilter implements Filter {
    String character = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encoding = filterConfig.getInitParameter("encoding");

        if (StringUtils.isNotEmpty(encoding)) {
            character = encoding;
        }

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ((HttpServletRequest) servletRequest).setCharacterEncoding(character);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
