package com.zx.myssm.springmvc;

import com.zx.myssm.ioc.BeanFactory;
import com.zx.myssm.ioc.ClassPathXmlApplicationContext;
import com.zx.myssm.Utils.StringUtils;
import com.zx.myssm.view.ViewBaseServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * @ClassName DispatchServlet
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/17 9:15
 * @Version 1.0
 */
@WebServlet("*.do")
public class DispatchServlet extends ViewBaseServlet {

    BeanFactory beanFactory;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext application = getServletContext();
        Object beanFactoryObj = application.getAttribute("beanFactory");
        if (null != beanFactoryObj) {
            beanFactory = (BeanFactory) beanFactoryObj;
        } else{
          throw new RuntimeException("IOC容器出错了");
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        //截取请求字符串
        String servletPath = req.getServletPath();
        String pathS = servletPath.substring(1);
        int doIndex = pathS.lastIndexOf(".do");
        String pathId = pathS.substring(0, doIndex);
        //找到map中对应的类
        Map<String, Object> beanMap = beanFactory.getBeanMap();
        Object o = beanMap.get(pathId);
        //获取隐藏域参数
        String operate = req.getParameter("operate");
        //为空 默认值 为 index
        if (StringUtils.isEmpty(operate)) {
            operate = "index";
        }
        try {

            //获取所有方法
            Method[] declaredMethods = o.getClass().getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                if (operate.equals(declaredMethod.getName())) {
                    //获取方法的参数
                    Parameter[] parameters = declaredMethod.getParameters();
                    Object[] parameterValues = new Object[parameters.length];
                    //多个参数类型for循环判断
                    for (int i = 0; i < parameters.length; i++) {
                        //参数名称
                        String parameterName = parameters[i].getName();
                        //如果参数名是req 传入req
                        if ("req".equals(parameterName)) {
                            parameterValues[i] = req;
                            //如果参数名是resp 传入resp
                        } else if ("resp".equals(parameterName)) {
                            parameterValues[i] = resp;
                            //如果参数名是session 传入session
                        } else if ("session".equals(parameterName)) {
                            parameterValues[i] = req.getSession();
                        } else {
                            //基本 参数 integer string...
                            String parameter = req.getParameter(parameterName);
                            //因为数据类型不同  创建obj变量存储
                            Object objValue = new Object();
                            //获取参数名的类型名
                            String TypeName = parameters[i].getType().getName();
                            //String类型 直接存入
                            objValue = parameter;
                            //参数类型为integer 转换为Integer 存入obj
                            if ("java.lang.Integer".equals(TypeName) && StringUtils.isNotEmpty(parameter)) {
                                objValue = Integer.parseInt(parameter);
                            }
                            // objValue  转换过 和 没转换的值存入 objValues数组
                            parameterValues[i] = objValue;
                        }
                    }
                    //调用控制器中对应的方法 接受返回值  并且执行 视图处理 重定向 和内部转发
                    declaredMethod.setAccessible(true);
                    //传入赋值好的参数 反射调用方法 获取返回值
                    Object invoke = declaredMethod.invoke(o, parameterValues);
                    String returnValue = (String) invoke;
                    //判断返回值 决定调用 图处理 重定向 和内部转发 方法
                    if (returnValue.startsWith("Redirect:")) {
                        String trValue = returnValue.substring("Redirect:".length());
                        resp.sendRedirect(trValue);
                    } else {
                        super.processTemplate(returnValue, req, resp);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DispatchServletException("dispatchServlet出错了");
        }
    }
}
