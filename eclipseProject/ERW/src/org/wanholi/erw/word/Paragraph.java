/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wanholi.erw.word;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.wanholi.erw.pub.Alignment;
import org.wanholi.erw.pub.XXML;

/**
 *2017.11.25
 * @author nchan
 * @version 1.0
 */
public final class Paragraph {
    static protected Paragraph Para=new Paragraph();
    protected Element paragraph;
    private Document xmld;
    private Paragraph(){}
    protected Paragraph createPara(Document xmld){
    	Para=new Paragraph();
        this.xmld=xmld;
        paragraph=xmld.createElement("w:p");
        paragraph.setAttribute("w:rsidR", "00511BB0");
        paragraph.setAttribute("w:rsidRDefault", "00511BB0");
        PPR ppr=new PPR(xmld);
        paragraph.appendChild(ppr.ppr);
        return  this;
    }
    public Paragraph setTextAlign(Alignment alignment){
        Element jc=xmld.createElement("w:jc");
        jc.setAttribute("w:val", alignment.val);
        Element ppr=(Element) paragraph.getElementsByTagName("w:pPr").item(0);
        ppr.insertBefore(jc, ppr.getElementsByTagName("w:rPr").item(0));
        return this;
    }
    public Run addRun(String text){
        Run run=Run.r.creatRun(xmld, text);
        paragraph.appendChild(run.run);
        return run;
    }

    class PPR{
        Element ppr;
        PPR(Document xmld){
            ppr=xmld.createElement("w:pPr");
            Element rpr=xmld.createElement("w:rPr");
            Element rFonts =xmld.createElement("w:rFonts");
            rFonts.setAttribute("w:hint", "eastAsia");
            rpr.appendChild(rFonts);
            ppr.appendChild(rpr);
        }
    }
}
