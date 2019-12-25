package com.dubbo.util;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.HashMap;

public class ReadExcel {

    /**
     * @param file 读取某个excel文件
     * @return Object
     */
    public Object[][] testData(String file) {
        ArrayList<String> arrkey = new ArrayList<String>();
        Workbook workbook = WorkBookEhi.getWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
//        获取总行数
        int rowTotalNum = sheet.getLastRowNum()+1;
//        总列数
        int columns = sheet.getRow(0).getPhysicalNumberOfCells();

        HashMap<String, String>[][] map = new HashMap[rowTotalNum - 1][1];
        // 对数组中所有元素hashmap进行初始化
        if (rowTotalNum > 1) {
            for (int i = 0; i < rowTotalNum - 1; i++) {
                map[i][0] = new HashMap();
            }
        } else {
            System.out.println("测试的Excel" + file + "中没有数据");
        }
        // 获得首行的列名，作为hashmap的key值
        for (int c = 0; c < columns; c++) {
            String cellvalue = CellUnit.getCellValue(sheet, 0, c);
            arrkey.add(cellvalue);
        }
        // 遍历所有的单元格的值添加到hashmap中
        for (int r = 1; r < rowTotalNum; r++) {
            for (int c = 0; c < columns; c++) {
                String cellvalue = CellUnit.getCellValue(sheet, r, c);
                map[r - 1][0].put(arrkey.get(c), cellvalue);
            }
        }
        return map;

    }
}
