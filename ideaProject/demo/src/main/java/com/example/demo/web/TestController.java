package com.example.demo.web;

import com.example.demo.domain.Test;
import com.example.demo.service.TestService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 王洪亮 on 2017/8/19.
 */
@Controller
@EnableAutoConfiguration
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/hello")
    public String hello(HttpServletRequest request){
        List<Test> allTest = this.testService.getAllTest();
        request.setAttribute("username","KING");
        request.setAttribute("allTest",allTest);

        String pageNum = request.getParameter("pageNum");
        String pageSize=request.getParameter("pageSize");

        Page<Test> pTest = this.testService.pageTest((pageNum != null && !pageNum.equals("") ? Integer.parseInt(pageNum) : 1), (pageSize != null && !pageSize.equals("") ? Integer.parseInt(pageSize) : 1));
        if(pTest.getContent()!=null){
            request.setAttribute("pTest",pTest.getContent());
            request.setAttribute("pageNum",(pageNum != null && !pageNum.equals("") ? Integer.parseInt(pageNum) : 1));
            request.setAttribute("pageSize",(pageSize != null && !pageSize.equals("") ? Integer.parseInt(pageSize) : 1));
            request.setAttribute("pageTotal",pTest.getTotalPages());
        }
        return "index";
    }

    /**
     *
     * 测试导出excel合并单元格
     * @param request
     * @return
     */
    @RequestMapping(value = "/export")
    public void export(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Map<String,Object>> dataList=new ArrayList<>();

        for(int i=0;i<10;i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("列1", "name");
            map.put("列2", "age" + i);
            map.put("列3","shengao");
            dataList.add(map);
        }
        for(int i=0;i<10;i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("列1", "name1");
            map.put("列2", "age" + i);
            map.put("列3","shengao1");
            dataList.add(map);
        }
        //申明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个表格
        HSSFSheet sheet = workbook.createSheet();
        //设置列宽度
        sheet.setDefaultColumnWidth(18);
        //行
        //设置表头
        HSSFRow row = sheet.createRow(0);//第一行

        HSSFCell cell = row.createCell(0);//第一行的第一个单元格

        HSSFRichTextString text1 = new HSSFRichTextString("列1");

        cell.setCellValue(text1);

        HSSFCell cell1 = row.createCell(1);

        HSSFRichTextString text2 = new HSSFRichTextString("列2");
        cell1.setCellValue(text2);

        HSSFCell cell2 = row.createCell(2);
        HSSFRichTextString text3 = new HSSFRichTextString("列3");
        cell2.setCellValue(text3);

        //尝试合并单元格
        //遍历写入正文
        for(int i=0;i<dataList.size();i++){
            row=sheet.createRow(i+1);
            HSSFCell cel = row.createCell(0);
            cel.setCellValue(new HSSFRichTextString(dataList.get(i).get("列1").toString()));
            HSSFCell ce = row.createCell(1);
            ce.setCellValue(new HSSFRichTextString(dataList.get(i).get("列2").toString()));
            HSSFCell c = row.createCell(2);
            c.setCellValue(new HSSFRichTextString(dataList.get(i).get("列3").toString()));
        }
       // sheet.addMergedRegion(new CellRangeAddress(1,10,0,0));

        int rowNum = sheet.getPhysicalNumberOfRows();
        int cellNum = sheet.getRow(0).getPhysicalNumberOfCells();


        Map<String,Object> numMap=new HashMap<>();
        for (int i=0;i<rowNum-1;i++){

            String s1 = sheet.getRow(i + 1).getCell(0).getStringCellValue();
            for (int j=i+1;j<rowNum-1;j++) {
                String s2 = sheet.getRow(j + 1).getCell(0).getStringCellValue();
                if (s1.equals(s2)) {
                    if (numMap.get(s1) == null) {
                        Integer[] s = new Integer[2];
                        if (s != null) {
                            s[0] = i + 1;
                            s[1] = j + 1;
                        }
                        numMap.put(s1, s);
                    } else {
                        Integer[] s = (Integer[]) numMap.get(s1);
                        s[1] = j + 1;
                        numMap.put(s1, s);
                    }
                }
            }
            ;
        }
       if(numMap!=null){
           for (Object value : numMap.values()) {
               Integer[] v= (Integer[]) value;
               System.out.println("Value0 = " + v[0]);
               System.out.println("Value1 = " + v[1]);
               sheet.addMergedRegion(new CellRangeAddress(v[0],v[1],0,0));
               sheet.addMergedRegion(new CellRangeAddress(v[0],v[1],2,2));

           }
       }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=excl导出测试统计.xls");//默认Excel名称
        response.flushBuffer();
        workbook.write(response.getOutputStream());

    }

    /**
     * 测试上传文件(断点续传)
     */
    @RequestMapping(value="/uploadFile")
    public void uploadFile(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {

        String filePath = request.getParameter("filePath");//需要下载的文件路径
        String hadDownFileLength=request.getParameter("hadDownFileLength");
        long hadFileLong=0;
        long needFileLong=0;
        if(hadDownFileLength!=null&&!hadDownFileLength.equals("")){
            //下载新的文件,如果不是新的文件需要判断是否下载,下载完毕就不需要下载
            hadFileLong=Long.parseLong(hadDownFileLength);
        }
        File file=new File(filePath);
        if(file.exists()){
            String fileName = file.getName();
            System.out.println("文件存在"+fileName);
            needFileLong = file.length();//将要下载的文件的总大小
            InputStream inStream=new FileInputStream(filePath);
            response.reset();
            response.setContentType("bin");
            response.addHeader("Content-Disposition","attachment;filename=\""+fileName+"\"");
            byte[] b = new byte[100];
            int len;
            try {
                while ((len = inStream.read(b)) > 0)
                    response.getOutputStream().write(b, 0, len);
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("文件不存在");
        }




    }


    @RequestMapping(value="/pageTest")
    public void pageTest(HttpServletRequest request,HttpServletResponse response){

    }
}
