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
import org.wanholi.erw.pub.Xmlns;

/**
 *2017.11.19
 * @author lifei
 * @version 1.0
 */
public class Settings extends XXML{
    String path="word"+File.separator+"settings.xml";
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	Settings() throws ParserConfigurationException {
		super();
		DocumentBuilder db = dbf.newDocumentBuilder();
		XMLD =  db.newDocument();
		XMLD.setXmlStandalone(true);
		root=XMLD.createElement("w:settings");
		XMLD.appendChild(root);
	}
        void build(){
            setAttribute("xmlns:mc", Xmlns.xmlnsmc.string);
            setAttribute("xmlns:o", Xmlns.xmlnso.string);
            setAttribute("xmlns:r", Xmlns.xmlnsr.string);
            setAttribute("xmlns:m", Xmlns.xmlnsm.string);
            setAttribute("xmlns:v", Xmlns.xmlnsv.string);
            setAttribute("xmlns:w10", Xmlns.xmlnsw10.string);
            setAttribute("xmlns:w", Xmlns.xmlnsw.string);
            setAttribute("xmlns:w14", Xmlns.xmlnsw14.string);
            setAttribute("xmlns:w15", Xmlns.xmlnsw15.string);
            setAttribute("xmlns:sl", Xmlns.xmlnssl.string);
            setAttribute("mc:Ignorable", "w14 w15");
            addElement("w:zoom").setAttribute("w:percent", "100");
            addElement("w:bordersDoNotSurroundHeader");
            addElement("w:bordersDoNotSurroundFooter");
            Element proofState=addElement("w:proofState");
            proofState.setAttribute("w:grammar", "clean");
            proofState.setAttribute("w:spelling", "clean");
            addElement("w:defaultTabStop").setAttribute("w:val", "420");
            addElement("w:drawingGridVerticalSpacing").setAttribute("w:val", "156");
            addElement("w:displayHorizontalDrawingGridEvery").setAttribute("w:val", "0");
            addElement("w:displayVerticalDrawingGridEvery").setAttribute("w:val", "2");
            addElement("w:characterSpacingControl").setAttribute("w:val", "compressPunctuation");
            Element compat=addElement("w:compat");
            compat.appendChild(XMLD.createElement("w:spaceForUL"));
            compat.appendChild(XMLD.createElement("w:balanceSingleByteDoubleByteWidth"));
            compat.appendChild(XMLD.createElement("w:doNotLeaveBackslashAlone"));
            compat.appendChild(XMLD.createElement("w:ulTrailSpace"));
            compat.appendChild(XMLD.createElement("w:doNotExpandShiftReturn"));
            compat.appendChild(XMLD.createElement("w:adjustLineHeightInTable"));
            compat.appendChild(XMLD.createElement("w:useFELayout"));
            Element compatSetting =XMLD.createElement("w:compatSetting");
            compat.appendChild(compatSetting);
                compatSetting.setAttribute("w:name", "compatibilityMode");
                compatSetting.setAttribute("w:uri", "http://schemas.microsoft.com/office/word");
                compatSetting.setAttribute("w:val", "14");
            compatSetting =XMLD.createElement("w:compatSetting");
            compat.appendChild(compatSetting);
                compatSetting.setAttribute("w:name", "overrideTableStyleFontSizeAndJustification");
                compatSetting.setAttribute("w:uri", "http://schemas.microsoft.com/office/word");
                compatSetting.setAttribute("w:val", "1");
            compatSetting =XMLD.createElement("w:compatSetting");
            compat.appendChild(compatSetting);
                compatSetting.setAttribute("w:name", "enableOpenTypeFeatures");
                compatSetting.setAttribute("w:uri", "http://schemas.microsoft.com/office/word");
                compatSetting.setAttribute("w:val", "1");
            compatSetting =XMLD.createElement("w:compatSetting");
            compat.appendChild(compatSetting);
                compatSetting.setAttribute("w:name", "doNotFlipMirrorIndents");
                compatSetting.setAttribute("w:uri", "http://schemas.microsoft.com/office/word");
                compatSetting.setAttribute("w:val", "1");
//            compatSetting =XMLD.createElement("w:compatSetting");
//            compat.appendChild(compatSetting);
//                compatSetting.setAttribute("w:name", "differentiateMultirowTableHeaders");
//                compatSetting.setAttribute("w:uri", "http://schemas.microsoft.com/office/word");
//                compatSetting.setAttribute("w:val", "1");
                
            Element rsids=addElement("w:rsids");
            Element rsid =XMLD.createElement("w:rsidRoot");
            rsids.appendChild(rsid);
            rsid.setAttribute("w:val", "004C0A0D");
            rsid =XMLD.createElement("w:rsid");
            rsids.appendChild(rsid);
            rsid.setAttribute("w:val", "004C0A0D");
            rsid =XMLD.createElement("w:rsid");
            rsids.appendChild(rsid);
            rsid.setAttribute("w:val", "00511BB0");
            rsid =XMLD.createElement("w:rsid");
            rsids.appendChild(rsid);
            rsid.setAttribute("w:val", "00861A67");
            rsid =XMLD.createElement("w:rsid");
            rsids.appendChild(rsid);
            rsid.setAttribute("w:val", "00A0105E");
            rsid =XMLD.createElement("w:rsid");
            rsids.appendChild(rsid);
            rsid.setAttribute("w:val", "00DF7EDF");
            Element mathPr=addElement("m:mathPr");
            Element e=XMLD.createElement("m:mathFont");
            mathPr.appendChild(e);
            e.setAttribute("m:val", "Cambria Math");
            e=XMLD.createElement("m:brkBin");
            mathPr.appendChild(e);
            e.setAttribute("m:val", "before");
            e=XMLD.createElement("m:brkBinSub");
            mathPr.appendChild(e);
            e.setAttribute("m:val", "--");
            e=XMLD.createElement("m:smallFrac");
            mathPr.appendChild(e);
            e.setAttribute("m:val", "0");
            e=XMLD.createElement("m:dispDef");
            mathPr.appendChild(e);
            e=XMLD.createElement("m:lMargin");
            mathPr.appendChild(e);
            e.setAttribute("m:val", "0");
            e=XMLD.createElement("m:rMargin");
            mathPr.appendChild(e);
            e.setAttribute("m:val", "0");
            e=XMLD.createElement("m:defJc");
            mathPr.appendChild(e);
            e.setAttribute("m:val", "centerGroup");
            e=XMLD.createElement("m:wrapIndent");
            mathPr.appendChild(e);
            e.setAttribute("m:val", "1440");
            e=XMLD.createElement("m:intLim");
            mathPr.appendChild(e);
            e.setAttribute("m:val", "subSup");
            e=XMLD.createElement("m:naryLim");
            mathPr.appendChild(e);
            e.setAttribute("m:val", "undOvr");
            
            Element themeFontLang =addElement("w:themeFontLang");
            themeFontLang.setAttribute("w:val", "en-US");
            themeFontLang.setAttribute("w:eastAsia", "zh-CN");
            
            Element clrSchemeMapping  =addElement("w:clrSchemeMapping");
            clrSchemeMapping.setAttribute("w:bg1", "light1");
            clrSchemeMapping.setAttribute("w:t1", "dark1");
            clrSchemeMapping.setAttribute("w:bg2", "light2");
            clrSchemeMapping.setAttribute("w:t2", "dark2");
            clrSchemeMapping.setAttribute("w:hyperlink", "hyperlink");
            for(int i=1;i<7;i++){
            	clrSchemeMapping.setAttribute("w:accent"+i, "accent"+i);
            }
            clrSchemeMapping.setAttribute("w:followedHyperlink", "followedHyperlink");
            
            Element shapeDefaults=addElement("w:shapeDefaults");
            Element oshapedefaults =XMLD.createElement("o:shapedefaults");
            shapeDefaults.appendChild(oshapedefaults);
            oshapedefaults.setAttribute("v:ext", "edit");
            oshapedefaults.setAttribute("spidmax", "1026");
            Element oshapelayout =XMLD.createElement("o:shapelayout");
            shapeDefaults.appendChild(oshapelayout);
            oshapelayout.setAttribute("v:ext", "edit");
                Element oidmap =XMLD.createElement("o:idmap");
                oidmap.setAttribute("v:ext", "edit");
                oidmap.setAttribute("data", "1");
                oshapelayout.appendChild(oidmap);
            addElement("w:decimalSymbol").setAttribute("w:val", ".");
            addElement("w:listSeparator").setAttribute("w:val", ",");
            addElement("w15:chartTrackingRefBased");
            addElement("w15:docId").setAttribute("w15:val", "{16FAF661-571A-4978-9EDC-E5B1F4E7340D}");
        }
}
