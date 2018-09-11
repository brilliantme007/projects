/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wanholi.erw.word;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.wanholi.erw.pub.XXML;

/**
 *文件信息
 * 2017.11.3
 * @author lifei
 * @version 1.0
 */
public class App extends XXML{
    String path="docProps"+File.separator+"app.xml";
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	App() throws ParserConfigurationException {
		super();
		DocumentBuilder db = dbf.newDocumentBuilder();
		XMLD =  db.newDocument();
		XMLD.setXmlStandalone(true);
		root=XMLD.createElement("Properties");
		XMLD.appendChild(root);
	}
        void build(){
            setAttribute("xmlns", "http://schemas.openxmlformats.org/officeDocument/2006/extended-properties");
            setAttribute("xmlns:vt", "http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes");
            addElement("Template").setTextContent("Normal.dotm");
            addElement("TotalTime").setTextContent("0");
            addElement("Pages").setTextContent("1");
            addElement("Words").setTextContent("0");
            addElement("Characters").setTextContent("0");
            addElement("Application").setTextContent("Microsoft Office Word");
            addElement("DocSecurity").setTextContent("0");
            addElement("Lines").setTextContent("0");
            addElement("Paragraphs").setTextContent("0");
            addElement("ScaleCrop").setTextContent("false");
            addElement("Company");
            addElement("LinksUpToDate").setTextContent("false");
            addElement("SharedDoc").setTextContent("false");
            addElement("HyperlinksChanged").setTextContent("false");
            addElement("AppVersion").setTextContent("14.0300");
                
        }
    
}
