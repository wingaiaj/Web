package com.zx.myssm.springmvc;

import com.zx.myssm.Utils.StringUtils;
import com.zx.myssm.ioc.BeanFactory;
import com.zx.myssm.ioc.ClassPathXmlApplicationContext;
import com.zx.myssm.view.ViewBaseServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * @ClassName DispatchServlet
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/22 21:22
 * @Version 1.0
 */
@WebServlet("*.do")
public class DispatchServlet extends ViewBaseServlet {
    Map<String, Object> beanMap = null;


    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();

        Object beanFactoryObj = servletContext.getAttribute("beanFactory");
        if (beanFactoryObj != null) {
            BeanFactory beanFactory = (BeanFactory) beanFactoryObj;
            beanMap = beanFactory.getBeanMap();
        }
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String servletPath = req.getServletPath();
        String substringPath1 = servletPath.substring(1);
        int dotIndex = substringPath1.lastIndexOf(".do");

        String ControllerName = substringPath1.substring(0, dotIndex);

        Object ControllerObj = beanMap.get(ControllerName);

        String operate = req.getParameter("operate");
        if (StringUtils.isEmpty(operate)) {
            operate = "index";
        }
        Method[] declaredMethods = ControllerObj.getClass().getDeclaredMethods();

        try {
            for (Method declaredMethod : declaredMethods) {
                if (declaredMethod.getName().equals(operate)) {

                    Parameter[] parameters = declaredMethod.getParameters();

                    Object[] parameterValues = new Object[parameters.length];

                    for (int i = 0; i < parameters.length; i++) {
                        String parameterName = parameters[i].getName();
                        if ("request".equals(parameterName)) {
                            parameterValues[i] = req;
                        } else if ("response".equals(parameterName)) {
                            parameterValues[i] = resp;
                        } else if ("session".equals(parameterName)) {
                            parameterValues[i] = req.getSession();
                        } else {
                            String pTypeName = parameters[i].getType().getName();
                            String parameter = req.getParameter(parameterName);
                            Object objValue = parameter;
                            if ("java.lang.Integer".equals(pTypeName) && parameter != null) {
                                objValue = Integer.parseInt(parameter);

                            }
                            parameterValues[i] = objValue;
                        }
                    }
                    declaredMethod.setAccessible(true);
                    Object returnRe = declaredMethod.invoke(ControllerObj, parameterValues);
                    String returnResult = (String) returnRe;
                    if (returnResult.startsWith("Redirect:")) {
                        String subRe = returnResult.substring("Redirect:".length());
                        resp.sendRedirect(subRe);
                    } else {
                        super.processTemplate(returnResult, req, resp);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DispatchServletException("Dispatch出错了");
        }

    }


}
