package com.whl.datafiles.customize.dao;

import com.whl.datafiles.customize.entity.User;
import com.whl.datafiles.domain.FileInfo;

import java.util.List;

/**
 * @Author: Wang HLiang
 * @Description: Create a new file
 * @Date: Created in 14:43 2018-08-01
 * @Modofied:
 */
public interface FileInfoDao extends BaseDao<User> {

    List<User> findAll();
}
