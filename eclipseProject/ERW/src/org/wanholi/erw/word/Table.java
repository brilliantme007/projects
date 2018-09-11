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
public class Table {
    private Table(){}
    //static protected Table table=new Table();
    protected  Element tbl;
    protected  Element tblGrid;
    private static Document doc;
    protected static Table createTable(Document doc){
        return createTable(doc,null);
    }
    protected static Table createTable(Document doc,int colSize){
        float width[]=new float[colSize];
        for(int i=0;i<colSize;i++){
            width[i]=10f;
        }
        return createTable(doc,width);
    }
    protected static Table createTable(Document doc,float ...width){
        Table table=new Table();
        Table.doc=doc;
        table.tbl=doc.createElement("w:tbl");
        table.setTblPr();
        table.setTblGrid(width);
        return table;
    }
    public TableRow addTableRow(){
        TableRow  tr=TableRow.tableRow.createTableRow(doc,this);
        tbl.appendChild(tr.tr);
        return tr;
    }
    private void setTblPr(){
        Element tblpr=doc.createElement("w:tblPr");
        Element style=doc.createElement("w:tblStyle");
        style.setAttribute("w:val", "a3");
        tblpr.appendChild(style);
        Element tblw=doc.createElement("w:tblW");
        tblw.setAttribute("w:w", "0");
        tblw.setAttribute("w:type", "auto");
        tblpr.appendChild(tblw);
        Element tblLook=doc.createElement("w:tblLook");
        tblLook.setAttribute("w:val", "00A0");
        tblLook.setAttribute("w:firstRow", "1");
        tblLook.setAttribute("w:lastRow", "0");
        tblLook.setAttribute("w:firstColumn", "1");
        tblLook.setAttribute("w:lastColumn", "0");
        tblLook.setAttribute("w:noHBand", "0");
        tblLook.setAttribute("w:noVBand", "0");
        tblpr.appendChild(tblLook);
        tbl.appendChild(tblpr);
    }
    private void setTblGrid(float ...width){
        tblGrid=doc.createElement("w:tblGrid");
        Integer w;
        if(width!=null){
            for(int i=0;i<width.length;i++){
                Element gridCol =doc.createElement("w:gridCol");
                w=(int)(width[i]*566.5983606557377);
                gridCol.setAttribute("w:w", w.toString());
                tblGrid.appendChild(gridCol);
            }
        }
        tbl.appendChild(tblGrid);
    }
}
