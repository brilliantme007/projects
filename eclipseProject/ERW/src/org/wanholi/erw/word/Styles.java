/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wanholi.erw.word;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.wanholi.erw.pub.XXML;
import org.wanholi.erw.pub.Xmlns;

/**
 *
 * @author lifei
 * @version 1.0
 * 2017.11.20
 */
public class Styles extends XXML{
    String path="word"+File.separator+"styles.xml";
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

	Styles() throws ParserConfigurationException {
		super();
		DocumentBuilder db = dbf.newDocumentBuilder();
		XMLD =  db.newDocument();
		XMLD.setXmlStandalone(true);
		root=XMLD.createElement("w:styles");
		XMLD.appendChild(root);
	}
        void build(){
            setAttribute("xmlns:mc", Xmlns.xmlnsmc.string);
            setAttribute("xmlns:r", Xmlns.xmlnsr.string);
            setAttribute("xmlns:w", Xmlns.xmlnsw.string);
            setAttribute("xmlns:w14", Xmlns.xmlnsw14.string);
            setAttribute("xmlns:w15", Xmlns.xmlnsw15.string);
            setAttribute("mc:Ignorable", "w14 w15");
            Element docDefaults=addElement("w:docDefaults");
            Element rPrDefault=XMLD.createElement("w:rPrDefault");
            docDefaults.appendChild(rPrDefault);
                Element rPr=XMLD.createElement("w:rPr");
                rPrDefault.appendChild(rPr);
                    Element rFonts=XMLD.createElement("w:rFonts");
                    rPr.appendChild(rFonts);
                    rFonts.setAttribute("w:asciiTheme", "minorHAnsi");
                    rFonts.setAttribute("w:eastAsiaTheme", "minorEastAsia");
                    rFonts.setAttribute("w:hAnsiTheme", "minorHAnsi");
                    rFonts.setAttribute("w:cstheme", "minorBidi");
                    Element kern=XMLD.createElement("w:kern");
                    rPr.appendChild(kern);
                    kern.setAttribute("w:val", "2");
                    Element sz=XMLD.createElement("w:sz");
                    rPr.appendChild(sz);
                    sz.setAttribute("w:val", "21");
                    Element szcs=XMLD.createElement("w:szCs");
                    rPr.appendChild(szcs);
                    szcs.setAttribute("w:val", "22");
                    Element lang=XMLD.createElement("w:lang");
                    rPr.appendChild(lang);
                    lang.setAttribute("w:val", "en-US");
                    lang.setAttribute("w:eastAsia", "zh-CN");
                    lang.setAttribute("w:bidi", "ar-SA");

            latentStyles LS=new latentStyles();
            addElement(LatentStyles);
            Element style=XMLD.createElement("w:style");
            style.setAttribute("w:type", "paragraph");
            style.setAttribute("w:default", "1");
            style.setAttribute("w:styleId", "a");
            Element name=XMLD.createElement("w:name");
            name.setAttribute("w:val", "Normal");
            style.appendChild(name);
            style.appendChild(XMLD.createElement("w:qFormat"));
            Element ppr=XMLD.createElement("w:pPr");
                Element widowControl=XMLD.createElement("w:widowControl");
                widowControl.setAttribute("w:val", "0");
                ppr.appendChild(widowControl);
                Element Jc=XMLD.createElement("w:jc");
                Jc.setAttribute("w:val", "both");
                ppr.appendChild(Jc);
            style.appendChild(ppr);
            addElement(style);
            style=XMLD.createElement("w:style");
            style.setAttribute("w:type", "character");
            style.setAttribute("w:default", "1");
            style.setAttribute("w:styleId", "a0");
                name=XMLD.createElement("w:name");
                name.setAttribute("w:val", "Default Paragraph Font");
                Element uiPriority=XMLD.createElement("w:uiPriority");
                uiPriority.setAttribute("w:val", "1");
                style.appendChild(name);
                style.appendChild(uiPriority);
                style.appendChild(XMLD.createElement("w:semiHidden"));
                style.appendChild(XMLD.createElement("w:unhideWhenUsed"));
            addElement(style);
            style=XMLD.createElement("w:style");
            style.setAttribute("w:type", "table");
            style.setAttribute("w:default", "1");
            style.setAttribute("w:styleId", "a1");
                name=XMLD.createElement("w:name");
                name.setAttribute("w:val", "Normal Table");
                uiPriority=XMLD.createElement("w:uiPriority");
                uiPriority.setAttribute("w:val", "99");
                style.appendChild(name);
                style.appendChild(uiPriority);
                style.appendChild(XMLD.createElement("w:semiHidden"));
                style.appendChild(XMLD.createElement("w:unhideWhenUsed"));
                Element talpr=XMLD.createElement("w:tblPr");
                style.appendChild(talpr);
                    Element tblind=XMLD.createElement("w:tblInd");
                    tblind.setAttribute("w:w", "0");
                    tblind.setAttribute("w:type", "dxa");
                    talpr.appendChild(tblind);
                    Element tblCellMar=XMLD.createElement("w:tblCellMar");
                    talpr.appendChild(tblCellMar);
                        Element top=XMLD.createElement("w:top");
                        top.setAttribute("w:w", "0");top.setAttribute("w:type", "dxa");
                        Element left=XMLD.createElement("w:left");
                        left.setAttribute("w:w", "108");left.setAttribute("w:type", "dxa");
                        Element bottom=XMLD.createElement("w:left");
                        bottom.setAttribute("w:w", "0");bottom.setAttribute("w:type", "dxa");
                        Element right=XMLD.createElement("w:left");
                        right.setAttribute("w:w", "108");right.setAttribute("w:type", "dxa");
                        tblCellMar.appendChild(top);tblCellMar.appendChild(left);
                        tblCellMar.appendChild(bottom);tblCellMar.appendChild(right);
            addElement(style);
            style=XMLD.createElement("w:style");
            style.setAttribute("w:type", "numbering");
            style.setAttribute("w:default", "1");
            style.setAttribute("w:styleId", "a2");
                name=XMLD.createElement("w:name");
                name.setAttribute("w:val", "No List");
                uiPriority=XMLD.createElement("w:uiPriority");
                uiPriority.setAttribute("w:val", "99");
                style.appendChild(name);
                style.appendChild(uiPriority);
                style.appendChild(XMLD.createElement("w:semiHidden"));
                style.appendChild(XMLD.createElement("w:unhideWhenUsed"));
                        
            addElement(style);
        }
        Element LatentStyles ;

