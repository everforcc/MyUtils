package cc.maven.excle.utils.writecolor;

import cc.advanced.concurrent.pool.ThreadPoolUtils;
import cc.core.date.utils.DateUtils;
import cc.core.file.img.dto.ImgColorDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

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

    private static ExecutorService executorService = ThreadPoolUtils.fixedThreadPool;

    /**
     * 2. 根据设置对象写入颜色
     * @param integerListMap
     * @param fileName
     * @throws Exception
     */
    public static void defined(Map<Integer, List<ImgColorDto>> integerListMap,String fileName) throws Exception {
        long before = DateUtils.time();
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
            //sheet.setColumnWidth(intX,1 * 256);
            sheet.setDefaultColumnWidth(1 * 1);
            // 创建第x行
            row = sheet.createRow(intX.shortValue());
            // 设置列高
            row.setHeight((short) 200);


            for(ImgColorDto imgColorDto: integerListMap.get(intX)){
//                long end = DateUtils.time();
//                // 每个大概需要20毫秒处理数据
//                System.out.println(imgColorDto + "耗时: " + DateUtils.timedif(before,end));
//                before = DateUtils.time();
//                // 创建第x个单元格
//                Cell cell = row.createCell(imgColorDto.getX());
//                // 设置单元格的值
//                cell.setCellValue("");
//                // 设置单元格颜色
//                XSSFCellStyle cellStyle = excel.createCellStyle();
//                cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(imgColorDto.getR(),imgColorDto.getG(),imgColorDto.getB())));
//                cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
//                cell.setCellStyle(cellStyle);

                executorService.execute(new DealY(row,imgColorDto,excel));
            }

        }
        executorService.shutdown();
        ThreadPoolUtils.isEnd(executorService);

        // 写入磁盘
        FileOutputStream out = new FileOutputStream(fileName);
        excel.write(out);
        out.close();
    }

    public static class DealY implements Runnable{

        private Row row;
        private ImgColorDto imgColorDto;
        private XSSFWorkbook excel;

        public DealY(Row row, ImgColorDto imgColorDto, XSSFWorkbook excel) {
            this.row = row;
            this.imgColorDto = imgColorDto;
            this.excel = excel;
        }

        @Override
        public void run() {
            long before = DateUtils.time();

            // 创建第x个单元格
            Cell cell = row.createCell(imgColorDto.getX());
            // 设置单元格的值
            cell.setCellValue("");
            // 设置单元格颜色
            XSSFCellStyle cellStyle = excel.createCellStyle();
            cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(imgColorDto.getR(),imgColorDto.getG(),imgColorDto.getB())));
            cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

            cell.setCellStyle(cellStyle);

            long end = DateUtils.time();
            // 每个大概需要20毫秒处理数据
            System.out.println(imgColorDto + "耗时: " + DateUtils.timedif(before,end));
        }
    }

}
