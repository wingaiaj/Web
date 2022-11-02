package com.zx.myssm.ioc;

import com.zx.myssm.Utils.StringUtils;
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
        this("applicationContext.xml");
    }

    public ClassPathXmlApplicationContext(String path) {
        try {
            if(StringUtils.isEmpty(path)){
                throw new RuntimeException("ioc容器配置出错了....");
            }
            //初始化 中加载application配置文件
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(path);
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


                                declaredField = beanObj.getClass().getDeclaredField(name);
                                declaredField.setAccessible(true);
                                declaredField.set(beanObj, refObj);

                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
