package cc.maven.excle.writecolor;

import cc.core.file.img.ImgColorDto;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author everforcc 2021-09-30
 */
public class EXCLEHSSFWriteUtils {

    public static void main(String[] args) throws Exception {
        //defined();
    }

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
        FileOutputStream fileOut = new FileOutputStream(
                "C:\\test\\excle\\POIFillAndColorExample.xlsx");
        workbook.write(fileOut);
        fileOut.close();
    }

    // 256限制
    public static void defined(Map<Integer,List<ImgColorDto>> integerListMap) throws Exception {
        //创建一份
        HSSFWorkbook excel = new HSSFWorkbook();
        //创建第一个sheet
        HSSFSheet sheet = excel.createSheet("color");
        //生成单元格样式
        HSSFCellStyle style = excel.createCellStyle();

        //FileOutputStream out = new FileOutputStream("C:\\test\\excle3\\lc.xls",true);

        //FileOutputStream out = null;
        int tempX = -1;
        HSSFRow row = null;
        for(Integer intX: integerListMap.keySet()) {
            sheet.setColumnWidth(intX,1 * 256);
            //创建第一行
            //HSSFRow row = sheet.createRow((short) 0);
            row = sheet.createRow(intX.shortValue());
            row.setHeight((short) 100);
            for(ImgColorDto imgColorDto: integerListMap.get(intX)){

                //创建第一个单元格
                //HSSFCell cell = row.createCell((short) 0);
                HSSFCell cell = row.createCell(imgColorDto.getY());
                //设置单元格的值
                cell.setCellValue("");

                //设置背景颜色
                //style.setFillForegroundColor(HSSFColor.LIME.index);
                //solid 填充  foreground  前景色
                //style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

                HSSFColor lightGray =  setColor(excel,(byte) imgColorDto.getR(), (byte)imgColorDto.getG(),(byte) imgColorDto.getB());
                style.setFillForegroundColor(lightGray.getIndex());
                style.setFillPattern(CellStyle.SOLID_FOREGROUND);

                cell.setCellStyle(style);

                //通过流写到硬盘
                /*FileOutputStream out = new FileOutputStream("C:\\test\\excle\\lc-2.xls");
                excel.write(out);
                out.close();*/

                //======  这里是重点，马上要自定义单元格的样式了  =============
                //cell.setCellValue("");
                //拿到palette颜色板
//                HSSFPalette palette = excel.getCustomPalette();
//
//                //这个是重点，具体的就是把之前的颜色 HSSFColor.LIME.index
//                //替换为  RGB(51,204,204) 宝石蓝这种颜色
//                //你可以改为 RGB(0,255,127)
//                //palette.setColorAtIndex(HSSFColor.LIME.index, (byte) 220, (byte) 20, (byte) 60);
//                palette.setColorAtIndex(HSSFColor.LIME.index, (byte) imgColorDto.getR(), (byte) imgColorDto.getG(), (byte) imgColorDto.getB());



                //======  这里是重点，马上要自定义单元格的样式了  ============= " + imgColorDto.getX() + imgColorDto.getY() + "
                /*FileOutputStream out = new FileOutputStream("C:\\test\\excle3\\lc.xls",true);
                excel.write(out);
                out.flush();
                out.close();*/
            }
            /*FileOutputStream out = new FileOutputStream("C:\\test\\excle3\\lc.xls",true);
            excel.write(out);
            out.flush();
            out.close();*/
        }




        //======  这里是重点，马上要自定义单元格的样式了  =============
        FileOutputStream out = new FileOutputStream("C:\\test\\excle\\sj-01.xls");
        excel.write(out);
        out.close();

    }

    public static HSSFColor setColor(HSSFWorkbook workbook, byte r,byte g, byte b){
        HSSFPalette palette = workbook.getCustomPalette();
        HSSFColor hssfColor = null;
        try {
            hssfColor= palette.findColor(r, g, b);
            if (hssfColor == null ){
                palette.setColorAtIndex(HSSFColor.LAVENDER.index, r, g,b);
                hssfColor = palette.getColor(HSSFColor.LAVENDER.index);
            }
        } catch (Exception e) {
        }
        return hssfColor;
    }

    //向Excel中写数据
    public static void writeExcel(List<Object> list ,String filePath){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("student");
        XSSFRow firstRow = sheet.createRow(0);//第一行表头
        XSSFCell cells[] = new XSSFCell[3];

        String[] titles = new String[]{"age","name","address"};
        //循环设置表头信息
        for (int i=0;i<3;i++){
            cells[0]=firstRow.createCell(i);
            cells[0].setCellValue(titles[i]);
        }

        //遍历list,将数据写入Excel中
        for (int i=0;i<list.size();i++){
            XSSFRow row = sheet.createRow(i+1);
            XSSFCell cell = row.createCell(0); //第一列
            cell.setCellValue("");
            cell=row.createCell(1); //第二列
            cell.setCellValue("");
            cell=row.createCell(2); //第三列
            cell.setCellValue("");
        }
        OutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
            workbook.write(out);
            out.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
