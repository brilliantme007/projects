package org.wanholi.erw.word;

import java.io.File;
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
public class Rels extends XXML{
        String path="_rels"+File.separator+".rels";
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	Rels() throws ParserConfigurationException {
		super();
		DocumentBuilder db = dbf.newDocumentBuilder();
		XMLD =  db.newDocument();
		XMLD.setXmlStandalone(true);
		root=XMLD.createElement("Relationships");
		XMLD.appendChild(root);
	}
	void build(){
		setAttribute("xmlns", "http://schemas.openxmlformats.org/package/2006/relationships");
		Map<String,String> map1=new HashMap();
		map1.put("Id", "rId1");
		map1.put("Type", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument");
		map1.put("Target", "word/document.xml");
		Map<String,String> map2=new HashMap();
		map2.put("Id", "rId2");
		map2.put("Type", "http://schemas.openxmlformats.org/package/2006/relationships/metadata/core-properties");
		map2.put("Target", "docProps/core.xml");
		Map<String,String> map3=new HashMap();
		map3.put("Id", "rId3");
		map3.put("Type", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/extended-properties");
		map3.put("Target", "docProps/app.xml");
		addElement("Relationship", map3);
		addElement("Relationship", map2);
		addElement("Relationship", map1);
	}
}
