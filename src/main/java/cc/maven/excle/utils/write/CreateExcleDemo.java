package cc.maven.excle.utils.write;

import cc.sysconstant.ConstantFile;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Yukino
 * 2020/6/18
 */
public class CreateExcleDemo {

    /**
     * 基本流程
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //createExcleFlow();
    }

    /**
     * 1. 创建excle写入测试
     * @throws IOException
     */
    public static void createExcleFlow() throws IOException {
        //创建工作簿 类似于创建Excel文件
        XSSFWorkbook workbook=new XSSFWorkbook();
        //创建 sheetname页名
        XSSFSheet sheet = workbook.createSheet("员工信息");
        sheet.setColumnWidth(3,20*256);//给第3列设置为20个字的宽度
        sheet.setColumnWidth(4,20*256);//给第4列设置为20个字的宽度
        //创建一行,下标从0开始
        XSSFRow row = sheet.createRow(0);
        //创建这行中的列,下标从0开始 （表头）
        XSSFCell cell = row.createCell(0);
        // 给cell 0下表赋值
        cell.setCellValue("姓名");
        //创建这行中的列,并给该列直接赋值
        row.createCell(1).setCellValue("年龄");
        row.createCell(2).setCellValue("性别");
        row.createCell(3).setCellValue("生日");
        row.createCell(4).setCellValue("手机号");
        // 设置表里内容
        row = sheet.createRow(1);
        row.createCell(0).setCellValue("T");
        row.createCell(1).setCellValue("保密");
        row.createCell(2).setCellValue("男");
        row.createCell(3).setCellValue("保密");
        row.createCell(4).setCellValue("12121212121");

        row = sheet.createRow(2);
        row.createCell(0).setCellValue("T");
        row.createCell(1).setCellValue("18");
        row.createCell(2).setCellValue("女");
        row.createCell(3).setCellValue("2000-01-01");
        row.createCell(4).setCellValue("12121212122");
        //设定 路径
        File file = new File(ConstantFile.L1_javaFilePath + ConstantFile.L2_excle + "/员工信息.xlsx");
        FileOutputStream stream = new FileOutputStream(file);
        // 需要抛异常
        workbook.write(stream);
        //关流
        stream.close();
    }

    /**
     * 2. 示例2 向Excel中写数据
     * @param list
     * @param filePath
     */
    public static void writeExcel(List<Object> list , String filePath){
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
