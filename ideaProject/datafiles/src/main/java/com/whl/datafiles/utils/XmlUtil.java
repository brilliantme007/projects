package com.whl.datafiles.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;
import java.util.Map;

public class XmlUtil {

    /**
     * DOM方式创建xml文件
     * @param file 文件
     * @throws Exception
     */
    public static void DOMcreate(File file,String tableName, List<Map<String,String>> params)throws Exception{
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        DocumentBuilder db=dbf.newDocumentBuilder();
        if(!file.exists()){
            file.createNewFile();
        }else{
            Document doc=db.parse(file);
            NodeList nodeList = doc.getElementsByTagName(tableName);
            int length = nodeList.getLength();
            Element item = (Element) nodeList.item(length - 1);
        }
        Document document=db.newDocument();
        document.setXmlStandalone(true);
        Element root=document.createElement(tableName);
        for (int i = 0; i < params.size(); i++) {
            Element item=document.createElement("item");
            Map<String, String> stringStringMap = params.get(i);
            if(stringStringMap!=null&&stringStringMap.size()>0){
                for (Map.Entry entryset:
                     stringStringMap.entrySet()) {
                    Element element=document.createElement(String.valueOf(entryset.getKey()));
                    element.setTextContent(String.valueOf(entryset.getValue()));
                    item.appendChild(element);
                }
            }
            root.appendChild(item);
        }
        document.appendChild(root);
        TransformerFactory tff=TransformerFactory.newInstance();
        Transformer tf=tff.newTransformer();
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.transform(new DOMSource(document), new StreamResult(file));
    }
}
