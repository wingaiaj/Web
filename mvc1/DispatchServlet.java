package com.zx.mySpring.dispatchservlet;

import com.zx.mySpring.Utils.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName DispatchServlet
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/17 9:15
 * @Version 1.0
 */
@WebServlet("*.do")
public class DispatchServlet extends HttpServlet {
    Map<String, Object> beanMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        try {
            //初始化 中加载application配置文件
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(resourceAsStream);
            //获取xml中标签名
            NodeList beanList = document.getElementsByTagName("bean");
            for (int i = 0; i < beanList.getLength(); i++) {
                Node beanItem = beanList.item(i);
                //判断此节点类型 为元素节点
                if (beanItem.getNodeType() == Node.ELEMENT_NODE) {
                    //转换元素类型
                    Element beanElement = (Element) beanItem;
                    //通过名称获取保存作用域的值
                    String beanIdStr = beanElement.getAttribute("id");
                    String beanClassStr = beanElement.getAttribute("class");
                    //反射创建类的实例
                    //根据xml bean标签中的 class 获取 Class实例
                    Class<?> beanClass = Class.forName(beanClassStr);
                    //创建对象
                    Object beanObj = beanClass.newInstance();

                    Method setServletContextMethod = beanClass.getDeclaredMethod("setServletContext", ServletContext.class);
                    setServletContextMethod.invoke(beanObj, this.getServletContext());

                    //添加到map中
                    beanMap.put(beanIdStr, beanObj);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {  //设置字符集
            req.setCharacterEncoding("utf-8");
            //截取请求字符串
            String servletPath = req.getServletPath();
            String pathS = servletPath.substring(1);
            int doIndex = pathS.lastIndexOf(".do");
            String pathId = pathS.substring(0, doIndex);
            //找到map中对应的类
            Object o = beanMap.get(pathId);
            //获取隐藏域参数
            String operate = req.getParameter("operate");
            //为空 默认值 为 index
            if (StringUtils.isEmpty(operate)) {
                operate = "index";
            }
            Method declaredMethod = o.getClass().getDeclaredMethod(operate, HttpServletRequest.class, HttpServletResponse.class);
            if (null != declaredMethod) {
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(o, req, resp);
            } else {
                new RuntimeException("找不到此方法");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
