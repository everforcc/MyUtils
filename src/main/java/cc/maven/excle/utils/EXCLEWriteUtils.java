package cc.maven.excle.utils;

import cc.maven.excle.utils.dto.EXCLEDataDto;
import cc.maven.excle.utils.dto.EXCLEDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;
import java.util.Map;

/**
 * @author everforcc
 */
public class EXCLEWriteUtils {

    private String targetFileName = "";

    public void writeExcel(EXCLEDto excleDto){
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
                    sheet.setColumnWidth(eRow.getWidth(), 1 * 256);
                    // 行高
                    // row.setHeight((short) 100);
                    row.setHeight((short)eRow.getHeigh());

                    // 创建第y列
                    Cell cell = row.createCell(rowY);
                    rowY++;
                    // 设置单元格格式
                    cell.setCellType(excleDataDto.getDataType());
                    // 设置单元格值
                    cell.setCellValue(excleDataDto.getDataValue());
                    // 设置单元格颜色
                    // 配合这个 PicReadColor 可以读取图片写入excle
                    setColor(excel,excleDataDto.getR(),excleDataDto.getG(),excleDataDto.getB());

                }
            }

        }

    }

    public static XSSFCellStyle setColor(Workbook workbook, int r, int g, int b){

        XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();
        cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(r, g, b)));
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

        return cellStyle;
    }

}
