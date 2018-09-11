package org.wanholi.erw.word;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.wanholi.erw.pub.XXML;
/**
 * 2017.11.13
 * @author lifei
 * @version 1.0
 */
public class ContentTypes extends XXML{
        String path="[Content_Types].xml";
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	ContentTypes() throws ParserConfigurationException {
		super();
		DocumentBuilder db = dbf.newDocumentBuilder();
		XMLD =  db.newDocument();
		XMLD.setXmlStandalone(true);
		root=XMLD.createElement("Types");
		XMLD.appendChild(root);
	}
	void build(){
		setAttribute("xmlns", "http://schemas.openxmlformats.org/package/2006/content-types");
		Map<String,String> map1=new HashMap();
		map1.put("PartName", "/word/theme/theme1.xml");
		map1.put("ContentType", "application/vnd.openxmlformats-officedocument.theme+xml");
		Map<String,String> map2=new HashMap();
		map2.put("PartName", "/word/styles.xml");
		map2.put("ContentType", "application/vnd.openxmlformats-officedocument.wordprocessingml.styles+xml");
		Map<String,String> map3=new HashMap();
		map3.put("Extension", "rels");
		map3.put("ContentType", "application/vnd.openxmlformats-package.relationships+xml");
		Map<String,String> map4=new HashMap();
		map4.put("Extension", "xml");
		map4.put("ContentType", "application/xml");
		Map<String,String> map5=new HashMap();
		map5.put("PartName", "/word/document.xml");
		map5.put("ContentType", "application/vnd.openxmlformats-officedocument.wordprocessingml.document.main+xml");
		Map<String,String> map6=new HashMap();
		map6.put("PartName", "/docProps/app.xml");
		map6.put("ContentType", "application/vnd.openxmlformats-officedocument.extended-properties+xml");
		Map<String,String> map7=new HashMap();
		map7.put("PartName", "/word/settings.xml");
		map7.put("ContentType", "application/vnd.openxmlformats-officedocument.wordprocessingml.settings+xml");
		Map<String,String> map8=new HashMap();
		map8.put("PartName", "/docProps/core.xml");
		map8.put("ContentType", "application/vnd.openxmlformats-package.core-properties+xml");
                Map<String,String> map9=new HashMap();
		map9.put("PartName", "/word/webSettings.xml");
		map9.put("ContentType", "application/vnd.openxmlformats-officedocument.wordprocessingml.webSettings+xml");
                Map<String,String> map10=new HashMap();
		map10.put("PartName", "/word/fontTable.xml");
		map10.put("ContentType", "application/vnd.openxmlformats-officedocument.wordprocessingml.fontTable+xml");
                
		addElement("Override", map1);
		addElement("Override", map2);
		addElement("Default", map3);
		addElement("Default", map4);
		addElement("Override", map5);
		addElement("Override", map6);
		addElement("Override", map7);
		addElement("Override", map8);
                addElement("Override", map9);
                addElement("Override", map10);
	}

}
