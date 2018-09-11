package com.whl.datafiles.web.createtable;


import com.whl.datafiles.config.DataXmlConfig;
import com.whl.datafiles.utils.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@EnableAutoConfiguration
@RequestMapping("/createtable")
public class CreateTableController {

    @Autowired
    private DataXmlConfig dataXmlConfig;

    @RequestMapping("/index.html")
    public String createTable(){
        return "createtable/index";
    }

    @RequestMapping("/addDataXml")
    @ResponseBody
    public Map<String,String> addXml(@Param("tableName")String tableName,@Param("valueKey")String valueKey,
                                     @Param("valueType")String valueType,@Param("valueLength")String valueLength){
        String[] valueKeys = valueKey.split(",");
        String[] valueTypes = valueType.split(",");
        String[] valueLengths = valueLength.split(",");
        List<Map<String,String>> params=new ArrayList<>();
        for (int i=0;i<valueKeys.length;i++) {
            Map<String,String> param=new HashMap<>();
            param.put("value_key",valueKeys[i]);
            param.put("value_type",valueTypes[i]);
            param.put("value_length",valueLengths[i]);
            params.add(param);
        }


        try {
            File file1 = new File(dataXmlConfig.getXmlpath());
            if(!file1.exists()){
                file1.mkdirs();
            }
            File file = new File(dataXmlConfig.getXmlpath()+File.separator+"database.xml");
            XmlUtil.DOMcreate(file,tableName,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
