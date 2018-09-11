/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wanholi.erw.excel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.wanholi.erw.pub.XXML;

/**
 *
 * @author nchan
 */
public class Core extends XXML{
            String path="docProps"+File.separator+"core.xml";
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	Core() throws ParserConfigurationException {
		super();
		DocumentBuilder db = dbf.newDocumentBuilder();
		XMLD =  db.newDocument();
		XMLD.setXmlStandalone(true);
		root=XMLD.createElement("cp:coreProperties");
		XMLD.appendChild(root);
	}
	void build(){
		setAttribute("xmlns:cp", "http://schemas.openxmlformats.org/package/2006/metadata/core-properties");
                setAttribute("xmlns:dc", "http://purl.org/dc/elements/1.1/");
                setAttribute("xmlns:dcterms", "http://purl.org/dc/terms/");
                setAttribute("xmlns:dcmitype", "http://purl.org/dc/dcmitype/");
                setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		Map<String,String> map1=new HashMap();
		map1.put("xsi:type", "dcterms:W3CDTF");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd#HH:mm:ss");
                String dateString = formatter.format(new Date()).replace("#", "T")+"Z";
		addElement("dc:creator").setTextContent("erw");
                
		addElement("dcterms:created", map1).setTextContent(dateString);
		addElement("dcterms:modified", map1).setTextContent(dateString);
	}
}
