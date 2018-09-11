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
 *
 * @author nchan
 */
public class FontTable extends XXML{
    String path="word"+File.separator+"fontTable.xml";
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        font FONT1,FONT2,FONT3,FONT4;
	FontTable() throws ParserConfigurationException {
		super();
		DocumentBuilder db = dbf.newDocumentBuilder();
		XMLD =  db.newDocument();
		XMLD.setXmlStandalone(true);
		root=XMLD.createElement("w:fonts");
		XMLD.appendChild(root);
	}
        void build(){
            setAttribute("xmlns:mc", "http://schemas.openxmlformats.org/markup-compatibility/2006");
            setAttribute("xmlns:r", "http://schemas.openxmlformats.org/officeDocument/2006/relationships");
            setAttribute("xmlns:w", "http://schemas.openxmlformats.org/wordprocessingml/2006/main");
            setAttribute("xmlns:w14", "http://schemas.microsoft.com/office/word/2010/wordml");
            setAttribute("xmlns:w15", "http://schemas.microsoft.com/office/word/2012/wordml");
            setAttribute("mc:Ignorable", "w14 w15");
            Font=XMLD.createElement("w:font");
            FONT1=new font("Calibri", "", "020F0502020204030204", "00", "swiss", "variable", "E0002AFF",
                    "C000247B", "00000009", "00000000", "000001FF", "00000000");
            addElement(Font);
            Font=XMLD.createElement("w:font");
            FONT2=new font("宋体", "SimSun", "02010600030101010101", "86", "auto", "variable", "00000003",
                    "288F0000", "00000016", "00000000", "00040001", "00000000");
            addElement(Font);
            Font=XMLD.createElement("w:font");
            FONT3=new font("Times New Roman", "", "02020603050405020304", "00", "roman", "variable", "E0002EFF",
                    "C000785B", "00000009", "00000000", "000001FF", "00000000");
            addElement(Font);
            Font=XMLD.createElement("w:font");
            FONT4=new font("Calibri Light", "", "020F0302020204030204", "00", "swiss", "variable", "E0002AFF",
                    "C000247B", "00000009", "00000000", "000001FF", "00000000");
            addElement(Font);
        }
        Element Font;
        class font{
        font(String fontName,String altName,String panose1,String charset,String family,String pitch,String usb0,String usb1,
        String usb2,String usb3,String csb0,String csb1) {
            Font.setAttribute("w:name", fontName);
            Element AltName =XMLD.createElement("w:altName");
            AltName.setAttribute("w:val", altName);
            Font.appendChild(AltName);
            Element Panose1=XMLD.createElement("w:panose1");
            Panose1.setAttribute("w:val", panose1);
            Font.appendChild(Panose1);
            Element Charset=XMLD.createElement("w:charset");
            Charset.setAttribute("w:val", charset);
            Font.appendChild(Charset);
            Element Family=XMLD.createElement("w:family");
            Family.setAttribute("w:val", family);
            Font.appendChild(Family);
            Element Pitch=XMLD.createElement("w:pitch");
            Pitch.setAttribute("w:val", pitch);
            Font.appendChild(Pitch);
            Element Sig=XMLD.createElement("w:sig");
            Sig.setAttribute("w:usb0", usb0);
            Sig.setAttribute("w:usb1", usb1);
            Sig.setAttribute("w:usb2", usb2);
            Sig.setAttribute("w:usb3", usb3);
            Sig.setAttribute("w:csb0", csb0);
            Sig.setAttribute("w:csb1", csb1);
            Font.appendChild(Sig);
        }
            
        }
}
