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

import org.w3c.dom.Element;
import org.wanholi.erw.pub.XXML;

/**
 *
 * @author lifei
 * @version 1.0
 * 2017.11.3
 */
public class Workbook extends XXML{
    String path="xl"+File.separator+"workbook.xml";
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	Workbook() throws ParserConfigurationException {
		super();
		DocumentBuilder db = dbf.newDocumentBuilder();
		XMLD =  db.newDocument();
		XMLD.setXmlStandalone(true);
		root=XMLD.createElement("workbook");
		XMLD.appendChild(root);
	}
      public  void build(){
            setAttribute("xmlns", "http://schemas.openxmlformats.org/spreadsheetml/2006/main");
            setAttribute("xmlns:r", "http://schemas.openxmlformats.org/officeDocument/2006/relationships");
            Map<String,String> map1=new HashMap();
            map1.put("appName", "xl");
            map1.put("lastEdited", "4");
            map1.put("lowestEdited", "4");
            map1.put("rupBuild", "4505");
            Map<String,String> map2=new HashMap();
            map2.put("filterPrivacy", "1");
            map2.put("defaultThemeVersion", "124226");
            addElement("fileVersion",map1);
            addElement("workbookPr",map2);
            Element bookViews=addElement("bookViews");
            Element workbookView =XMLD.createElement("workbookView");
            bookViews.appendChild(workbookView);
            {workbookView.setAttribute("xWindow", "240");workbookView.setAttribute("yWindow", "105");workbookView.setAttribute("windowWidth", "14805");workbookView.setAttribute("windowHeight", "8010");}
            Element sheets=addElement("sheets");
            Element sheet =XMLD.createElement("sheet");
            sheets.appendChild(sheet);
            sheet.setAttribute("name", "Sheet1");
            sheet.setAttribute("sheetId", "1");
            sheet.setAttribute("r:id", "rId1");
            Element calcPr =addElement("calcPr");
            {calcPr.setAttribute("calcId", "122211");calcPr.setAttribute("fullCalcOnLoad", "1");}
        }
}
