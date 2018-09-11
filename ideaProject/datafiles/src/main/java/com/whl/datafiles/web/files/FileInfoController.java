package com.whl.datafiles.web.files;

import com.whl.datafiles.domain.Config;
import com.whl.datafiles.domain.FileInfo;
import com.whl.datafiles.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2017/11/27.
 */

@Controller
@EnableAutoConfiguration
public class FileInfoController {

    @Autowired
    private FileInfoService fileInfoService;

    @Autowired
    private Config config;

    @RequestMapping(value = "/filelist.html")
    public String hello(HttpServletRequest request){

        String pageNum = request.getParameter("pageNum");
        String pageSize=request.getParameter("pageSize");

        Page<FileInfo> pTest = this.fileInfoService.pageFilesInfo((pageNum != null && !pageNum.equals("") ? Integer.parseInt(pageNum) : 1), (pageSize != null && !pageSize.equals("") ? Integer.parseInt(pageSize) : 10));
        if(pTest.getContent()!=null){
            request.setAttribute("pTest",pTest.getContent());
            request.setAttribute("pageNum",(pageNum != null && !pageNum.equals("") ? Integer.parseInt(pageNum) : 1));
            request.setAttribute("pageSize",(pageSize != null && !pageSize.equals("") ? Integer.parseInt(pageSize) : 10));
            request.setAttribute("pageTotal",pTest.getTotalPages());
        }
        return "files/index";
    }

