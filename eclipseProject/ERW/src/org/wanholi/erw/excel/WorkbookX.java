/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wanholi.erw.excel;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.wanholi.erw.pub.XXML;

/**
 *
 * @author lifei
 * @version 1.0
 * 2017.11.4
 */
public class WorkbookX extends XXML{
    String path="xl"+File.separator+"_rels"+File.separator+"workbook.xml.rels";
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	WorkbookX() throws ParserConfigurationException {
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
		map1.put("Type", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/worksheet");
		map1.put("Target", "worksheets/sheet1.xml");
		Map<String,String> map2=new HashMap();
		map2.put("Id", "rId2");
		map2.put("Type", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/theme");
		map2.put("Target", "theme/theme1.xml");
		Map<String,String> map3=new HashMap();
		map3.put("Id", "rId3");
		map3.put("Type", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/styles");
		map3.put("Target", "styles.xml");
		addElement("Relationship", map1);
		addElement("Relationship", map2);
		addElement("Relationship", map3);
        }
}
