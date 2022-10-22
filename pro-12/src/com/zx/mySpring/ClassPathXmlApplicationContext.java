package com.zx.mySpring;

import javassist.bytecode.analysis.FramePrinter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ClassPathXmlApplicationContext
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/19 21:20
 * @Version 1.0
 */
public class ClassPathXmlApplicationContext implements BeanFactory {
    Map<String, Object> beanMap = new HashMap<>();

    @Override
    public Map<String, Object> getBeanMap() {
        return beanMap;
    }


    public ClassPathXmlApplicationContext() {
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

                if (beanItem.getNodeType() == Node.ELEMENT_NODE) {

                    Element beanElement = (Element) beanItem;

                    String beanIdStr = beanElement.getAttribute("id");
                    String beanClassStr = beanElement.getAttribute("class");


                    Class<?> beanClass = Class.forName(beanClassStr);

                    Object beanObj = beanClass.newInstance();


                    beanMap.put(beanIdStr, beanObj);
                }
            }
            for (int i = 0; i < beanList.getLength(); i++) {
                Node beanNode = beanList.item(i);
                if (beanNode.getNodeType() == Element.ELEMENT_NODE) {

                    Element beanElement = (Element) beanNode;

                    String id = beanElement.getAttribute("id");

                    NodeList childNodes = beanElement.getChildNodes();

                    for (int j = 0; j < childNodes.getLength(); j++) {

                        Node childNode = childNodes.item(j);

                        if (childNode.getNodeType() == Element.ELEMENT_NODE) {

                            Element childNodeElement = (Element) childNode;

                            String ref = childNodeElement.getAttribute("ref");
                            String name = childNodeElement.getAttribute("name");

                            Object refObj = beanMap.get(ref);
                            Object beanObj = beanMap.get(id);

                            Field declaredField = null;

                            try {
                                declaredField = beanObj.getClass().getDeclaredField(name);
                                declaredField.setAccessible(true);
                                declaredField.set(beanObj, refObj);
                            } catch (NoSuchFieldException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
