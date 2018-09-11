package com.whl.datafiles.service;

import com.whl.datafiles.domain.FileInfo;
import com.whl.datafiles.repository.FileInfoRepositoryInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/11/27.
 */
@Component
public class FileInfoService {

    @Autowired
    private FileInfoRepositoryInter fileInfoRepositoryInter;

    public Page<FileInfo> pageFilesInfo(int pageNum, int pageSize){
        Pageable pageable=new PageRequest(pageNum-1,pageSize);
        Page<FileInfo> page=this.fileInfoRepositoryInter.findAll(pageable);
        return page;
    }

    public void saveFileInfo(FileInfo fileInfo){
        this.fileInfoRepositoryInter.save(fileInfo);
    }

    public FileInfo getFileInfoById(String fid){
        return this.fileInfoRepositoryInter.findOne(fid!=null&&!fid.equals("")?Integer.valueOf(fid):0);
    }

    public void deleteFile(String fid){
        this.fileInfoRepositoryInter.delete(fid!=null&&!fid.equals("")?Integer.valueOf(fid):0);
    }
}
