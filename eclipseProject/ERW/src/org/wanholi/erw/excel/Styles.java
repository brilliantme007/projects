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
 * 2017.11.4
 */
public class Styles extends XXML{
    String path="xl"+File.separator+"styles.xml";
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        fonts FONTS;
        fills FILLS;
        borders BORDERS;
        cellStyleXfs CELLSTYLEXFS;
        cellXfs CELLXFS;
        cellStyles CELLSTYLES;
	Styles() throws ParserConfigurationException {
		super();
		DocumentBuilder db = dbf.newDocumentBuilder();
		XMLD =  db.newDocument();
		XMLD.setXmlStandalone(true);
		root=XMLD.createElement("styleSheet");
		XMLD.appendChild(root);
	}
        void build(){
            setAttribute("xmlns", "http://schemas.openxmlformats.org/spreadsheetml/2006/main");
            Fonts=XMLD.createElement("fonts");
            FONTS=new fonts();
            addElement(Fonts);
            Fills=XMLD.createElement("fills");
            FILLS=new fills();
            addElement(Fills);
            Borders=XMLD.createElement("borders");
            BORDERS=new borders();
            addElement(Borders);
            CellStyleXfs=XMLD.createElement("cellStyleXfs");
            CELLSTYLEXFS=new cellStyleXfs();
            addElement(CellStyleXfs);
            CellXfs=XMLD.createElement("cellXfs");
            CELLXFS=new cellXfs();
            addElement(CellXfs);
            CellStyles=XMLD.createElement("cellStyles");
            CELLSTYLES=new cellStyles();
            addElement(CellStyles);
            
            addElement("dxfs").setAttribute("count", "0");
            Map<String,String> map=new HashMap();
            map.put("count", "0");
            map.put("defaultTableStyle", "TableStyleMedium2");
            map.put("defaultPivotStyle", "PivotStyleMedium9");
            addElement("tableStyles", map);
        }
        Element Fonts;
        Element Fills;
        Element Borders;
        Element CellStyleXfs;
        Element CellXfs;
        Element CellStyles;
    class fonts {
        int count=0;
        
        fonts() {
            addFont(11, 1, "宋体", 2, "minor");
                
        }
        final void addFont(int size,int color,String name,int family,String scheme){
            count++;
            Fonts.setAttribute("count", String.valueOf(count));
            Element font=XMLD.createElement("font");
            Fonts.appendChild(font);
                Element sz=XMLD.createElement("sz");
                sz.setAttribute("val", String.valueOf(size));
                font.appendChild(sz);
                Element Color=XMLD.createElement("color");
                Color.setAttribute("theme", String.valueOf(color));
                font.appendChild(Color);
                Element Name=XMLD.createElement("name");
                Name.setAttribute("val", name);
                font.appendChild(Name);
                Element Family=XMLD.createElement("family");
                Family.setAttribute("val", String.valueOf(family));
                font.appendChild(Family);
                Element Scheme =XMLD.createElement("scheme");
                Scheme.setAttribute("val", scheme);
                font.appendChild(Scheme);
                
        }
        
    }
    
       class fills {
           int count=2;
            fills() {
                Fills.setAttribute("count", String.valueOf(count));
                Element fill=XMLD.createElement("fill");
                Fills.appendChild(fill);
                    Element patternFill =XMLD.createElement("patternFill");
                    patternFill.setAttribute("patternType", "none");
                    fill.appendChild(patternFill);
                    
                Element fill2=XMLD.createElement("fill");
                Fills.appendChild(fill2);
                    Element patternFill2 =XMLD.createElement("patternFill");
                    patternFill2.setAttribute("patternType", "gray125");
                    fill2.appendChild(patternFill2);
            }
           
       }
       
       class borders{
           int count=0;
           borders(){
               addBorder(0, 0, 0, 0, 0);
           }
           final void addBorder(int left,int right,int top,int bottom,int diagonal){
               count++;
               Borders.setAttribute("count", String.valueOf(count));
               Element border=XMLD.createElement("border");
               Borders.appendChild(border);
                    Element Left=XMLD.createElement("left");
                    border.appendChild(Left);
                    
                    Element Right=XMLD.createElement("right");
                    border.appendChild(Right);
                    
                    Element Top=XMLD.createElement("top");
                    border.appendChild(Top);
                    
                    Element Bottom=XMLD.createElement("bottom");
                    border.appendChild(Bottom);
                    
                    Element Diagonal=XMLD.createElement("diagonal");
                    border.appendChild(Diagonal);
                
           }
       }
       class cellStyleXfs {
           int count=1;

        cellStyleXfs() {
            CellStyleXfs.setAttribute("count", String.valueOf(count));
            Element xf=XMLD.createElement("xf");
            CellStyleXfs.appendChild(xf);
            {xf.setAttribute("numFmtId", String.valueOf(0));xf.setAttribute("fontId", "0");xf.setAttribute("fillId", "0");xf.setAttribute("borderId", "0");}
        }
           
       }
       class cellXfs {
           int count=1;

        cellXfs() {
             CellXfs.setAttribute("count", String.valueOf(count));
            Element xf=XMLD.createElement("xf");
            CellXfs.appendChild(xf);
            {xf.setAttribute("numFmtId", String.valueOf(0));xf.setAttribute("fontId", "0");xf.setAttribute("fillId", "0");xf.setAttribute("borderId", "0");xf.setAttribute("xfId", "0");}
        }
           
       }
       class cellStyles {
           int count=1;
        public cellStyles() {
            CellStyles.setAttribute("count", String.valueOf(count));
            Element cellStyle=XMLD.createElement("cellStyle");
            CellStyles.appendChild(cellStyle);
            {cellStyle.setAttribute("name", "Normal");cellStyle.setAttribute("xfId", "0");cellStyle.setAttribute("builtinId", "0");}
        }
           
       }
}
