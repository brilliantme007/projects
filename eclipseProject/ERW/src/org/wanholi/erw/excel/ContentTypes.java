package org.wanholi.erw.excel;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.wanholi.erw.pub.XXML;

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
		map1.put("PartName", "/xl/theme/theme1.xml");
		map1.put("ContentType", "application/vnd.openxmlformats-officedocument.theme+xml");
		Map<String,String> map2=new HashMap();
		map2.put("PartName", "/xl/styles.xml");
		map2.put("ContentType", "application/vnd.openxmlformats-officedocument.spreadsheetml.styles+xml");
		Map<String,String> map3=new HashMap();
		map3.put("Extension", "rels");
		map3.put("ContentType", "application/vnd.openxmlformats-package.relationships+xml");
		Map<String,String> map4=new HashMap();
		map4.put("Extension", "xml");
		map4.put("ContentType", "application/xml");
		Map<String,String> map5=new HashMap();
		map5.put("PartName", "/xl/workbook.xml");
		map5.put("ContentType", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet.main+xml");
		Map<String,String> map6=new HashMap();
		map6.put("PartName", "/docProps/app.xml");
		map6.put("ContentType", "application/vnd.openxmlformats-officedocument.extended-properties+xml");
		Map<String,String> map7=new HashMap();
		map7.put("PartName", "/xl/worksheets/sheet1.xml");
		map7.put("ContentType", "application/vnd.openxmlformats-officedocument.spreadsheetml.worksheet+xml");
		Map<String,String> map8=new HashMap();
		map8.put("PartName", "/docProps/core.xml");
		map8.put("ContentType", "application/vnd.openxmlformats-package.core-properties+xml");

		addElement("Override", map1);
		addElement("Override", map2);
		addElement("Default", map3);
		addElement("Default", map4);
		addElement("Override", map5);
		addElement("Override", map6);
		addElement("Override", map7);
		addElement("Override", map8);
	}

}