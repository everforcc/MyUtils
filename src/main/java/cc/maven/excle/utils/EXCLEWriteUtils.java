package cc.maven.excle.utils;

import cc.core.file.img.ColorRGB;
import cc.maven.excle.utils.dto.EXCLEDataDto;
import cc.maven.excle.utils.dto.EXCLEDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author everforcc
 */
public class EXCLEWriteUtils {

    private static String targetFileName = "C:\\test\\excle\\eSheet-6.xls";

    // 测试数据
    public static void main(String[] args) {

        // excle
        EXCLEDto excleDto = new EXCLEDto();

        // sheet页集合
        Map<String, EXCLEDto.ESheet> stringESheetMap = new HashMap<>();

        // sheet页
        EXCLEDto.ESheet eSheet = new EXCLEDto.ESheet();

        // 单元格列
        Map<Integer,List<EXCLEDto.ERow>> integerListMapX = new HashMap<>();

        // 单元格行
        List<EXCLEDto.ERow> eRowList = new ArrayList<>();

        // 单元格值
        eRowList.add(new EXCLEDto.ERow(new EXCLEDataDto("value1", ColorRGB.stringColorRGBAry.get("苍白的紫罗兰红色"))));
        eRowList.add(new EXCLEDto.ERow(new EXCLEDataDto("value2", ColorRGB.stringColorRGBAry.get("蓟"))));
        eRowList.add(new EXCLEDto.ERow(new EXCLEDataDto("value3", ColorRGB.stringColorRGBAry.get("李子"))));

        integerListMapX.put(0,eRowList);
        integerListMapX.put(4,eRowList);

        eSheet.setIntegerListMapX(integerListMapX);

        stringESheetMap.put("eSheet-1",eSheet);
        stringESheetMap.put("eSheet-2",eSheet);
        excleDto.setStringESheetMap(stringESheetMap);

        try {
            writeExcel(excleDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeExcel(EXCLEDto excleDto) throws Exception {
        //创建一份
        Workbook excel = new XSSFWorkbook();
        Row row = null;

        Map<String, EXCLEDto.ESheet> stringESheetMap = excleDto.getStringESheetMap();

        //List<EXCLEDto.ESheet> sheetList = excleDto.getSheetList();
        for(Map.Entry<String, EXCLEDto.ESheet> stringESheetEntry: stringESheetMap.entrySet()){
            //创建第一个sheet
            Sheet sheet = excel.createSheet(stringESheetEntry.getKey());

            EXCLEDto.ESheet eSheet = stringESheetEntry.getValue();

            Map<Integer,List<EXCLEDto.ERow>> integerListMap = eSheet.getIntegerListMapX();

            for(Map.Entry<Integer,List<EXCLEDto.ERow>> integerListEntry: integerListMap.entrySet()) {

                //创建第x行
                row = sheet.createRow(integerListEntry.getKey());

                List<EXCLEDto.ERow> rowList = integerListEntry.getValue();
                int rowY = 0;
                for(EXCLEDto.ERow eRow: rowList) {
                    EXCLEDataDto excleDataDto = eRow.getExcleDataDto();
                    // 第n列的宽
                    if(eRow.getWidth()!=0) {
                        sheet.setColumnWidth(eRow.getWidth(), 1 * 256);
                    }
                    // 行高
                    // row.setHeight((short) 100);
                    if(eRow.getHeigh()!=0) {
                        row.setHeight((short) eRow.getHeigh());
                    }

                    // 创建第y列
                    Cell cell = row.createCell(rowY);
                    rowY++;
                    // 设置单元格格式
                    cell.setCellType(excleDataDto.getDataType());
                    // 设置单元格值
                    cell.setCellValue(excleDataDto.getDataValue());
                    // 设置单元格颜色
                    // 配合这个 PicReadColor 可以读取图片写入excle
                    if(excleDataDto.getR()!=0&&excleDataDto.getG()!=0&&excleDataDto.getB()!=0) {
                        setColor(excel, excleDataDto.getR(), excleDataDto.getG(), excleDataDto.getB(),cell);
                    }
                }
            }

        }

        FileOutputStream out = new FileOutputStream(targetFileName);
        excel.write(out);
        out.flush();
        out.close();
    }

    public static void setColor(Workbook workbook, int r, int g, int b,Cell cell){

        XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();
        cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(r, g, b)));
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cell.setCellStyle(cellStyle);
        //return cellStyle;
    }

}
