package org.wanholi.erw.excel;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.wanholi.erw.pub.Util;

import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class XXlsx {

    List<File> files = new ArrayList<File>();
    String folderName = String.valueOf(System.currentTimeMillis());
    String tempPath=new StringBuffer(System.getProperty("java.io.tmpdir")).append(folderName).append(File.separator).toString();

    public XXlsx() throws ParserConfigurationException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
        String clazzs[] = {"App", "ContentTypes", "Core", "Rels", "Styles", "Theme", "Workbook", "WorkbookX", "Sheet"};
        for (String className : clazzs) {
            Class class1 = Class.forName("org.wanholi.erw.excel." + className);
            Object object = class1.newInstance();
            Method method = class1.getDeclaredMethod("build");
            method.invoke(object);
            Field field = class1.getDeclaredField("path");
            String path = (String) field.get(object);
            Method method2 = class1.getSuperclass().getDeclaredMethod("getDocument");
            Document document = (Document) method2.invoke(object);
            doc2XML(document, tempPath + path);

        }

    }

    public void save(String filepath) {
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
     *
     * @param zipFilePath :保存路径
     * @param fileName :文件名
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
