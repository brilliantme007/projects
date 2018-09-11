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
import org.wanholi.erw.pub.XXML;

/**
 *
 * @author nchan
 */
public class WebSetting extends XXML{
    String path="word"+File.separator+"webSettings.xml";
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	WebSetting() throws ParserConfigurationException {
		super();
		DocumentBuilder db = dbf.newDocumentBuilder();
		XMLD =  db.newDocument();
		XMLD.setXmlStandalone(true);
		root=XMLD.createElement("w:webSettings");
		XMLD.appendChild(root);
	}
        void build(){
            setAttribute("xmlns:mc", "http://schemas.openxmlformats.org/markup-compatibility/2006");
            setAttribute("xmlns:r", "http://schemas.openxmlformats.org/officeDocument/2006/relationships");
            setAttribute("xmlns:w", "http://schemas.openxmlformats.org/wordprocessingml/2006/main");
            setAttribute("xmlns:w14", "http://schemas.microsoft.com/office/word/2010/wordml");
            setAttribute("xmlns:w15", "http://schemas.microsoft.com/office/word/2012/wordml");
            setAttribute("mc:Ignorable", "w14 w15");
            addElement("w:optimizeForBrowser");
            addElement("w:allowPNG");
        }
}
