package cc.maven.excle.utils.writecolor;

import cc.core.file.img.dto.ImgColorDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author everforcc 2021-09-30
 */
public class XSSFWriteColorUtils {

    /**
     * 1. 示例，写入颜色
     * @throws Exception
     */
    public void poiColor() throws Exception {
        // Create a workbook object
        Workbook workbook = new XSSFWorkbook();
        // Create sheet
        Sheet sheet = workbook.createSheet();

        // Create a row and put some cells in it.
        Row row = sheet.createRow((short) 1);

        // Aqua background
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        Cell cell = row.createCell((short) 1);
        cell.setCellValue("X1");
        cell.setCellStyle(style);

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("C:\\test\\excle\\POIFillAndColorExample.xlsx");
        workbook.write(fileOut);
        fileOut.close();
    }

    /**
     * 2. 根据设置对象写入颜色
     * @param integerListMap
     * @param fileName
     * @throws Exception
     */
    public static void defined(Map<Integer, List<ImgColorDto>> integerListMap,String fileName) throws Exception {
        // 创建一份
        XSSFWorkbook excel = new XSSFWorkbook();
        // 创建第一个sheet
        Sheet sheet = excel.createSheet("color");
        // 生成单元格样式
        // CellStyle style = excel.createCellStyle();

        int tempX = -1;
        Row row = null;

        // 这块儿调整为为多线程， 太慢了 图片分辨率最大的边手动调整为100，分十个线程，一起跑
        for(Integer intX: integerListMap.keySet()) {
            // 设置列宽
            sheet.setColumnWidth(intX,1 * 256);
            // 创建第x行
            row = sheet.createRow(intX.shortValue());
            // 设置列高
            row.setHeight((short) 100);
            for(ImgColorDto imgColorDto: integerListMap.get(intX)){
                System.out.println(imgColorDto);
                // 创建第x个单元格
                Cell cell = row.createCell(imgColorDto.getX());
                // 设置单元格的值
                cell.setCellValue("");
                // 设置单元格颜色
                XSSFCellStyle cellStyle = excel.createCellStyle();
                cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(imgColorDto.getR(),imgColorDto.getG(),imgColorDto.getB())));
                cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
                cell.setCellStyle(cellStyle);
            }
        }

        // 写入磁盘
        FileOutputStream out = new FileOutputStream("C:\\test\\excle\\" + fileName);
        excel.write(out);
        out.close();
    }

}
