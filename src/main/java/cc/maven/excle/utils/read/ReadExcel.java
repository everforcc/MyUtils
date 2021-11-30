package cc.maven.excle.utils.read;

import cc.constant.ConstantFile;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * Yukino
 * 2020/4/23
 */
public class ReadExcel {

    /**
     * 1. 标准的读取格式，需要按照业务调整
     * 2. 多了个插入sql的过程
     */

    //
    private final String filePath= ConstantFile.javaFilePath + "";

    // 例如 P,6  53
    // Map<String,String> map = new HashMap<>();
    // 行列 A-Z,AA-AZ    1-10 10-100
    // String value[][];

    /**
     * 1.根据路径获取文件
     */
    public void flow(String fileName){

        //1. 获取指定路径的文件信息
        System.out.println("文件名:"+filePath+fileName);
        //获取文件类型
        int fileType = checkFileType(fileName);
        //2. 获取输入流
        FileInputStream fileInputStream = getFileStream(fileName);

        //3. 读取excle 输出流生成workbook
        Workbook wb = getWorkbook(fileType,fileInputStream);

        //4. 根据workbook读取信息
        // 需要根据实际业务给对象赋值
        loadFile(wb);
    }

    /**
     * 1. 判断文件类型 是否为excle
     * @param name
     * @return
     */
    public int checkFileType(String name){
        if(name==null||"".equals(name)){
            try {
                throw new Exception("文件名不能为空");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(isExcel2003(name)){
            return 2003;
        }

        if(isExcel2007(name)){
            return 2007;
        }
        return 0;

    }

    /**
     * 1.1
     * @param filePath
     * @return @描述：是否是2003的excel，返回true是2003
     */
    public static boolean isExcel2003(String filePath)  {
        // .（点号）也是一个正则表达式，它匹配任何一个字符如："a" 或 "1"。
        //  (?i) 表示所在位置右侧的表达式开启忽略大小写模式
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * 1.2
     * @param filePath
     * @return @描述：是否是2007的excel，返回true是2007
     */
    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    /**
     * 2. 读取文件
     * @param fileName
     * @return
     */
    public FileInputStream getFileStream(String fileName){
        File file = new File(filePath+fileName);
        //获取输入流
        FileInputStream fileInputStream = null;
        try {
            fileInputStream=new FileInputStream(file);
            //fileInputStream.
        } catch (FileNotFoundException e) {
            System.out.println("获取file流出错");
            e.printStackTrace();
        }
        return fileInputStream;
    }

    /**
     * 3. 读取excle 输出流生成workbook
     * @param fileType
     * @param inputStream
     * @return
     */
    public Workbook getWorkbook(int fileType,InputStream inputStream){
        Workbook wb = null;
        switch (fileType){
            case 2003:
                try {
                    wb = new HSSFWorkbook(inputStream);
                    System.out.println("2003");
                } catch (IOException e) {
                    System.out.println("生成2003work出错");
                    e.printStackTrace();
                }
                break;
            case 2007:
                try {
                    wb = new XSSFWorkbook(inputStream);
                    System.out.println("2007");
                } catch (IOException e) {
                    System.out.println("生成2007work出错");
                    e.printStackTrace();
                }
                break;
            case 0:
                try {
                    throw new Exception("文件类型未知");
                } catch (Exception e) {
                    System.out.println("未知文件类型catch");
                    e.printStackTrace();
                }
                break;
        }
        return wb;
    }

    /**
     * 4. 根据workbook读取信息
     * @param wb
     */
    public void loadFile(Workbook wb){
        //获取sheet页的数量
        int sheetNum = wb.getNumberOfSheets();
        //存行数
        int sheetrows[] = new int[sheetNum];


        //System.out.println("一共有"+sheetNum+"给个sheet页");

        //先操作第一个sheet页

        //行数
        Sheet sheet_1 = wb.getSheetAt(0); //第一个sheet页
        sheetrows[0] = sheet_1.getPhysicalNumberOfRows();

        //存列数
        int sheetcells[][] = new int[sheetNum][sheetrows[0]];

        //System.out.println("第1个sheet页有"+sheetrows[0]+"行");
        //System.out.println("sheet_1.getRow(0):"+sheet_1.getRow(1)); //不知道什么意思

        if(sheetrows[0]>1&&sheet_1.getRow(0)!=null){
            //都 一 个sheet页的第 一 行
            sheetcells[0][0] =  sheet_1.getRow(0).getPhysicalNumberOfCells();
            //System.out.println("总列数:"+sheetcells[0][0]);
        }

        StringBuilder stringBuilder[] = new StringBuilder[sheetrows[0]];

        String insertsql[] = new String[sheetrows[0]];
        //开始写行列逻辑
        for(int r=0; r<sheetrows[0]; r++){
            stringBuilder[r] = new StringBuilder().append("insert into ADIT_BUDGET (ITEMCODE, ITEMNAME, MONTH, BJ000AD, BJGM0, BJGM0TJ, BJGM0DL, BJMK0, BJMK1, BJMK1TJ, BJMK2, BJBS0, BJBS1, BJBS202, BJCL010, BJCR0, BJAC0, BJAD0, BJAD0HO, BJNJ0, GD000AD, GDGM0, GDDG1, GDMK1, GDMK2, GDMK3, GDAD0, GDBS1, GDMP0, GDAC0, GDAD0HO, GDMK0, GDRC0, GDCL010, GDNJ0, SZ000AD, SZMK0, SZAD0, JS000AD, JSGM0, JSMK0, JSBS1, JSBS2, JSAD0, JSAC0, JSAD0HO, JSCL010, JSNJ0, SU000AD, SUMK0, SUAC0, SD000AD, SDGM0, SDMK1, SDMK1A0, SDMK1B0, SDMK1C0, SDMK1D0, SDMK1E0, SDAD0, SDUW0, SDAC0, SH000AD, SHBD0, SHPD0, SHSA001, SHCL0, SHCL002, SHUW0, SHUWAI0, SHRI0, SHCP0, SHMP0, SHMPAI0, SHCR0, SHBCP0, SHIA0, SHAT0, SHAC0, SHAM0, SHHR0, SHAD0, SH000BU, SHIT0, SHGM0, SHDG219, SHDG504, SHDG508, SHDG1, SHDG2, SHCF0, SHDG5, SHCH0, SHMPI0) values (");
            Row row = sheet_1.getRow(r);
            if(row==null){
                continue;
            }
            // ？从 01 开始原因
            for(int c=0; c<sheetcells[0][0] ; c++){
                   Cell cell = row.getCell(c);
                   if(cell != null){
                       //如果是数字
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            //System.out.print(", '"+Math.round(cell.getNumericCellValue())+"'");
                            stringBuilder[r].append(", '"+Math.round(cell.getNumericCellValue())+"'");
                        }else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                            //System.out.print(", '"+cell.getStringCellValue()+"'");
                            stringBuilder[r].append(", '"+cell.getStringCellValue()+"'");
                        }else if(cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
                            //System.out.print(", ''");
                            stringBuilder[r].append(", ''");
                        }else if(cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN){
                            //Sstem.out.println("错误");
                        }else if(cell.getCellType() == HSSFCell.CELL_TYPE_ERROR){
                            //System.out.println("错误");
                        }else if(cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
                            //System.out.println("错误");
                        }else {
                            //System.out.println("错误");
                        }

                   }
            }
            stringBuilder[r].append(");");
            insertsql[r]=stringBuilder[r].toString().replaceAll("\\(,","(");
            System.out.println();
        }

        for(String s:insertsql){
            System.out.println(s);
        }

    }

    // 读取指定的行列 坐标和值

}
