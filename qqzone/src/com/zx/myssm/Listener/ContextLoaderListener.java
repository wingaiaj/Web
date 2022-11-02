package com.zx.myssm.Listener;

import com.zx.myssm.ioc.BeanFactory;
import com.zx.myssm.ioc.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @ClassName ContextLoaderListener
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/22 15:43
 * @Version 1.0
 */
@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //配置
        ServletContext application = sce.getServletContext();

        String path = application.getInitParameter("contextConfigLocation");

        BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);

        application.setAttribute("beanFactory", beanFactory);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
