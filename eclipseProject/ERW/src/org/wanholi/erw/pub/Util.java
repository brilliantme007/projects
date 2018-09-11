package org.wanholi.erw.pub;

import java.io.File;
import java.io.IOException;
/**
 * 工具类
 * 2017.11.10
 * @author pansoft
 * @version1.0
 */
public final class Util {

	public static void creatNewFile(String pathname) throws IOException{
		File file=new File(pathname);
		File parent=file.getParentFile();
		if(!parent.exists())
			parent.mkdirs();
		file.createNewFile();
	}
        public static String RGB2Hex(int r,int g,int b){
            return  new StringBuffer(0).append("#").append(Integer.toHexString(r).length()==1?"0"+Integer.toHexString(r):Integer.toHexString(r)).
                    append(Integer.toHexString(g).length()==1?"0"+Integer.toHexString(g):Integer.toHexString(g))
                    .append(Integer.toHexString(b).length()==1?"0"+Integer.toHexString(b):Integer.toHexString(b)).toString();
        }
}
