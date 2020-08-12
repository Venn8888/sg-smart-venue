package com.sg.framework.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

public class JxlUtil {
	/** 
     * 根据模板生成Excel文件. 
     * @param srcFilePath 模板文件. 
     * @param list 模板中存放的数据. 
     * @param destFilePath 生成的文件. 
     */
    public static String createExcel(String srcFilePath, Map<String,Object> params, String destFilePath){  
        XLSTransformer transformer = new XLSTransformer();  
        try {  
            //生成Excel文件  
            transformer.transformXLS(srcFilePath, params, destFilePath);
        } catch (ParsePropertyException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        return destFilePath;
    }
    public static String createExcel(InputStream is, Map<String,Object> params, String destFilePath){  
        XLSTransformer transformer = new XLSTransformer();  
        try {  
            //生成Excel文件  
            Workbook workbook = transformer.transformXLS(is, params);
            FileUtil.createFolder(destFilePath.substring(0, destFilePath.lastIndexOf("/")));
            FileOutputStream fos = new FileOutputStream(new File(destFilePath));
            workbook.write(fos);
        } catch (ParsePropertyException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        return destFilePath;
    } 
}