    @RequestMapping(value="/addfiles")
    public  String addFiles(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response,
                            @Param("filedir") String filedir,@Param("fdis") String fdis){
        if (filedir==null){
            filedir="";
        }
        if (!file.isEmpty()){
            try {
                FileInfo fileInfo=new FileInfo();
                fileInfo.setFname(file.getOriginalFilename());
                fileInfo.setFurl((filedir!=null&&!filedir.equals("")?("/"+filedir):"")+"/"+file.getOriginalFilename());
                fileInfo.setFdis(fdis!=null?fdis:"无");
                fileInfo.setFcreatetime(new Timestamp(System.currentTimeMillis()));
                if(file.getSize()<=config.getMaxsavesize()*1024*1024){
                    InputStream is = file.getInputStream();
                    byte[] tes=new byte[(int) file.getSize()];
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] b = new byte[2048];
                    int ch;
                    ch = is.read(b);
                    while (ch != -1) {
                        bos.write(b, 0, ch);
                        ch = is.read(b);
                    }
                    tes = bos.toByteArray();
                    bos.close();
                    is.close();
                    fileInfo.setFb(tes);
                }
                fileInfo.setEndstiff(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1));
                fileInfo.setUid(UUID.randomUUID().toString());


                final FileInfo tempFileInfo=fileInfo;
                fileInfoService.saveFileInfo(tempFileInfo);

                final String tempFiledir = filedir;
                final MultipartFile tempFile = file;
                new Thread(){
                    @Override
                    public void run() {
                        super.run();

                        File file1 = new File(config.getSavefileurl());
                        if (!file1.exists()){
                            file1.mkdirs();
                        }

                        File file2=new File(config.getSavefileurl()+"/"+tempFiledir);
                        if (!file2.exists()){
                            file2.mkdirs();
                        }
                        try{
                            BufferedOutputStream out = new BufferedOutputStream(
                                    new FileOutputStream(new File(config.getSavefileurl())+(tempFiledir!=null&&!tempFiledir.equals("")?("/"+tempFiledir):"")+"/"+tempFile.getOriginalFilename()));
                            out.write(tempFile.getBytes());
                            out.flush();
                            out.close();
                        }catch (Exception e){

                        }
                    }
                }.start();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "redirect:/filelist.html";
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/filelist.html";
            }
        }
        return "redirect:/filelist.html";
    }
    @RequestMapping(value="/filedirdemo")
    public String filedirdemo(){
        this.getDirectory(new File("files"));
        return "filedirdemo";
    }
    private void getDirectory(File file) {
        File flist[] = file.listFiles();
        if (flist == null || flist.length == 0) {
            return ;
        }
        for (File f : flist) {
            if (f.isDirectory()) {
                //这里将列出所有的文件夹
                System.out.println("Dir==>" + f.getAbsolutePath());
                getDirectory(f);
            } else {
                //这里将列出所有的文件
                System.out.println("file==>" + f.getAbsolutePath());
            }
        }
    }

    @RequestMapping(value="/downloadfiles")
    public  void downloadFiles(HttpServletRequest request, HttpServletResponse response,
                            @Param("fid") String fid) throws Exception {
                if(fid!=null&&!fid.equals("")){
                    FileInfo fileInfo = this.fileInfoService.getFileInfoById(fid);
                    if(fileInfo!=null){
                        String path=config.getSavefileurl()+fileInfo.getFurl();
                        File file = new File(path);
                        if(file.exists()){
                            System.out.println(1);
                            String filename = file.getName();
                            InputStream fis = new BufferedInputStream(new FileInputStream(path));
                            byte[] buffer = new byte[fis.available()];
                            fis.read(buffer);
                            fis.close();
                            response.reset();
                            // 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
                            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.replaceAll(" ", "").getBytes("gbk"),"iso8859-1"));
                            response.addHeader("Content-Length", "" + file.length());
                            OutputStream os = new BufferedOutputStream(response.getOutputStream());
                            response.setContentType("application/octet-stream");
                            os.write(buffer);// 输出文件
                            os.flush();
                            os.close();

                        }else{
                            System.out.println(2);
                            response.reset();
                            // 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
                            response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileInfo.getFname().replaceAll(" ", "").getBytes("gbk"),"iso8859-1"));
                            response.addHeader("Content-Length", "" + file.length());
                            OutputStream os = new BufferedOutputStream(response.getOutputStream());
                            response.setContentType("application/octet-stream");
                            os.write(fileInfo.getFb());// 输出文件
                            os.flush();
                            os.close();
                        }
                    }
                }
    }

    @RequestMapping(value="/deletefiles")
    public String deleteFile(HttpServletRequest request,HttpServletResponse response,@Param("fid")String  fid){
        if(fid!=null&&!fid.equals("")){
            FileInfo fileInfo = this.fileInfoService.getFileInfoById(fid);
            if(fileInfo!=null){
                String path=config.getSavefileurl()+fileInfo.getFurl();
                File file = new File(path);
                if(file.exists()){
                    this.deleteFile(file);
                }
                File fiel=new File(config.getSavefileurl()+File.separator+fid);
                if(fiel.exists()){
                    this.deleteFile(fiel);
                }
                this.fileInfoService.deleteFile(fid);
            }
        }
        return "redirect:/filelist.html";
    }
    public void deleteFile(File file) {
        if (file.exists()) {//判断文件是否存在
            if (file.isFile()) {//判断是否是文件
                file.delete();//删除文件
            } else if (file.isDirectory()) {//否则如果它是一个目录
                File[] files = file.listFiles();//声明目录下所有的文件 files[];
                for (int i = 0;i < files.length;i ++) {//遍历目录下所有的文件
                    this.deleteFile(files[i]);//把每个文件用这个方法进行迭代
                }
                file.delete();//删除文件夹
            }
        } else {
            System.out.println("所删除的文件不存在");
        }
    }
    @RequestMapping(value="/prefiles")
    public void preFile(HttpServletRequest request, HttpServletResponse response, @Param("fid")String  fid) throws IOException {
        if(fid!=null&&!fid.equals("")) {
            FileInfo fileInfo = this.fileInfoService.getFileInfoById(fid);
            byte[] fb = fileInfo.getFb();
            if(!(fb!=null&&fb.length>0)){
                String path=config.getSavefileurl()+fileInfo.getFurl();
                File file = new File(path);
                if(file.exists()){
                    InputStream fis = new BufferedInputStream(new FileInputStream(path));
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    fis.close();
                    this.writePic(response,buffer);
                }
            }else{
                this.writePic(response,fb);
            }

        }
    }

    public void writePic(HttpServletResponse response,byte[] fb) throws IOException {
        OutputStream os = response.getOutputStream();
        os.write(fb);
        os.flush();
        os.close();
    }


    @RequestMapping(value="/writTxt")
    public @ResponseBody String writeTxt(HttpServletRequest request,HttpServletResponse response, @Param("fid")String  fid) throws IOException {
        String result="";
        if(fid!=null&&!fid.equals(""))
        {
            FileInfo fileInfo = this.fileInfoService.getFileInfoById(fid);
            byte[] fb = fileInfo.getFb();
            if(fb!=null&&fb.length>0){
                result=new String(fb);
            }else{
                String path=config.getSavefileurl()+fileInfo.getFurl();
                File file = new File(path);
                if(file.exists()){
                    InputStream fis = new BufferedInputStream(new FileInputStream(path));
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    fis.close();
                    result=new String(buffer);
                }
            }
        }
        return result;
    }

    @RequestMapping(value="/writDoc")
    public @ResponseBody String writeDoc(HttpServletRequest request,HttpServletResponse response, @Param("fid")String  fid) throws Exception {
        String result="";
        if(fid!=null&&!fid.equals(""))
        {
            FileInfo fileInfo = this.fileInfoService.getFileInfoById(fid);
            String s = config.getSavefileurl() + fileInfo.getFurl();
        }
        return result;
    }

    @RequestMapping(value="/getpic")
    public void getpic(HttpServletRequest request,HttpServletResponse response, @Param("picname")String  picname,@Param("fid")String fid) throws Exception {
        String path=config.getSavefileurl()+"/"+fid+"/"+picname;
        File file = new File(path);
        if(file.exists()){
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            this.writePic(response,buffer);
        }
    }
}
