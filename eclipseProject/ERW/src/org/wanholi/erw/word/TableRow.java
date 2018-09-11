/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wanholi.erw.word;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author nchan
 */
public class TableRow {
    private TableRow(){};
    static protected TableRow tableRow=new TableRow();
    protected Element tr;
    private Document doc;
    private Table table;
    protected TableRow createTableRow(Document doc,Table table){
        tableRow=new TableRow();
        this.doc=doc;
        this.table=table;
        tr=doc.createElement("w:tr");
        tr.setAttribute("w:rsidR", "00CF0BBC");
        tr.setAttribute("w:rsidTr", "00CF0BBC");
        return this;
    }
    
    public TableColumn addTableColumn(Paragraph paragraph){
        int i=tr.getElementsByTagName("w:tc").getLength();
        String width=table.tblGrid.getChildNodes().item(i).getAttributes().getNamedItem("w:w").getNodeValue();
        TableColumn tc=TableColumn.tableColumn.createTableColumn(doc, Integer.parseInt(width),paragraph,0,null);
        tr.appendChild(tc.tc);
        return tc;
    }
    /**
     * 
     * @param paragraph
     * @param colSpan 
     * @return 
     */
    public TableColumn addTableColumn(Paragraph paragraph,Integer colSpan){
        int i=tr.getElementsByTagName("w:tc").getLength();
        String width=table.tblGrid.getChildNodes().item(i).getAttributes().getNamedItem("w:w").getNodeValue();
        TableColumn tc=TableColumn.tableColumn.createTableColumn(doc, Integer.parseInt(width),paragraph,colSpan,null);
        tr.appendChild(tc.tc);
        return tc;
    }
    /**
     * 
     * @param paragraph
     * @param mergeType 是否合并行.true:另起一行合并；false：开始合并行
     * @return 
     */
    public TableColumn addTableColumn(Paragraph paragraph,Boolean mergeType){
        int i=tr.getElementsByTagName("w:tc").getLength();
        String width=table.tblGrid.getChildNodes().item(i).getAttributes().getNamedItem("w:w").getNodeValue();
        TableColumn tc=TableColumn.tableColumn.createTableColumn(doc, Integer.parseInt(width),paragraph,0,mergeType);
        tr.appendChild(tc.tc);
        return tc;
    }
    /**
     * 设置行高度 单位：厘米
     * @param height 高度值
     * @param isExact 是否固定高度，默认false
     * @return 
     */
    public TableRow setHeight(Double height,Boolean isExact){
        Element tcpr=null;
        if(this.tr.getElementsByTagName("w:tcPr")!=null)
            tcpr=(Element) this.tr.getElementsByTagName("w:tcPr").item(0);
                
        if(tcpr==null){
            tcpr=this.doc.createElement("w:tcPr");
            this.tr.appendChild(tcpr);
        }
        Element trHeight=this.doc.createElement("w:trHeight");
        trHeight.setAttribute("w:val", Integer.toString((int)(height*567.68)));
        if(isExact)
            trHeight.setAttribute("w:hRule", "exact");
        tcpr.appendChild(trHeight);
        return this;
    }
    /**
     * 是否允许跨页断行
     * @param canSplit 默认允许
     * @return 
     */
    public TableRow isCanSplit(Boolean canSplit){
        Element tcpr=null;
        if(this.tr.getElementsByTagName("w:tcPr")!=null)
            tcpr=(Element) this.tr.getElementsByTagName("w:tcPr").item(0);
                
        if(tcpr==null){
            tcpr=this.doc.createElement("w:tcPr");
            this.tr.appendChild(tcpr);
        }
        if(!canSplit){
            Element w_cantSplit=this.doc.createElement("w:cantSplit");
            tcpr.appendChild(w_cantSplit);
        }
        else tcpr.removeChild(tcpr.getElementsByTagName("w:cantSplit").item(0));
        return  this;
    }
}
