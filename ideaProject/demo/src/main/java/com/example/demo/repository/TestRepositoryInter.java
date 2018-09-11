package com.example.demo.repository;

import com.example.demo.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by 王洪亮 on 2017/8/19.
 */
public interface TestRepositoryInter extends JpaRepository<Test, Integer> ,JpaSpecificationExecutor<Test>{

}
