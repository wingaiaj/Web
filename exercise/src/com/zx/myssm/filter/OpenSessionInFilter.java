package com.zx.myssm.filter;

import com.zx.myssm.Utils.Connections;
import com.zx.myssm.Utils.StringUtils;
import com.zx.myssm.trans.TransactionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName OpenSessionInFilter
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/22 21:19
 * @Version 1.0
 */
@WebFilter("*.do")
public class OpenSessionInFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            TransactionManager.TransactionBegin();
            filterChain.doFilter(servletRequest, servletResponse);
            TransactionManager.TransactionCommit();
        } catch (Exception e) {
            TransactionManager.TransactionRollback();
            e.printStackTrace();
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
