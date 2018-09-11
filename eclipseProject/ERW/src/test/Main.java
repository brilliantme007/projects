package test;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.wanholi.erw.excel.XXlsx;
import org.wanholi.erw.pub.Alignment;
import org.wanholi.erw.pub.PageSize;
import org.wanholi.erw.word.Paragraph;
import org.wanholi.erw.word.Run;
import org.wanholi.erw.word.Table;
import org.wanholi.erw.word.TableColumn;
import org.wanholi.erw.word.TableRow;
import org.wanholi.erw.word.XDocx;


public class Main{
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, ParserConfigurationException   {

		XDocx docx=new XDocx();
		
		
		//Run r2=p1.addRun(" Word");
		//r1.setFontSize(48);
		//r2.setFontSize(24);
                Table t=docx.addTable(5);
                Paragraph p1=docx.createParagraph();
                p1.setTextAlign(Alignment.CENTER);
		Run r1=p1.addRun("Hello");
                TableRow tr=t.addTableRow();
                tr.addTableColumn(p1,3).showDefaultBorder();
                for(int i=0;i<8;i++){
                TableRow tc=t.addTableRow();
                if(i%2==0)
                    tc.addTableColumn(p1,true).showDefaultBorder().setVAlign(Alignment.CENTER);
                else tc.addTableColumn(p1,false).showDefaultBorder();
                tc.addTableColumn(p1).showDefaultBorder();
                tc.addTableColumn(p1).showDefaultBorder();
                }
		docx.save("D:/1.docx");

	}
	@Deprecated
	public static String getXDocument(Document doc) throws TransformerException {
		TransformerFactory  tf  =  TransformerFactory.newInstance();
		Transformer t = tf.newTransformer();
		t.setOutputProperty("encoding","UTF-8");
		ByteArrayOutputStream  bos  =  new  ByteArrayOutputStream();
		t.transform(new DOMSource(doc), new StreamResult(bos));
		String xmlStr = bos.toString();
		return xmlStr;
	}


}
