package com.example.demo.service;

import com.example.demo.domain.Test;
import com.example.demo.repository.TestRepositoryInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by 王洪亮 on 2017/8/19.
 */
@Component
public class TestService {
    @Autowired
    private TestRepositoryInter testRepositoryInter;

    public List<Test> getAllTest(){
        return this.testRepositoryInter.findAll();
    }

    public Page<Test> pageTest(int pageNum,int pageSize){
        Pageable pageable=new PageRequest(pageNum-1,pageSize);
        Page<Test> page=this.testRepositoryInter.findAll(pageable);
        return page;
    }
}
