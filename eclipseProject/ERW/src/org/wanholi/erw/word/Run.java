/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wanholi.erw.word;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *2017.11.28
 * @author nchan
 * @version 1.0
 */
public class Run {
    static protected Run r=new Run();
    protected Element run;
    @Deprecated
    Run(){}
    private Document doc;
    protected Run creatRun(Document doc,String text){
    	r=new Run();
        this.doc=doc;
        run=doc.createElement("w:r");
        run.setAttribute("w:rsidRPr", "008305EA");
        Element t=doc.createElement("w:t");
        t.setTextContent(text);
        run.appendChild(t);
        return this;
    }
    public Run setFontSize(Integer size){
        size=size*2;
        Element rpr= (Element) run.getElementsByTagName("w:rPr").item(0);
        if(rpr==null){
            rpr=doc.createElement("w:rPr");
            run.insertBefore(rpr, run.getElementsByTagName("w:t").item(0));
        }
        Element sz=doc.createElement("w:sz");
        sz.setAttribute("w:val", size.toString());
        rpr.appendChild(sz);
        return this;
    }
    
}
