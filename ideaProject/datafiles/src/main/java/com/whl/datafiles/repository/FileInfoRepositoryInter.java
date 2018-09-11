package com.whl.datafiles.repository;

import com.whl.datafiles.domain.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FileInfoRepositoryInter extends JpaRepository<FileInfo, Integer>,JpaSpecificationExecutor<FileInfo> {

}