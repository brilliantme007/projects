/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wanholi.erw.excel;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.wanholi.erw.pub.XXML;

/**
 *文件信息
 * @author lifei
 * @version 1.0
 * 2017.11.3
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
            addElement("Application").setTextContent("Microsoft Excel");
            addElement("DocSecurity").setTextContent("0");
            addElement("ScaleCrop").setTextContent("false");
            Element HeadingPairs=addElement("HeadingPairs");
                Element vector=XMLD.createElement("vt:vector");
                HeadingPairs.appendChild(vector);
                {vector.setAttribute("size", "2"); vector.setAttribute("baseType", "variant");}
                    Element variant=XMLD.createElement("vt:variant");
                    vector.appendChild(variant);
                        Element lpstr=XMLD.createElement("vt:lpstr");
                        lpstr.setTextContent("Worksheets");
                        variant.appendChild(lpstr);
                    Element variant1=XMLD.createElement("vt:variant");
                    vector.appendChild(variant1);
                        Element vti4=XMLD.createElement("vt:i4");
                        vti4.setTextContent("1");
                        variant1.appendChild(vti4);
            Element TitlesOfParts=addElement("TitlesOfParts");
                Element vector1=XMLD.createElement("vt:vector");
                TitlesOfParts.appendChild(vector1);
                {vector1.setAttribute("size", "1"); vector1.setAttribute("baseType", "lpstr");}
                    Element lpstr1=XMLD.createElement("vt:lpstr");
                    vector1.appendChild(lpstr1);
                    lpstr1.setTextContent("Sheet1");
            addElement("Company");
            addElement("LinksUpToDate").setTextContent("false");
            addElement("SharedDoc").setTextContent("false");
            addElement("HyperlinksChanged").setTextContent("false");
            addElement("AppVersion").setTextContent("14.0300");
                
        }
    
}
