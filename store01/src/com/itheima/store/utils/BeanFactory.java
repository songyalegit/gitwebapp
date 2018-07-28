package com.itheima.store.utils;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class BeanFactory {

	public static Object createBean(String name){
		Object obj=null;
		try {
			SAXReader reader = new SAXReader();
			InputStream is=BeanFactory.class.getClassLoader().getResourceAsStream("bean.xml");
			Document document=reader.read(is);
			Element rootElement=document.getRootElement();
			List<Element> elements=rootElement.elements();
			for (Element element : elements) {
				String keyname=element.attributeValue("id");
				if (keyname.equals(name)) {
					String path=element.attributeValue("class");
					obj=Class.forName(path).newInstance();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
