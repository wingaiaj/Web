package com.zx.myssm.filter;

import com.zx.myssm.trans.TransactionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.sql.SQLException;

/**
 * @ClassName OpenSessionInFilter
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/21 20:25
 * @Version 1.0
 */
@WebFilter("*.do")
public class OpenSessionInFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {

        try {
            //开启事务
            TransactionManager.beginTrans();

            filterChain.doFilter(servletRequest, servletResponse);

            //提交事务
            TransactionManager.commit();
        } catch (Exception e) {
            try {
                //回滚事务
               TransactionManager.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
