package com.whl.datafiles.customize.daoImpl;

import com.whl.datafiles.customize.dao.FileInfoDao;
import com.whl.datafiles.customize.entity.User;
import com.whl.datafiles.domain.FileInfo;

import java.util.List;

/**
 * @Author: Wang HLiang
 * @Description: Create a new file
 * @Date: Created in 14:51 2018-08-01
 * @Modofied:
 */
public class FileInfoDaoImpl extends BaseDaoImpl<User> implements FileInfoDao {
    @Override
    public List<User> findAll() {
        return null;
    }
}
