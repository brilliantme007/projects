package org.wanholi.erw.pub;

import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
 * 构建XML文件的公共父类
 * @author lifei
 * @version 1.0
 * 2017.11.3
 */
public class XXML {
	protected Document XMLD;
	protected Element root;
	protected XXML(){};

	public void setAttribute(String key,String value){
		root.setAttribute(key, value);

	}
	public Element addElement(String tagName,Map<String, String> attrs){
		Element element=XMLD.createElement(tagName);
                if(attrs!=null){
                    for(String key:attrs.keySet()){
			element.setAttribute(key, attrs.get(key));
                    }
                }
		root.appendChild(element);
                return element;
	}
        public Element addElement(String tagName){
            return addElement(tagName, null);
        }
        public void addElement(Element element){
            root.appendChild(element);
        }
	public Document getDocument(){
		return XMLD;
	}
}
