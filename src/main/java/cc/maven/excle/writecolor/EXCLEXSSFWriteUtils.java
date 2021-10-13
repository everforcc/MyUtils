package cc.maven.excle.writecolor;

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
public class EXCLEXSSFWriteUtils {

    public static void defined(Map<Integer, List<ImgColorDto>> integerListMap,String fileName) throws Exception {
        //创建一份
        XSSFWorkbook excel = new XSSFWorkbook();
        //创建第一个sheet
        Sheet sheet = excel.createSheet("color");
        //生成单元格样式
        CellStyle style = excel.createCellStyle();

        //FileOutputStream out = new FileOutputStream("C:\\test\\excle3\\lc.xls",true);

        //FileOutputStream out = null;
        int tempX = -1;
        Row row = null;

        // 这块儿调整为为多线程， 太慢了 图片分辨率最大的边手动调整为100，分十个线程，一起跑
        for(Integer intX: integerListMap.keySet()) {
            sheet.setColumnWidth(intX,1 * 256);
            //创建第一行
            //HSSFRow row = sheet.createRow((short) 0);
            row = sheet.createRow(intX.shortValue());
            row.setHeight((short) 100);
            for(ImgColorDto imgColorDto: integerListMap.get(intX)){
                System.out.println(imgColorDto);
                //创建第一个单元格
                //HSSFCell cell = row.createCell((short) 0);
                Cell cell = row.createCell(imgColorDto.getX());
                //设置单元格的值
                cell.setCellValue("");

                //设置背景颜色
                //style.setFillForegroundColor(HSSFColor.LIME.index);
                //solid 填充  foreground  前景色
                //style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

                //HSSFColor lightGray =  setColor(excel,(byte) imgColorDto.getR(), (byte)imgColorDto.getG(),(byte) imgColorDto.getB());
                XSSFCellStyle cellStyle = excel.createCellStyle();
                cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(imgColorDto.getR(),imgColorDto.getG(),imgColorDto.getB())));
                cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
                cell.setCellStyle(cellStyle);

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
        FileOutputStream out = new FileOutputStream("C:\\test\\excle\\" + fileName);
        excel.write(out);
        out.close();

    }

    public static void definedThread(Map<Integer, List<ImgColorDto>> integerListMap,String fileName) throws Exception {
        //创建一份
        XSSFWorkbook excel = new XSSFWorkbook();
        //创建第一个sheet
        Sheet sheet = excel.createSheet("color");
        //生成单元格样式
        CellStyle style = excel.createCellStyle();

        //FileOutputStream out = new FileOutputStream("C:\\test\\excle3\\lc.xls",true);

        //FileOutputStream out = null;
        int tempX = -1;
        Row row = null;

        // 这块儿调整为为多线程， 太慢了 图片分辨率最大的边手动调整为100，分十个线程，一起跑
        for(Integer intX: integerListMap.keySet()) {
            sheet.setColumnWidth(intX,1 * 256);
            //创建第一行
            //HSSFRow row = sheet.createRow((short) 0);
            row = sheet.createRow(intX.shortValue());
            row.setHeight((short) 100);
            for(ImgColorDto imgColorDto: integerListMap.get(intX)){
                System.out.println(imgColorDto);
                //创建第一个单元格
                //HSSFCell cell = row.createCell((short) 0);
                Cell cell = row.createCell(imgColorDto.getX());
                //设置单元格的值
                cell.setCellValue("");

                //设置背景颜色
                //style.setFillForegroundColor(HSSFColor.LIME.index);
                //solid 填充  foreground  前景色
                //style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

                //HSSFColor lightGray =  setColor(excel,(byte) imgColorDto.getR(), (byte)imgColorDto.getG(),(byte) imgColorDto.getB());
                XSSFCellStyle cellStyle = excel.createCellStyle();
                cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(imgColorDto.getR(),imgColorDto.getG(),imgColorDto.getB())));
                cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
                cell.setCellStyle(cellStyle);

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
        FileOutputStream out = new FileOutputStream("C:\\test\\excle\\" + fileName);
        excel.write(out);
        out.close();

    }

    public static XSSFCellStyle setColor(XSSFWorkbook workbook, byte r,byte g, byte b){

        XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();
        cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(r, g, b)));
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

        return cellStyle;
    }

}
