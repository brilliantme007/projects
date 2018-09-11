/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wanholi.erw.word;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.wanholi.erw.pub.Util;

/**
 *
 * @author nchan
 */
public class XDocx {
    
 List<File> files = new ArrayList<File>();
    String folderName = String.valueOf(System.currentTimeMillis());
    String tempPath=new StringBuffer(System.getProperty("java.io.tmpdir")).append(folderName).append(File.separator).toString();
XDocument doc;

    /**
     * 创建新的Docx文档
     * @throws ParserConfigurationException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchFieldException 
     */
    public XDocx() throws ParserConfigurationException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
        String clazzs[] = {"App", "ContentTypes", "Core", "Rels", "Styles", "Theme", "WebSetting", "DocumentX", "FontTable","Settings"};
        for (String className : clazzs) {
            Class class1 = Class.forName("org.wanholi.erw.word." + className);
            Object object = class1.newInstance();
            Method method = class1.getDeclaredMethod("build");
            method.invoke(object);
            Field field = class1.getDeclaredField("path");
            String path = (String) field.get(object);
            Method method2 = class1.getSuperclass().getDeclaredMethod("getDocument");
            Document document = (Document) method2.invoke(object);
            doc2XML(document, tempPath + path);

        }
        //初始化document.xml
        doc=new XDocument();
        {
           doc.build();
        }

    }
    /**
     * 读取外部docx文档
     * @param templetPath 文件全路径及名称，如D:/test/temp.docx
     * @throws Exception 
     */
    public XDocx(String templetPath) throws Exception{
        if(!templetPath.endsWith(".docx")&&!templetPath.endsWith(".DOCX"))
           throw new Exception("Unsupported file type!");
       else{
           File file=new File(templetPath);
           ZipFile zfile=new ZipFile(templetPath);
           //临时文件夹
           folderName = String.valueOf(System.currentTimeMillis());
           tempPath=new StringBuffer(System.getProperty("java.io.tmpdir")).append(folderName).append(File.separator).toString();
           ZipInputStream zis=new ZipInputStream(new FileInputStream(file));
           ZipEntry zipEntry;
           //遍历并解压文档中的文件
           while ((zipEntry=zis.getNextEntry())!=null) {
               System.out.println(zipEntry.getName());
               InputStream in= zfile.getInputStream(zipEntry);
               //存为临时文件
               File tempFile=new File(tempPath+File.separator+zipEntry.getName());
               if(!tempFile.exists()){
                   Util.creatNewFile(tempPath+File.separator+zipEntry.getName());
               }
               OutputStream out=new FileOutputStream(tempFile);
                    int i=0;
                    while ((i=in.read()) != -1) {
                        out.write(i);
                    }
                    out.flush();
                    out.close();
                    in.close();
           }
           
       }
        doc=new XDocument(tempPath+File.separator+"word"+File.separator+"document.xml");
    }
    
  /**
    * 在指定位置插入表格
    * @param target 插入的位置标记，例如：在word中新建一段落，内容为“#TBL#”，在此传入“TBL”即可
    * @param cols 要插入的表格列数
    */
   public Table insertTable(String target,int cols){
       Table table=doc.insertTable(target,6);
       return table;
   }
   /**
         * 文本内容替换，map中的key为文档中的内容，value为替换后的内容
         * @param replaceMap 
         */
       public void replace(Map<String,String> replaceMap){
           doc.replace(replaceMap);
        
   }
    public Paragraph addParagraph(){
        return doc.addParagraph();
    }
    public Paragraph createParagraph(){
        return doc.createParagraph();
    }
    public Table addTable(){
        return doc.addTable();
    }
    public Table addTable(int colSize){
        return doc.addTable(colSize);
    }
    public Table addTable(float ...width){
        return doc.addTable(width);
    }

    public void save(String filepath) {
        doc.setDocEnd();
        Document document = doc.getDocument();
        doc2XML(document, tempPath + doc.path);
            
        long start = System.currentTimeMillis();
        scan(new File(tempPath));
        try {
            createDocx(folderName, filepath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            deleteFiles(new File(tempPath));
        }
        System.out.println("用时" + (System.currentTimeMillis() - start) + "ms");
    }

    /**
     * Document保存为 XML文件
     * @param doc Document对象
     * @param path 文件路径
     */
    void doc2XML(Document doc, String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                Util.creatNewFile(path);
            }
            Source source = new DOMSource(doc);
            Result result = new StreamResult(file);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void scan(File dir)//扫描文件夹
    {

        for (int i = 0; i < dir.list().length; i++) {
            File file = new File(dir.getAbsolutePath() + File.separator + dir.list()[i]);
            if (file.isDirectory())//是文件夹
            {
                //if(file.getName().indexOf("media")!=-1 && file.list().length>0)
                //	hasMedia=true;
                scan(file);
            }
            //else 
            files.add(file);
        }
    }

    /**
     * 打包文件，生成Docx
     * @param zipFilePath :文件夹名
     * @param fileName :文件全名
     * @return
     */
    private boolean createDocx(String folder, String fileName) {
        boolean flag = false;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            File zipFile = new File(fileName);
            if (zipFile.exists()) {
                zipFile.delete();
            }
            fos = new FileOutputStream(zipFile);
            zos = new ZipOutputStream(new BufferedOutputStream(fos));
            byte[] bufs = new byte[1024 * 10];
            for (int i = files.size() - 1; i >= 0; i--) {
                if (files.get(i).isFile()) {
                    //创建ZIP实体，并添加进压缩包 
                    String path = files.get(i).getAbsolutePath();
                    path = path.substring(path.indexOf(String.valueOf(folder)) + String.valueOf(folder).length() + 1);
                    ZipEntry zipEntry = new ZipEntry(path);
                    zos.putNextEntry(zipEntry);
                    //如果是文件，写进压缩包里  
                    fis = new FileInputStream(files.get(i));
                    bis = new BufferedInputStream(fis, 1024 * 10);
                    int read = 0;
                    while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
                        zos.write(bufs, 0, read);
                    }
                    fis.close();
                    bis.close();
                }
            }
            flag = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            //关闭流  
            try {
                if (null != bis) {
                    bis.close();
                }
                if (null != zos) {
                    zos.close();
                }
                if (null != fis) {
                    fis.close();
                }
                if (null != fos) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return flag;
    }

    /**
     * 删除临时文件
     */
    private void deleteFiles(File dir) {
        for (int i = 0; i < files.size(); i++) {
            files.get(i).delete();
            if (files.get(i).exists()) {
                files.get(i).delete();
                System.out.println("文件"+files.get(i).getAbsolutePath()+"删除失败");
            } else {
                System.out.println("文件" + files.get(i).getAbsolutePath() + "删除成功");
            }
        }
        dir.delete();
    }
}
