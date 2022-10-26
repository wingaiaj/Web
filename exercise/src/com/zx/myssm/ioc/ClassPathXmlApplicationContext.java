package com.zx.myssm.ioc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ClassPathXmlApplicationContext
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/22 21:20
 * @Version 1.0
 */
public class ClassPathXmlApplicationContext implements BeanFactory {

    Map<String, Object> map = new HashMap<>();


    public ClassPathXmlApplicationContext(String path) {

        try {

            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(path);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(resourceAsStream);


            NodeList beans = document.getElementsByTagName("bean");

            for (int i = 0; i < beans.getLength(); i++) {
                Node bean = beans.item(i);
                if (bean.getNodeType() == Node.ELEMENT_NODE) {
                    Element beanElement = (Element) bean;
                    String idStr = beanElement.getAttribute("id");
                    String beanClassStr = beanElement.getAttribute("class");

                    Class<?> beanClass = Class.forName(beanClassStr);
                    Object beanObj = beanClass.newInstance();
                    map.put(idStr, beanObj);
                }
            }

            for (int i = 0; i < beans.getLength(); i++) {
                Node bean = beans.item(i);
                if (bean.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementBean = (Element) bean;
                    String id = elementBean.getAttribute("id");
                    NodeList childNodes = elementBean.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node childNode = childNodes.item(j);
                        if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element elementChildNode = (Element) childNode;

                            String name = elementChildNode.getAttribute("name");
                            String ref = elementChildNode.getAttribute("ref");

                            Object refObj = map.get(ref);
                            Object idObj = map.get(id);

                            Field declaredField = idObj.getClass().getDeclaredField(name);
                            declaredField.setAccessible(true);
                            declaredField.set(idObj, refObj);

                        }

                    }

                }


            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("classPathXmlApplicationContext出错了...");
        }
    }

    @Override
    public Map<String, Object> getBeanMap() {
        return map;
    }

}
