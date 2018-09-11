
package org.wanholi.erw.word;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.wanholi.erw.pub.Alignment;
import org.wanholi.erw.pub.BorderType;

/**
 *2018.1.2
 * @author lifei
 * @version 1.0
 */
public class TableColumn {
    private TableColumn(){};
    static protected TableColumn tableColumn=new TableColumn();
    protected Element tc;
    private Document doc;
    private Element tcBorder;
    private Element tcPr;
    protected TableColumn createTableColumn(Document doc,Integer width,Paragraph p,Integer colSpan,Boolean mergeType){
        tableColumn=new TableColumn();
        this.doc=doc;
        tc=doc.createElement("w:tc");
        tcPr=doc.createElement("w:tcPr");
        Element tcW=doc.createElement("w:tcW");
        tcW.setAttribute("w:w", width.toString());
        tcW.setAttribute("w:type", "dxa");
        tcPr.appendChild(tcW);
        if(colSpan>1){
            Element gridSpan=doc.createElement("w:gridSpan");
            gridSpan.setAttribute("w:val", colSpan.toString());
            tcPr.appendChild(gridSpan);
        }
        if(mergeType != null){
            Element merge=doc.createElement("w:vMerge");
            if(mergeType)
                merge.setAttribute("w:val", "restart");
            tcPr.appendChild(merge);
        }
        tcBorder=doc.createElement("w:tcBorders");
        tc.appendChild(tcPr);
        if(mergeType != null && !mergeType)
            tc.appendChild(p.paragraph.cloneNode(false));
        else 
            tc.appendChild(p.paragraph.cloneNode(true));
        System.out.println(p.paragraph.getElementsByTagName("w:t").item(0).getTextContent());
        return this;
    }
    private TableColumn setBorder(String type,BorderType borderType,String color,Integer space,Integer size){
        //if(tcPr.getElementsByTagName("w:tcBorders")==null)
            tcPr.appendChild(tcBorder);
        Element e=doc.createElement(type);
        e.setAttribute("w:val", borderType.string);
        e.setAttribute("w:color", color);
        e.setAttribute("w:space", space.toString());
        e.setAttribute("w:sz", size.toString());
        tcBorder.appendChild(e);
        return this;
    }
    public TableColumn setBorderTop(BorderType borderType,String color,Integer space,Integer size){
        return setBorder("w:top", borderType, color, space, size);
    }
    public TableColumn setBorderRight(BorderType borderType,String color,Integer space,Integer size){
        return setBorder("w:right", borderType, color, space, size);
    }
    public TableColumn setBorderLeft(BorderType borderType,String color,Integer space,Integer size){
        return setBorder("w:left", borderType, color, space, size);
    }
    public TableColumn setBorderBottom(BorderType borderType,String color,Integer space,Integer size){
        return setBorder("w:bottom", borderType, color, space, size);
    }
    /**
     * 显示默认边框
     * @return 
     */
    public TableColumn showDefaultBorder(){
        setBorderBottom(BorderType.SINGLE, "auto", 0, 4).setBorderLeft(BorderType.SINGLE, "auto", 0, 4)
                        .setBorderRight(BorderType.SINGLE,  "auto", 0, 4).setBorderTop(BorderType.SINGLE, "auto", 0, 4);
        return this;
    }
    /**
     * 垂直对齐方式
     * @param valign
     * @return 
     */
    public TableColumn setVAlign(Alignment valign){
        Element align=doc.createElement("w:vAlign");
        align.setAttribute("w:val", valign.val);
        tcPr.appendChild(align);
        return this;
    }
}
