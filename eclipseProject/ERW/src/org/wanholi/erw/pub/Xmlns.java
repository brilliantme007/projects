/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wanholi.erw.pub;

/**
 *
 * @author nchan
 */
public enum Xmlns {
    
    xmlnswpc("http://schemas.microsoft.com/office/word/2010/wordprocessingCanvas"),
    xmlnsmc("http://schemas.openxmlformats.org/markup-compatibility/2006"),
    xmlnso("urn:schemas-microsoft-com:office:office"),
    xmlnsm("http://schemas.openxmlformats.org/officeDocument/2006/math"),
    xmlnsv("urn:schemas-microsoft-com:vml"),
    xmlnswp14("http://schemas.microsoft.com/office/word/2010/wordprocessingDrawing"),
    xmlnswp("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing"),
    xmlnsw10("urn:schemas-microsoft-com:office:word"),
    xmlnsw("http://schemas.openxmlformats.org/wordprocessingml/2006/main"),
    xmlnsw14("http://schemas.microsoft.com/office/word/2010/wordml"),
    xmlnsw15("http://schemas.microsoft.com/office/word/2012/wordml"),
    xmlnswpg("http://schemas.microsoft.com/office/word/2010/wordprocessingGroup"),
    xmlnswpi("http://schemas.microsoft.com/office/word/2010/wordprocessingInk"),
    xmlnswne("http://schemas.microsoft.com/office/word/2006/wordml"),
    xmlnswps("http://schemas.microsoft.com/office/word/2010/wordprocessingShape"),
    mcIgnorable("w14 w15 wp14"),
    xmlnssl("http://schemas.openxmlformats.org/schemaLibrary/2006/main"),
    xmlnsr("http://schemas.openxmlformats.org/officeDocument/2006/relationships");

    
    public  String string;
    private Xmlns(String string){
        this.string=string;
    }
}
