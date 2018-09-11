package com.example.demo.service;

import com.example.demo.domain.Test;
import com.example.demo.domain.UUser;
import com.example.demo.repository.UUserRepositoryInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 王洪亮 on 2017/8/19.
 */
@Component
public class UserService {
    @Autowired
    private UUserRepositoryInter uuserRepositoryInter;

    public List<UUser> getAllUser(){
        return this.uuserRepositoryInter.findAll();
    }
}
