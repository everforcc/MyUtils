package cc.maven.excle.use.costmcs;

import cc.constant.ConstantFile;
import cc.core.io.base.PrintWriterUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Yukino
 * 2020/4/26
 */
public class CostmcsBUDGET01 {
    //用来生成费控预算sql

    // excle路径
    String filePath= ConstantFile.L1_javaFilePath + "/20200427预算导入";
    //String fileList[]={"2020年AD&IT预算（不含滴滴）---导费控.xlsx","2020年HR预算---导费控.xlsx"};
    //表名
    //String tableName[] = {"ADIT_BUDGET",""};
    //String insertBegin[] = {" insert into ADIT_BUDGET  values  ",""};

    // 换成map       key excle名 value tablename
    Map<String, String> map = new HashMap<String, String>()
    {{
        //put("2020年AD&IT预算（不含滴滴）---导费控.xlsx", "ADIT_BUDGET");
       put("HR.xlsx", "HR_BUDGET");
    }};



    //@Test
    public void flow1(){
        for(Map.Entry<String,String> entry:map.entrySet() ){
            String fileName  = entry.getKey();
            //1.获取指定路径的文件信息
            println("文件名:" + filePath + fileName);
            //获取文件类型
            int fileType = checkFileType(fileName);
            //获取输入流
            FileInputStream fileInputStream = getFileStream(fileName);

            //2.读取excle 输出流生成workbook
            Workbook wb = getWorkbook(fileType, fileInputStream);

            //3.根据workbook读取信息
            loadFile(wb,entry.getValue());
        }
    }

    /**
     * 控制输出都用这个测试 成功后直接屏蔽掉
     * @param str
     */
    public void println(String str){ System.out.println(str); }
    public void println(){ System.out.println(); }
    public void print(String str){ System.out.print(str); }
    //public void print(){ System.out.print(); }


    public Workbook getWorkbook(int fileType,InputStream inputStream){
        Workbook wb = null;
        switch (fileType){
            case 2003:
                try {
                    wb = new HSSFWorkbook(inputStream);
                    println("2003");
                } catch (IOException e) {
                    println("生成2003work出错");
                    e.printStackTrace();
                }
                break;
            case 2007:
                try {
                    wb = new XSSFWorkbook(inputStream);
                    println("2007");
                } catch (IOException e) {
                    println("生成2007work出错");
                    e.printStackTrace();
                }
                break;
            case 0:
                try {
                    throw new Exception("文件类型未知");
                } catch (Exception e) {
                    println("未知文件类型catch");
                    e.printStackTrace();
                }
                break;
        }
        return wb;
    }


    //判断文件类型 是否为excle
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

    public FileInputStream getFileStream(String fileName){
        File file = new File(filePath+fileName);
        //获取输入流
        FileInputStream fileInputStream = null;
        try {
            fileInputStream=new FileInputStream(file);
            //fileInputStream.
        } catch (FileNotFoundException e) {
            println("获取file流出错");
            e.printStackTrace();
        }
        return fileInputStream;
    }

    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath)  {
        // .（点号）也是一个正则表达式，它匹配任何一个字符如："a" 或 "1"。
        //  (?i) 表示所在位置右侧的表达式开启忽略大小写模式
        return filePath.matches("^.+\\.(?i)(xls)$");
    }
    //@描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    public void loadFile( Workbook wb , String tableName ){
        // 第一个sheet
        int useSheetNum = 0;
        // 第一行
        int useSheetRowsNum = 0;

        //获取sheet页的数量
        int sheetNum = wb.getNumberOfSheets();
        // sheet行数的数组
        int sheetrows[] = new int[sheetNum];


        //System.out.println("一共有"+sheetNum+"给个sheet页");

        //先操作第一个sheet页

        //行数
        Sheet sheet_1 = wb.getSheetAt(useSheetNum); //第一个sheet页
        // 存入第一个sheet 页的行数
        sheetrows[useSheetRowsNum] = sheet_1.getPhysicalNumberOfRows();

        //存列数
        int sheetcells[][] = new int[sheetNum][sheetrows[useSheetNum]];

        if(sheetrows[useSheetRowsNum]>1&&sheet_1.getRow(useSheetRowsNum)!=null){
            //都 一 个sheet页的第 一 行
            sheetcells[useSheetNum][useSheetRowsNum] =  sheet_1.getRow(useSheetRowsNum).getPhysicalNumberOfCells();
        }

        StringBuilder stringBuilder[] = new StringBuilder[sheetrows[useSheetNum]];
        String insertsql[] = new String[sheetrows[useSheetNum]];
        //开始写行列逻辑
        for(int r=0; r<sheetrows[useSheetRowsNum]; r++){ // 每列从上倒下
            stringBuilder[r] = new StringBuilder().append("insert into " + tableName + " values (");
            Row row = sheet_1.getRow(r);
            if(row==null){
                continue;
            }
            // 第一个sheet 页 的第一行
            for(int c=0; c<sheetcells[0][0] ; c++){// 每一行 从左到有
                Cell cell = row.getCell(c);
                if(cell != null){
                    //如果是数字
                    if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                        stringBuilder[r].append(", '"+Math.round(cell.getNumericCellValue())+"'");
                    }else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                        // 去除所有空格
                        stringBuilder[r].append(", '"+cell.getStringCellValue().replaceAll(" ","")+"'");
                    }else if(cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
                        stringBuilder[r].append(", '0'");
                        println("空白:"+"行"+r+",列"+c);
                    }else if(cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN){
                        println();
                        println("错误1");
                    }else if(cell.getCellType() == HSSFCell.CELL_TYPE_ERROR){
                        println();
                        println("错误2");
                    }else if(cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
                        println();
                        println("错误3:"+"行"+r+",列"+c);
                    }else {
                        println();
                        println("错误4");
                    }

                }
            }
            stringBuilder[r].append(");");
            //替换掉第一次产生的 ，
            insertsql[r]=stringBuilder[r].toString().replaceAll("\\(,","(");
        }
        // 输出拼接的 insert sql
        // 并写入文件
        for(String s:insertsql){
            println(s);
            PrintWriterUtils.printWriter(new File(filePath+"//"+tableName+".sql"),s);
        }




    }

}
