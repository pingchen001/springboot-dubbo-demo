package com.dubbo.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class WorkBookEhi {

    /**
     * 创建 workbook
     *
     * @param filePath excel文件路径
     * @return Workbook 对象
     * @throws IOException
     */
    public static Workbook getWorkbook(String filePath) {
        Workbook wb = null;
        try {
            if (filePath.endsWith(".xls")) {
                File file = new File(filePath);
                InputStream is = new FileInputStream(file);
                wb = new HSSFWorkbook(is);
            } else if (filePath.endsWith(".xlsx") || filePath.endsWith(".xlsm")) {
                wb = new XSSFWorkbook(filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

}
