package org.wanholi.erw.word;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.wanholi.erw.pub.XXML;
import org.wanholi.erw.pub.Xmlns;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * 文档document.xml
 *
 * @author lifei
 */
public class XDocument extends XXML {

    String path = "word" + File.separator + "document.xml";
    // 得到DOM解析器的工厂实例 
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    /**
     * 构建xml文档
     *
     * @throws ParserConfigurationException
     */
    XDocument() throws ParserConfigurationException {
        super();
        DocumentBuilder db = dbf.newDocumentBuilder();
        XMLD = db.newDocument();
        XMLD.setXmlStandalone(true);
        root = XMLD.createElement("w:document");
        XMLD.appendChild(root);
    }

    /**
     * 读取xml文档
     *
     * @throws ParserConfigurationException
     */
    XDocument(String xmlPath) throws ParserConfigurationException, SAXException, IOException {
        // 从DOM工厂中获得DOM解析器  
        DocumentBuilder db = dbf.newDocumentBuilder();
        // 把要解析的xml文档读入DOM解析器
        XMLD = db.parse(xmlPath);
        XMLD.setXmlStandalone(true);
        body=(Element) XMLD.getElementsByTagName("w:body").item(0);
        //root=(Element) XMLD.getElementsByTagName("w:document").item(0);
    }
    Element body;

    void build() {
        setAttribute("xmlns:wpc", Xmlns.xmlnswpc.string);
        setAttribute("xmlns:mc", Xmlns.xmlnsmc.string);
        setAttribute("xmlns:o", Xmlns.xmlnso.string);
        setAttribute("xmlns:r", Xmlns.xmlnsr.string);
        setAttribute("xmlns:m", Xmlns.xmlnsm.string);
        setAttribute("xmlns:v", Xmlns.xmlnsv.string);
        setAttribute("xmlns:wp14", Xmlns.xmlnswp14.string);
        setAttribute("xmlns:wp", Xmlns.xmlnswp.string);
        setAttribute("xmlns:w10", Xmlns.xmlnsw10.string);
        setAttribute("xmlns:w", Xmlns.xmlnsw.string);
        setAttribute("xmlns:w14", Xmlns.xmlnsw14.string);
        setAttribute("xmlns:w15", Xmlns.xmlnsw15.string);
        setAttribute("xmlns:wpg", Xmlns.xmlnswpg.string);
        setAttribute("xmlns:wpi", Xmlns.xmlnswpi.string);
        setAttribute("xmlns:wne", Xmlns.xmlnswne.string);
        setAttribute("xmlns:wps", Xmlns.xmlnswps.string);
        setAttribute("mc:Ignorable", Xmlns.mcIgnorable.string);
        body = addElement("w:body");
    }

    Paragraph addParagraph() {
        Paragraph para = Paragraph.Para.createPara(XMLD);
        body.appendChild(para.paragraph);
        return para;
    }

    Paragraph createParagraph() {
        Paragraph para = Paragraph.Para.createPara(XMLD);
        return para;
    }

    Table addTable() {
        Table table = Table.createTable(XMLD);
        body.appendChild(table.tbl);
        return table;
    }

    Table addTable(int colSize) {
        Table table = Table.createTable(XMLD, colSize);
        body.appendChild(table.tbl);
        return table;
    }

    Table addTable(float... width) {
        Table table = Table.createTable(XMLD, width);
        body.appendChild(table.tbl);
        return table;
    }

    /**
     * 在指定位置插入表格
     *
     * @param target
     * @param colSize
     * @return
     */
    Table insertTable(String target, int colSize) {
        Table table = Table.createTable(XMLD, colSize);
        NodeList list = XMLD.getElementsByTagName("w:p");
        for (int i = 0, n = list.getLength(); i < n; i++) {
            Element element = (Element) list.item(i);
            System.out.println(element.getTextContent());
            if (element.getTextContent().equals("#" + target + "#")) {
                XMLD.getElementsByTagName("w:body").item(0).replaceChild(table.tbl, element);
                break;
            }
            
        }
        return table;
    }

    /**
     * 文本内容替换，map中的key为文档中的内容，value为替换后的内容
     *
     * @param replaceMap
     */
    void replace(Map<String, String> replaceMap) {
        try {
//Document转String
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            t.setOutputProperty("encoding", "UTF-8");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            t.transform(new DOMSource(XMLD), new StreamResult(bos));
            String xmlStr = bos.toString();
//替换
            for (String key : replaceMap.keySet()) {
                xmlStr = xmlStr.replace(key, replaceMap.get(key).toString());
            }
//String转Document
            StringReader sr = new StringReader(xmlStr);
            InputSource is = new InputSource(sr);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            XMLD = builder.parse(is);
            body=(Element) XMLD.getElementsByTagName("w:body").item(0);

        } catch (Exception e) {
        }
    }

    void setDocEnd() {
        NodeList list = body.getElementsByTagName("w:p");
        if (list.getLength() == 0) {
            addParagraph();
        }
        Node lastP = list.item(list.getLength() - 1);
        Element bookmarkStart = XMLD.createElement("w:bookmarkStart");
        bookmarkStart.setAttribute("w:id", "0");
        bookmarkStart.setAttribute("w:name", "_GoBack");
        lastP.appendChild(bookmarkStart);
        Element bookmarkEnd = XMLD.createElement("w:bookmarkEnd");
        bookmarkEnd.setAttribute("w:id", "0");
        lastP.appendChild(bookmarkEnd);
        if(XMLD.getElementsByTagName("w:sectPr")==null || XMLD.getElementsByTagName("w:sectPr").getLength()==0);
        else{
        sectPr SPr = new sectPr();
        body.appendChild(SPr.SectPr);
        }
    }
//	public String getXDocument() throws TransformerException {
//		TransformerFactory  tf  =  TransformerFactory.newInstance();
//		Transformer t = tf.newTransformer();
//		t.setOutputProperty("encoding","UTF-8");//解决中文问题，试过用GBK不行
//		ByteArrayOutputStream  bos  =  new  ByteArrayOutputStream();
//		t.transform(new DOMSource(doc), new StreamResult(bos));
//		String xmlStr = bos.toString();
//		return xmlStr;
//	}

    class sectPr {

        Element SectPr;
        Element pgSz;
        Element pgMar;

        sectPr() {
            SectPr = XMLD.createElement("w:sectPr");
            SectPr.setAttribute("w:rsidR", "00511BB0");
            pgSz = XMLD.createElement("w:pgSz");
            pgSz.setAttribute("w:w", "11906");
            pgSz.setAttribute("w:h", "16838");
            SectPr.appendChild(pgSz);
            pgMar = XMLD.createElement("w:pgMar");
            pgMar.setAttribute("w:top", "1440");
            pgMar.setAttribute("w:right", "1800");
            pgMar.setAttribute("w:bottom", "1440");
            pgMar.setAttribute("w:left", "1800");
            pgMar.setAttribute("w:header", "851");
            pgMar.setAttribute("w:footer", "992");
            pgMar.setAttribute("w:gutter", "0");
            SectPr.appendChild(pgMar);

            Element cols = XMLD.createElement("w:cols");
            cols.setAttribute("w:space", "425");
            SectPr.appendChild(cols);
            Element docGrid = XMLD.createElement("w:docGrid");
            docGrid.setAttribute("w:type", "lines");
            docGrid.setAttribute("w:linePitch", "312");
            SectPr.appendChild(docGrid);
        }
    }
}