        class latentStyles {
            latentStyles(){
                LatentStyles=XMLD.createElement("w:latentStyles");
                LatentStyles.setAttribute("w:defLockedState", "0");
                LatentStyles.setAttribute("w:defUIPriority", "99");
                LatentStyles.setAttribute("w:defSemiHidden", "0");
                LatentStyles.setAttribute("w:defUnhideWhenUsed", "0");
                LatentStyles.setAttribute("w:defQFormat", "0");
                LatentStyles.setAttribute("w:count", "371");
                addLsdException("Normal", 0,null, null, 1);
                addLsdException("heading 1", 9,null, null, 1);
                for(int i=2;i<10;i++)
                    addLsdException("heading "+i, 9,1, 1, 1);
                for(int i=1;i<10;i++)
                    addLsdException("index "+i, null,1, 1, null);
                for(int i=1;i<10;i++)
                    addLsdException("toc "+i, 39,1, 1, null);
                addLsdException("Normal Indent", null,1, 1, null);
                addLsdException("footnote text", null,1, 1, null);
                addLsdException("annotation text", null,1, 1, null);
                addLsdException("header", null,1, 1, null);
                addLsdException("footer", null,1, 1, null);
                addLsdException("index heading",  null,1, 1, null);
                addLsdException("caption", 35,1, 1, 1);
                addLsdException("table of figures", null,1, 1, null);
                addLsdException("envelope address", null,1, 1, null);
                addLsdException("envelope return", null,1, 1, null);
                addLsdException("footnote reference", null,1, 1, null);
                addLsdException("annotation reference", null,1, 1, null);
                addLsdException("line number", null,1, 1, null);
                addLsdException("page number", null,1, 1, null);
                addLsdException("endnote reference", null,1, 1, null);
                addLsdException("endnote text", null,1, 1, null);
                addLsdException("table of authorities", null,1, 1, null);
                addLsdException("macro", null,1, 1, null);
                addLsdException("toa heading", null,1, 1, null);
                addLsdException("List", null,1, 1, null);
                addLsdException("List Bullet", null,1, 1, null);
                addLsdException("List Number", null,1, 1, null);
                addLsdException("List Continue", null,1, 1, null);
                for(int i=2;i<6;i++)
                {
                    addLsdException("List "+i, null,1, 1, null);
                    addLsdException("List Bullet "+i, null,1, 1, null);
                    addLsdException("List Number "+i, null,1, 1, null);
                    addLsdException("List Continue "+i, null,1, 1, null);
                }
                addLsdException("Title", 10,null, null, 1);
                addLsdException("Closing", null,1, 1, null);
                addLsdException("Signature", null,1, 1, null);
                addLsdException("Default Paragraph Font", 1,1, 1, null);
                addLsdException("Body Text", null,1, 1, null);
                addLsdException("Body Text Indent", null,1, 1, null);
                addLsdException("Message Header", null,1, 1, null);
                addLsdException("Subtitle", 11,null, null, 1);
                addLsdException("Salutation", null,1, 1, null);
                addLsdException("Date", null,1, 1, null);
                addLsdException("Body Text First Indent", null,1, 1, null);
                addLsdException("Body Text First Indent 2", null,1, 1, null);
                addLsdException("Note Heading", null,1, 1, null);
                addLsdException("Body Text 2", null,1, 1, null);
                addLsdException("Body Text 3", null,1, 1, null);
                addLsdException("Body Text Indent 2", null,1, 1, null);
                addLsdException("Body Text Indent 3", null,1, 1, null);
                addLsdException("Block Text", null,1, 1, null);
                addLsdException("Hyperlink", null,1, 1, null);
                addLsdException("FollowedHyperlink", null,1, 1, null);
                addLsdException("Strong", 22,null, null, 1);
                addLsdException("Emphasis", 20,null, null, 1);
                addLsdException("Document Map", null,1, 1, null);
                addLsdException("Plain Text", null,1, 1, null);
                addLsdException("E-mail Signature", null,1, 1, null);
                addLsdException("HTML Top of Form", null,1, 1, null);
                addLsdException("HTML Bottom of Form", null,1, 1, null);
                addLsdException("Normal (Web)", null,1, 1, null);
                addLsdException("HTML Acronym", null,1, 1, null);
                addLsdException("HTML Address", null,1, 1, null);
                addLsdException("HTML Cite", null,1, 1, null);
                addLsdException("HTML Code", null,1, 1, null);
                addLsdException("HTML Definition", null,1, 1, null);
                addLsdException("HTML Keyboard", null,1, 1, null);
                addLsdException("HTML Preformatted", null,1, 1, null);
                addLsdException("HTML Sample", null,1, 1, null);
                addLsdException("HTML Typewriter", null,1, 1, null);
                addLsdException("HTML Variable", null,1, 1, null);
                addLsdException("Normal Table", null,1, 1, null);
                addLsdException("annotation subject", null,1, 1, null);
                addLsdException("No List", null,1, 1, null);
                
                for(int i=1;i<9;i++){
                    if(i<3){
                        addLsdException("Table Subtle "+i, null,1, 1, null);
                    }
                    if(i<4){
                        addLsdException("Outline List "+i, null,1, 1, null);
                        addLsdException("Table Simple "+i, null,1, 1, null);
                        addLsdException("Table Colorful "+i, null,1, 1, null);
                        addLsdException("Table 3D effects "+i, null,1, 1, null);
                        addLsdException("Table Web "+i, null,1, 1, null);
                    }
                    if(i<5){
                        addLsdException("Table Classic "+i, null,1, 1, null);
                    }
                    if(i<6){
                        addLsdException("Table Columns "+i, null,1, 1, null);
                    }
                    addLsdException("Table Grid "+i, null,1, 1, null);
                    addLsdException("Table List "+i, null,1, 1, null);
                }
                addLsdException("Table Contemporary", null,1, 1, null);
                addLsdException("Table Elegant", null,1, 1, null);
                addLsdException("Table Professional", null,1, 1, null);
                addLsdException("Balloon Text", null,1, 1, null);
                addLsdException("Table Grid", 39,null, null, null);
                addLsdException("Table Theme", null,1, 1, null);
                addLsdException("Placeholder Text", null,1, null, null);
                addLsdException("No Spacing", 1,null, null, 1);
                addLsdException("Light Shading", 60);
                addLsdException("Light List", 61);
                addLsdException("Light Grid", 62);
                
                addLsdException("Medium Shading 1",63);
                addLsdException("Medium Shading 2", 64);
                addLsdException("Medium List 1", 65);
                addLsdException("Medium List 2",  66);
                addLsdException("Medium Grid 1",  67);
                addLsdException("Medium Grid 2",  68);
                addLsdException("Medium Grid 3",  69);
                addLsdException("Dark List",  70);
                addLsdException("Colorful Shading",  71);
                addLsdException("Colorful List",  72);
                addLsdException("Colorful Grid",  73);
                addLsdException("Light Shading Accent 1",  60);
                addLsdException("Light List Accent 1",  61);
                addLsdException("Light Grid Accent 1",  62);
                addLsdException("Medium Shading 1 Accent 1",  63);
                addLsdException("Medium Shading 2 Accent 1",  64);
                addLsdException("Medium List 1 Accent 1",  65);
                addLsdException("Revision", null, 1,null,null);
                addLsdException("List Paragraph",  34,null,null,1);
                addLsdException("Quote",  29,null,null,1);
                addLsdException("Intense Quote",  30,null,null,1);
                addLsdException("Medium List 2 Accent 1",  66);
                addLsdException("Medium Grid 1 Accent 1",  67);
                addLsdException("Medium Grid 2 Accent 1",  68);
                addLsdException("Medium Grid 3 Accent 1",  69);
                addLsdException("Dark List Accent 1",  70);
                addLsdException("Colorful Shading Accent 1",  71);
                addLsdException("Colorful List Accent 1",  72);
                addLsdException("Colorful Grid Accent 1",  73);
                addLsdException("Light Shading Accent 2",  60);
                addLsdException("Light List Accent 2",  61);
                addLsdException("Light Grid Accent 2",  62);
                addLsdException("Medium Shading 1 Accent 2",  63);
                addLsdException("Medium Shading 2 Accent 2",  64);
                addLsdException("Medium List 1 Accent 2",  65);
                addLsdException("Medium List 2 Accent 2",  66);
                addLsdException("Medium Grid 1 Accent 2",  67);
                addLsdException("Medium Grid 2 Accent 2",  68);
                addLsdException("Medium Grid 3 Accent 2",  69);
                addLsdException("Dark List Accent 2",  70);
                addLsdException("Colorful Shading Accent 2",  71);
                addLsdException("Colorful List Accent 2",  72);
                addLsdException("Colorful Grid Accent 2",  73);
                addLsdException("Light Shading Accent 3",  60);
                addLsdException("Light List Accent 3",  61);
                addLsdException("Light Grid Accent 3",  62);
                addLsdException("Medium Shading 1 Accent 3",  63);
                addLsdException("Medium Shading 2 Accent 3",  64);
                addLsdException("Medium List 1 Accent 3",  65);
                addLsdException("Medium List 2 Accent 3",  66);
                addLsdException("Medium Grid 1 Accent 3",  67);
                addLsdException("Medium Grid 2 Accent 3",  68);
                addLsdException("Medium Grid 3 Accent 3",  69);
                addLsdException("Dark List Accent 3",  70);
                addLsdException("Colorful Shading Accent 3",  71);
                addLsdException("Colorful List Accent 3",  72);
                addLsdException("Colorful Grid Accent 3",  73);
                addLsdException("Light Shading Accent 4",  60);
                addLsdException("Light List Accent 4",  61);
                addLsdException("Light Grid Accent 4",  62);
                addLsdException("Medium Shading 1 Accent 4",  63);
                addLsdException("Medium Shading 2 Accent 4",  64);
                addLsdException("Medium List 1 Accent 4",  65);
                addLsdException("Medium List 2 Accent 4",  66);
                addLsdException("Medium Grid 1 Accent 4",  67);
                addLsdException("Medium Grid 2 Accent 4",  68);
                addLsdException("Medium Grid 3 Accent 4" ,69);
                addLsdException("Dark List Accent 4" ,70);
                addLsdException("Colorful Shading Accent 4" ,71);
                addLsdException("Colorful List Accent 4" ,72);
                addLsdException("Colorful Grid Accent 4" ,73);
                addLsdException("Light Shading Accent 5" ,60);
                addLsdException("Light List Accent 5" ,61);
                addLsdException("Light Grid Accent 5" ,62);
                addLsdException("Medium Shading 1 Accent 5" ,63);
                addLsdException("Medium Shading 2 Accent 5" ,64);
                addLsdException("Medium List 1 Accent 5" ,65);
                addLsdException("Medium List 2 Accent 5" ,66);
                addLsdException("Medium Grid 1 Accent 5" ,67);
                addLsdException("Medium Grid 2 Accent 5" ,68);
                addLsdException("Medium Grid 3 Accent 5" ,69);
                addLsdException("Dark List Accent 5" ,70);
                addLsdException("Colorful Shading Accent 5" ,71);
                addLsdException("Colorful List Accent 5" ,72);
                addLsdException("Colorful Grid Accent 5" ,73);
                addLsdException("Light Shading Accent 6" ,60);
                addLsdException("Light List Accent 6" ,61);
                addLsdException("Light Grid Accent 6" ,62);
                addLsdException("Medium Shading 1 Accent 6" ,63);
                addLsdException("Medium Shading 2 Accent 6" ,64);
                addLsdException("Medium List 1 Accent 6" ,65);
                addLsdException("Medium List 2 Accent 6" ,66);
                addLsdException("Medium Grid 1 Accent 6" ,67);
                addLsdException("Medium Grid 2 Accent 6" ,68);
                addLsdException("Medium Grid 3 Accent 6" ,69);
                addLsdException("Dark List Accent 6" ,70);
                addLsdException("Colorful Shading Accent 6" ,71);
                addLsdException("Colorful List Accent 6" ,72);
                addLsdException("Colorful Grid Accent 6" ,73);
                addLsdException("Subtle Emphasis" ,19,null,null, 1);
                addLsdException("Intense Emphasis" ,21,null,null, 1);
                addLsdException("Subtle Reference" ,31,null,null, 1);
                addLsdException("Intense Reference" ,32,null,null, 1);
                addLsdException("Book Title" ,33,null,null,1);
                addLsdException("Bibliography",37,1 , 1,null);
                addLsdException("TOC Heading",39, 1 ,1 ,1);
                addLsdException("Plain Table 1" ,41);
                addLsdException("Plain Table 2" ,42);
                addLsdException("Plain Table 3" ,43);
                addLsdException("Plain Table 4" ,44);
                addLsdException("Plain Table 5" ,45);
                addLsdException("Grid Table Light" ,40);
                addLsdException("Grid Table 1 Light" ,46);
                addLsdException("Grid Table 2" ,47);
                addLsdException("Grid Table 3" ,48);
                addLsdException("Grid Table 4" ,49);
                addLsdException("Grid Table 5 Dark" ,50);
                addLsdException("Grid Table 6 Colorful" ,51);
                addLsdException("Grid Table 7 Colorful" ,52);
                addLsdException("Grid Table 1 Light Accent 1" ,46);
                addLsdException("Grid Table 2 Accent 1" ,47);
                addLsdException("Grid Table 3 Accent 1" ,48);
                addLsdException("Grid Table 4 Accent 1" ,49);
                addLsdException("Grid Table 5 Dark Accent 1" ,50);
                addLsdException("Grid Table 6 Colorful Accent 1" ,51);
                addLsdException("Grid Table 7 Colorful Accent 1" ,52);
                addLsdException("Grid Table 1 Light Accent 2" ,46);
                addLsdException("Grid Table 2 Accent 2" ,47);
                addLsdException("Grid Table 3 Accent 2" ,48);
                addLsdException("Grid Table 4 Accent 2" ,49);
                addLsdException("Grid Table 5 Dark Accent 2" ,50);
                addLsdException("Grid Table 6 Colorful Accent 2" ,51);
                addLsdException("Grid Table 7 Colorful Accent 2" ,52);
                addLsdException("Grid Table 1 Light Accent 3" ,46);
                addLsdException("Grid Table 2 Accent 3" ,47);
                addLsdException("Grid Table 3 Accent 3" ,48);
                addLsdException("Grid Table 4 Accent 3" ,49);
                addLsdException("Grid Table 5 Dark Accent 3" ,50);
                addLsdException("Grid Table 6 Colorful Accent 3" ,51);
                addLsdException("Grid Table 7 Colorful Accent 3" ,52);
                addLsdException("Grid Table 1 Light Accent 4" ,46);
                addLsdException("Grid Table 2 Accent 4" ,47);
                addLsdException("Grid Table 3 Accent 4" ,48);
                addLsdException("Grid Table 4 Accent 4" ,49);
                addLsdException("Grid Table 5 Dark Accent 4" ,50);
                addLsdException("Grid Table 6 Colorful Accent 4" ,51);
                addLsdException("Grid Table 7 Colorful Accent 4" ,52);
                addLsdException("Grid Table 1 Light Accent 5" ,46);
                addLsdException("Grid Table 2 Accent 5" ,47);
                addLsdException("Grid Table 3 Accent 5" ,48);
                addLsdException("Grid Table 4 Accent 5" ,49);
                addLsdException("Grid Table 5 Dark Accent 5" ,50);
                addLsdException("Grid Table 6 Colorful Accent 5" ,51);
                addLsdException("Grid Table 7 Colorful Accent 5" ,52);
                addLsdException("Grid Table 1 Light Accent 6" ,46);
                addLsdException("Grid Table 2 Accent 6" ,47);
                addLsdException("Grid Table 3 Accent 6" ,48);
                addLsdException("Grid Table 4 Accent 6" ,49);
                addLsdException("Grid Table 5 Dark Accent 6" ,50);
                addLsdException("Grid Table 6 Colorful Accent 6" ,51);
                addLsdException("Grid Table 7 Colorful Accent 6" ,52);
                addLsdException("List Table 1 Light" ,46);
                addLsdException("List Table 2" ,47);
                addLsdException("List Table 3" ,48);
                addLsdException("List Table 4" ,49);
                addLsdException("List Table 5 Dark" ,50);
                addLsdException("List Table 6 Colorful" ,51);
                addLsdException("List Table 7 Colorful" ,52);
                addLsdException("List Table 1 Light Accent 1" ,46);
                addLsdException("List Table 2 Accent 1" ,47);
                addLsdException("List Table 3 Accent 1" ,48);
                addLsdException("List Table 4 Accent 1" ,49);
                addLsdException("List Table 5 Dark Accent 1" ,50);
                addLsdException("List Table 6 Colorful Accent 1" ,51);
                addLsdException("List Table 7 Colorful Accent 1" ,52);
                addLsdException("List Table 1 Light Accent 2" ,46);
                addLsdException("List Table 2 Accent 2" ,47);
                addLsdException("List Table 3 Accent 2" ,48);
                addLsdException("List Table 4 Accent 2" ,49);
                addLsdException("List Table 5 Dark Accent 2" ,50);
                addLsdException("List Table 6 Colorful Accent 2" ,51);
                addLsdException("List Table 7 Colorful Accent 2" ,52);
                addLsdException("List Table 1 Light Accent 3" ,46);
                addLsdException("List Table 2 Accent 3" ,47);
                addLsdException("List Table 3 Accent 3" ,48);
                addLsdException("List Table 4 Accent 3" ,49);
                addLsdException("List Table 5 Dark Accent 3" ,50);
                addLsdException("List Table 6 Colorful Accent 3" ,51);
                addLsdException("List Table 7 Colorful Accent 3" ,52);
                addLsdException("List Table 1 Light Accent 4" ,46);
                addLsdException("List Table 2 Accent 4" ,47);
                addLsdException("List Table 3 Accent 4" ,48);
                addLsdException("List Table 4 Accent 4" ,49);
                addLsdException("List Table 5 Dark Accent 4" ,50);
                addLsdException("List Table 6 Colorful Accent 4" ,51);
                addLsdException("List Table 7 Colorful Accent 4" ,52);
                addLsdException("List Table 1 Light Accent 5" ,46);
                addLsdException("List Table 2 Accent 5" ,47);
                addLsdException("List Table 3 Accent 5" ,48);
                addLsdException("List Table 4 Accent 5" ,49);
                addLsdException("List Table 5 Dark Accent 5" ,50);
                addLsdException("List Table 6 Colorful Accent 5" ,51);
                addLsdException("List Table 7 Colorful Accent 5" ,52);
                addLsdException("List Table 1 Light Accent 6" ,46);
                addLsdException("List Table 2 Accent 6" ,47);
                addLsdException("List Table 3 Accent 6" ,48);
                addLsdException("List Table 4 Accent 6" ,49);
                addLsdException("List Table 5 Dark Accent 6" ,50);
                addLsdException("List Table 6 Colorful Accent 6" ,51);
                addLsdException("List Table 7 Colorful Accent 6" ,52);
            }
            private void addLsdException(String name,Integer uiPriority){
                addLsdException(name, uiPriority, null, null, null);
            }
            private void addLsdException (String name,Integer uiPriority,Integer semiHidden,Integer unhideWhenUsed,Integer qFormat){
                Element lsdException =XMLD.createElement("w:lsdException");
                LatentStyles.appendChild(lsdException);
                if(null != name)
                    lsdException.setAttribute("w:name", name);
                if(null != uiPriority)
                    lsdException.setAttribute("w:uiPriority", uiPriority.toString());
                if(null != semiHidden)
                    lsdException.setAttribute("w:semiHidden", semiHidden.toString());
                if(null != unhideWhenUsed)
                    lsdException.setAttribute("w:unhideWhenUsed", unhideWhenUsed.toString());
                if(null != qFormat)
                    lsdException.setAttribute("w:qFormat", qFormat.toString());
            }
        }
}
