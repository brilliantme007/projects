package org.wanholi.erw.excel;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.wanholi.erw.pub.XXML;

public class Sheet extends XXML{

	String path="xl"+File.separator+"worksheets"+File.separator+"sheet1.xml";
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	Sheet() throws ParserConfigurationException {
		super();
		DocumentBuilder db = dbf.newDocumentBuilder();
		XMLD =  db.newDocument();
		XMLD.setXmlStandalone(true);
		root=XMLD.createElement("worksheet");
		XMLD.appendChild(root);
	}
    void build(){
        setAttribute("xmlns", "http://schemas.openxmlformats.org/spreadsheetml/2006/main");
        setAttribute("xmlns:r", "http://schemas.openxmlformats.org/officeDocument/2006/relationships");
        addElement("dimension").setAttribute("ref", "A1");
        Element sheetViews=addElement("sheetViews");
        Element sheetView=XMLD.createElement("sheetView");
        sheetViews.appendChild(sheetView);
        sheetView.setAttribute("tabSelected", "1");
        sheetView.setAttribute("workbookViewId", "0");
        addElement("sheetFormatPr").setAttribute("defaultRowHeight", "15");
        addElement("sheetData");
        Element pageMargins =addElement("pageMargins");
        pageMargins.setAttribute("left", "0.7");
        pageMargins.setAttribute("right", "0.7");
        pageMargins.setAttribute("top", "0.75");
        pageMargins.setAttribute("bottom", "0.75");
        pageMargins.setAttribute("header", "0.3");
        pageMargins.setAttribute("footer", "0.3");
    }
}
