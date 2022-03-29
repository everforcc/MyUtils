package cc.maven.excle.utils.writecolor;

import cc.core.file.img.dto.ImgColorDto;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

public class HSSFWriteColorUtils {

    public static void main(String[] args) throws Exception {
        //defined();
    }

    // 256限制

    /**
     * 1.
     * @param integerListMap
     * @throws Exception
     */
    public static void defined(Map<Integer,List<ImgColorDto>> integerListMap) throws Exception {
        // 创建一份
        HSSFWorkbook excel = new HSSFWorkbook();
        // 创建第一个sheet
        HSSFSheet sheet = excel.createSheet("color");
        // 生成单元格样式,要放循环内，要不下次可能会覆盖上次的值
        // HSSFCellStyle style = excel.createCellStyle();

        int tempX = -1;
        HSSFRow row = null;
        for(Integer intX: integerListMap.keySet()) {
            // 设置宽度
            sheet.setColumnWidth(intX,1 * 256);
            // 创建第x行
            row = sheet.createRow(intX.shortValue());
            // 设置行高
            row.setHeight((short) 100);

            for(ImgColorDto imgColorDto: integerListMap.get(intX)){
                //创建第y个单元格
                HSSFCell cell = row.createCell(imgColorDto.getY());
                //设置单元格的值
                cell.setCellValue("");

                // 生成单元格样式
                HSSFCellStyle style = excel.createCellStyle();
                HSSFColor lightGray =  setColor(excel,(byte) imgColorDto.getR(), (byte)imgColorDto.getG(),(byte) imgColorDto.getB());
                // 设置背景颜色
                style.setFillForegroundColor(lightGray.getIndex());
                //solid 填充  foreground  前景色
                style.setFillPattern(CellStyle.SOLID_FOREGROUND);
                cell.setCellStyle(style);
            }
        }

        // 写到磁盘
        FileOutputStream out = new FileOutputStream("C:\\test\\excle\\sj-01.xls");
        excel.write(out);
        out.close();
    }

    /**
     * 2. 设置颜色
     * @param workbook
     * @param r
     * @param g
     * @param b
     * @return
     */
    public static HSSFColor setColor(HSSFWorkbook workbook, byte r,byte g, byte b){

        //拿到palette颜色板
        HSSFPalette palette = workbook.getCustomPalette();
        HSSFColor hssfColor = null;
        try {
            hssfColor= palette.findColor(r, g, b);
            if (hssfColor == null ){
                /**
                 * 1. 这个是重点，具体的就是把之前的颜色 HSSFColor.LIME.index
                 * 2. 替换为  RGB(51,204,204) 宝石蓝这种颜色
                 * 3. 你可以改为 RGB(0,255,127)
                 */
                palette.setColorAtIndex(HSSFColor.LAVENDER.index, r, g,b);
                hssfColor = palette.getColor(HSSFColor.LAVENDER.index);
            }
        } catch (Exception e) {
        }
        return hssfColor;
    }

    /**
     * 3. 测试
     * excle中的颜色对应的RGB值
     */
    private void getExcleColorValueConstant(){
        System.out.println(IndexedColors.AQUA.getIndex());
    }

}
