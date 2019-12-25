package com.dubbo.util;



import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

public class CellUnit {

    /**
     * 通过sheet 行号和列返回值
     *
     * @param sheet   sheet name
     * @param rowNum  行号
     * @param cellNum 列号
     * @return
     */
    public static String getCellValue(Sheet sheet, int rowNum, int cellNum) {
        Cell cell = sheet.getRow(rowNum).getCell(cellNum);
        String value = CellUnit.getCellValue(cell);
        return value;
    }


    //CellUnit.getCellValue() 方法封装如下：
    /**
     * 把不同类型的单元格转换成字符串，并返回
     *
     * @param cell cell
     * @return 当个单元格值
     */

    public static String getCellValue(Cell cell) {
        String value = "";
        switch (cell.getCellTypeEnum()) {
            case STRING:
                value = String.valueOf(cell.getRichStringCellValue());
                return value;
            case NUMERIC:
                value = String.valueOf(cell.getNumericCellValue());
                return value;
            case BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                return value;

            case FORMULA:
                value = String.valueOf(cell.getCellFormula());
                return value;

            case ERROR:
                value = String.valueOf(cell.getErrorCellValue());
                return value;
            case BLANK:
                return value;
            default:
                //log.warn("未知该单元格类型");
                return value;

        }
    }
}
