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
import org.wanholi.erw.pub.Font;
import org.wanholi.erw.pub.XXML;

/**
 *重用
 * @author lifei
 * @version 1.0
 * 2017.11.14
 */
public class Theme extends XXML{
    String path="word"+File.separator+"theme"+File.separator+"theme1.xml";
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	Theme() throws ParserConfigurationException {
		super();
		DocumentBuilder db = dbf.newDocumentBuilder();
		XMLD =  db.newDocument();
		XMLD.setXmlStandalone(true);
		root=XMLD.createElement("a:theme");
		XMLD.appendChild(root);
	}
        clrScheme CLRScheme;
        fontScheme FONTScheme;
        fmtScheme FMTScheme;
	void build(){
            setAttribute("xmlns:a", "http://schemas.openxmlformats.org/drawingml/2006/main");
            setAttribute("name", "Office 主题");
            Element themeElements=addElement("a:themeElements");
            
            ClrScheme=XMLD.createElement("a:clrScheme");
            ClrScheme.setAttribute("name", "Office");
            CLRScheme=new clrScheme();
            themeElements.appendChild(ClrScheme);
            FontScheme=XMLD.createElement("a:fontScheme");
            FontScheme.setAttribute("name", "Office");
            FONTScheme=new fontScheme();
            themeElements.appendChild(FontScheme);
            FmtScheme=XMLD.createElement("a:fmtScheme");
            FmtScheme.setAttribute("name", "Office");
            FMTScheme=new fmtScheme();
            themeElements.appendChild(FmtScheme);
            
            addElement("a:objectDefaults");
            addElement("a:extraClrSchemeLst");
//            Element extLst=addElement("a:extLst");
//                Element ext=XMLD.createElement("a:ext");
//                ext.setAttribute("uri", "{05A4C25C-085E-4340-85A3-A5531E510DB2}");
//                extLst.appendChild(ext);
//                    Element themeFamily=XMLD.createElement("thm15:themeFamily");
//                    themeFamily.setAttribute("xmlns:thm15", "http://schemas.microsoft.com/office/thememl/2012/main");
//                    themeFamily.setAttribute("name", "Office Theme");
//                    themeFamily.setAttribute("id", "{62F939B6-93AF-4DB8-9C6B-D6C7DFDC589F}");
//                    themeFamily.setAttribute("vid", "{4A3C46E8-61CC-4603-A589-7422A47A8E4A}");
//                ext.appendChild(themeFamily);
        }
        Element ClrScheme;
        Element FontScheme;
        Element FmtScheme;
        class clrScheme{
        clrScheme() {
            Element dk1=XMLD.createElement("a:dk1");
                Element sysClr1=XMLD.createElement("a:sysClr");
                sysClr1.setAttribute("val", "windowText");sysClr1.setAttribute("lastClr", "000000");
                dk1.appendChild(sysClr1);
            ClrScheme.appendChild(dk1);
            Element lt1=XMLD.createElement("a:lt1");
                Element sysClr2=XMLD.createElement("a:sysClr");
                sysClr2.setAttribute("val", "window");sysClr2.setAttribute("lastClr", "FFFFFF");
                lt1.appendChild(sysClr2);
            ClrScheme.appendChild(lt1);
            Element dk2=XMLD.createElement("a:dk2");
                Element srgbClr1=XMLD.createElement("a:srgbClr");
                srgbClr1.setAttribute("val", "1F497D");
                dk2.appendChild(srgbClr1);
            ClrScheme.appendChild(dk2);
            Element lt2=XMLD.createElement("a:lt2");
                Element srgbClr2=XMLD.createElement("a:srgbClr");
                srgbClr2.setAttribute("val", "EEECE1");
                lt2.appendChild(srgbClr2);
            ClrScheme.appendChild(lt2);
            String vals[]="4F81BD C0504D 9BBB59 8064A2 4BACC6 F79646".split(" ");
            for(int i=1;i<=vals.length;i++){
                Element e=XMLD.createElement("a:accent"+i);
                Element srgbClr3=XMLD.createElement("a:srgbClr");
                srgbClr3.setAttribute("val", vals[i-1]);
                e.appendChild(srgbClr3);
                ClrScheme.appendChild(e);
            }
            Element hlink=XMLD.createElement("a:hlink");
                Element srgbClr4=XMLD.createElement("a:srgbClr");
                srgbClr4.setAttribute("val", "0000FF");
                hlink.appendChild(srgbClr4);
            ClrScheme.appendChild(hlink);
            Element folHlink=XMLD.createElement("a:folHlink");
                Element srgbClr5=XMLD.createElement("a:srgbClr");
                srgbClr5.setAttribute("val", "800080");
                folHlink.appendChild(srgbClr5);
            ClrScheme.appendChild(folHlink);
            
        }
            
        }
        class fontScheme{
            fontScheme(){
                Element majorFont=XMLD.createElement("a:majorFont");
                Element minorFont=XMLD.createElement("a:minorFont");
                    Element latin=XMLD.createElement("a:latin");
                    Element latin2=XMLD.createElement("a:latin");
                    latin.setAttribute("typeface", "Cambria");
                   // latin.setAttribute("panose", "020F0302020204030204");
                    latin2.setAttribute("typeface", "Calibri");
                    //latin2.setAttribute("panose", "020F0502020204030204");
                    majorFont.appendChild(latin);
                    minorFont.appendChild(latin2);
                    Element ea=XMLD.createElement("a:ea");
                    ea.setAttribute("typeface", "");
                    majorFont.appendChild(ea);
                    minorFont.appendChild(ea.cloneNode(false));
                    Element cs=XMLD.createElement("a:cs");
                    cs.setAttribute("typeface", "");
                    majorFont.appendChild(cs);
                    minorFont.appendChild(cs.cloneNode(false));
                    for(String key:Font.font.keySet()){
                        Element font=XMLD.createElement("a:font");
                        font.setAttribute("script", key);
                        font.setAttribute("typeface", Font.font.get(key));
                        majorFont.appendChild(font);
                    }
                    for(String key:Font.minorfont.keySet()){
                        Element font=XMLD.createElement("a:font");
                        font.setAttribute("script", key);
                        font.setAttribute("typeface", Font.minorfont.get(key));
                        minorFont.appendChild(font);
                    }
                FontScheme.appendChild(majorFont);
                FontScheme.appendChild(minorFont);
            }
        }
        class fmtScheme{
            class SchemeClr{
                Element getSchemeClr(String val1,Integer tint,Integer shade,Integer satMod) {
                    Element schemeClr=XMLD.createElement("a:schemeClr");
                    schemeClr.setAttribute("val", val1);
                    if(tint!=null){
                        Element Tint=XMLD.createElement("a:tint");
                        Tint.setAttribute("val", tint.toString());
                        schemeClr.appendChild(Tint);
                    }
                    if(shade!=null){
                        Element Shade=XMLD.createElement("a:shade");
                        Shade.setAttribute("val", shade.toString());
                        schemeClr.appendChild(Shade);
                    }
                    if(satMod!=null){
                        Element SatMod=XMLD.createElement("a:satMod");
                        SatMod.setAttribute("val", satMod.toString());
                        schemeClr.appendChild(SatMod);
                    }
                    return schemeClr;
                }
                
            }
            SchemeClr Scheme=new SchemeClr();
            class OuterShdw{
            	Element getOuterShdw(Integer blurRad,Integer dist,Integer dir,Integer alpha ){
            		Element outerShdw=XMLD.createElement("a:outerShdw");
            		outerShdw.setAttribute("blurRad", blurRad.toString());outerShdw.setAttribute("dist", dist.toString());
            		outerShdw.setAttribute("dir", dir.toString());outerShdw.setAttribute("rotWithShape", "0");
            			Element SrgbClr=XMLD.createElement("a:srgbClr");
            			SrgbClr.setAttribute("val", "000000");
            			outerShdw.appendChild(SrgbClr);
            				Element Alpha=XMLD.createElement("a:alpha");
            				Alpha.setAttribute("val", alpha.toString());
            				SrgbClr.appendChild(Alpha);
            		return outerShdw;
            	}
            }
            OuterShdw Outer=new OuterShdw();
            fmtScheme() {
                Element fillStyleLst=XMLD.createElement("a:fillStyleLst");
                FmtScheme.appendChild(fillStyleLst);
                    Element solidFill=XMLD.createElement("a:solidFill");
                    fillStyleLst.appendChild(solidFill);
                        Element schemeClr=XMLD.createElement("a:schemeClr");
                        schemeClr.setAttribute("val", "phClr");
                        solidFill.appendChild(schemeClr);
                    Element gradFill;
                    Integer val[][]={{0,50000,300000,35000,37000,300000,100000,15000,350000,1},
                                     {0,51000,130000,80000,93000,130000,100000,94000,135000,0}};
                    for(int j=0;j<2;j++){
                        gradFill=XMLD.createElement("a:gradFill");
                        gradFill.setAttribute("rotWithShape", "1");
                        fillStyleLst.appendChild(gradFill);
                            Element gsLst=XMLD.createElement("a:gsLst");
                            gradFill.appendChild(gsLst);
                                Element gs;
                                for(int i=0;i<3;i++){
                                    gs=XMLD.createElement("a:gs");
                                    gs.setAttribute("pos", val[j][(i+1)*3-3].toString());
                                    if(j==0)
                                        gs.appendChild(Scheme.getSchemeClr("phClr", val[j][(i+1)*3-2], null, val[j][(i+1)*3-1]));
                                    else
                                        gs.appendChild(Scheme.getSchemeClr("phClr", null, val[j][(i+1)*3-2], val[j][(i+1)*3-1]));
                                    gsLst.appendChild(gs);
                                }
                            Element lin=XMLD.createElement("a:lin");
                            gradFill.appendChild(lin);
                            lin.setAttribute("ang", "16200000");lin.setAttribute("scaled", val[j][9].toString());
                    }
                Element lnStyleLst=XMLD.createElement("a:lnStyleLst");
                FmtScheme.appendChild(lnStyleLst);
                Element ln;
                Integer val2[]={9525,25400,38100};
                for(int i=0;i<3;i++){
                    ln=XMLD.createElement("a:ln");
                    ln.setAttribute("w", val2[i].toString());ln.setAttribute("cap", "flat");ln.setAttribute("cmpd", "sng");ln.setAttribute("algn", "ctr");
                        Element solidFill1=XMLD.createElement("a:solidFill");
                        ln.appendChild(solidFill1);
                        if(i==0)
                            solidFill1.appendChild(Scheme.getSchemeClr("phClr", null, 95000, 105000));
                        else 
                            solidFill1.appendChild(Scheme.getSchemeClr("phClr", null, null, null));
                        
                        Element prstDash=XMLD.createElement("a:prstDash");
                        prstDash.setAttribute("val", "solid");
                        ln.appendChild(prstDash);
                        lnStyleLst.appendChild(ln);
                            
                }
                Element effectStyleLst=XMLD.createElement("a:effectStyleLst");
                FmtScheme.appendChild(effectStyleLst);
                Element effectStyle;
                for(int i=0;i<3;i++){
                	effectStyle=XMLD.createElement("a:effectStyle");
                	effectStyleLst.appendChild(effectStyle);
                		Element effectLst=XMLD.createElement("a:effectLst");
                		effectStyle.appendChild(effectLst);
                		if(i==0)
                			effectLst.appendChild(Outer.getOuterShdw(40000, 20000, 5400000, 38000));
                		else
                			effectLst.appendChild(Outer.getOuterShdw(40000, 23000, 5400000, 35000));
                		
                		if(i==2){
                			Element scene3d=XMLD.createElement("a:scene3d");
                			effectStyle.appendChild(scene3d);
                				Element camera =XMLD.createElement("a:camera");
                				camera.setAttribute("prst", "orthographicFront");
                				scene3d.appendChild(camera);
                					Element rot=XMLD.createElement("a:rot");
                					camera.appendChild(rot);
                					rot.setAttribute("lat", "0");rot.setAttribute("lon", "0");rot.setAttribute("rev", "0");
                				Element lightRig =XMLD.createElement("a:lightRig");
                				lightRig.setAttribute("rig", "threePt");lightRig.setAttribute("dir", "t");
                				scene3d.appendChild(lightRig);
                					Element rot1=XMLD.createElement("a:rot");
                					lightRig.appendChild(rot1);
                					rot1.setAttribute("lat", "0");rot1.setAttribute("lon", "0");rot1.setAttribute("rev", "1200000");
                			Element sp3d=XMLD.createElement("a:sp3d");
                			effectStyle.appendChild(sp3d);
                				Element bevelT =XMLD.createElement("a:bevelT");
                				sp3d.appendChild(bevelT);
                				bevelT.setAttribute("w", "63500");bevelT.setAttribute("h", "25400");
                		}
                }
                Element bgFillStyleLst=XMLD.createElement("a:bgFillStyleLst");
                FmtScheme.appendChild(bgFillStyleLst);
                	Element solidFill1=XMLD.createElement("a:solidFill");
                	bgFillStyleLst.appendChild(solidFill1);
                	solidFill1.appendChild(Scheme.getSchemeClr("phClr", null, null, null));
                	Element gradFill1 =XMLD.createElement("a:gradFill");
                	gradFill1.setAttribute("rotWithShape", "1");
                	bgFillStyleLst.appendChild(gradFill1);
                		Element gsLst=XMLD.createElement("a:gsLst");
                		gradFill1.appendChild(gsLst);
                		Integer val1[][]={{0,40000,null,350000},{40000,45000,99000,350000},
                				{100000,null,20000,255000}};
                		Element gs;
                		for(int i=0;i<3;i++){
                			gs=XMLD.createElement("a:gs");
                			gs.setAttribute("pos", val1[i][0].toString());
                			gs.appendChild(Scheme.getSchemeClr("phClr", val1[i][1], val1[i][2], val1[i][3]));
                			gsLst.appendChild(gs);
                		}
                		Element path =XMLD.createElement("a:path");
                		path.setAttribute("path", "circle");
                		gradFill1.appendChild(path);
                			Element fillToRect =XMLD.createElement("a:fillToRect");
                			path.appendChild(fillToRect);
                			fillToRect.setAttribute("l", "50000");fillToRect.setAttribute("t", "-80000");
                			fillToRect.setAttribute("r", "50000");fillToRect.setAttribute("b", "180000");
                	
                	Element gradFill2=(Element) gradFill1.cloneNode(false);
                	bgFillStyleLst.appendChild(gradFill2);
                	Element gsLst2=XMLD.createElement("a:gsLst");
            		gradFill2.appendChild(gsLst2);
            		Integer val3[][]={{0,80000,null,300000},{100000,null,30000,200000}};
            		Element gs1;
            		for(int i=0;i<2;i++){
            			gs1=XMLD.createElement("a:gs");
            			gs1.setAttribute("pos", val3[i][0].toString());
            			gs1.appendChild(Scheme.getSchemeClr("phClr", val3[i][1], val3[i][2], val3[i][3]));
            			gsLst2.appendChild(gs1);
            		}
            		Element path1 =XMLD.createElement("a:path");
            		path1.setAttribute("path", "circle");
            		gradFill2.appendChild(path1);
            			Element fillToRect1 =XMLD.createElement("a:fillToRect");
            			path1.appendChild(fillToRect1);
            			fillToRect1.setAttribute("l", "50000");fillToRect1.setAttribute("t", "50000");
            			fillToRect1.setAttribute("r", "50000");fillToRect1.setAttribute("b", "50000");
            			
            }
            
        }
}
