package cn.cc.dsf.excle.utils;

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
 * 2020/6/11
 */
public class LoadExcle {

    // 只操作第一个sheet页
    // 放入 x y  v
    ExcleValueHelper excleValueHelper = new ExcleValueHelper();

   private String filePath;
    //加载excle信息

    private static int XStartIndex ;
    private static int YStartIndex ;
    private static int XEndIndex ;
    private static int YEndINdex ;

    private int defaultSheet = 0;

    public int getDefaultSheet() {
        return defaultSheet;
    }

    public void setDefaultSheet(int defaultSheet) {
        this.defaultSheet = defaultSheet - 1;
    }

    public LoadExcle(String filePath) {
        this.filePath = filePath;
    }

    // 改成    x char,y int,x char,y int
    public LoadExcle(String filePath, String xStratIndex, String xEndIndex, int yStartIndex, int yEndIndex) {
        this.filePath = filePath;
        XStartIndex = ExcleValueHelper.byteToInt(xStratIndex.toUpperCase()) - 1;
        YStartIndex = yStartIndex -1;
        XEndIndex = ExcleValueHelper.byteToInt(xEndIndex.toUpperCase())-1;
        YEndINdex = yEndIndex - 1;
    }

    public ExcleValueHelper flow(){
        int fileType = checkFileType(filePath);
        FileInputStream fileInputStream = getFileStream(filePath);
        Workbook wb = getWorkbook(fileType, fileInputStream);
        return loadFile(wb);
    }

    /**
     * 控制输出都用这个测试 成功后直接屏蔽掉
     * @param str
     */
    public void println(String str){
        System.out.println(str);
    }
    public void println(){ System.out.println(); }
    public void print(String str){ System.out.print(str); }


    public Workbook getWorkbook(int fileType, InputStream inputStream){
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

    public FileInputStream getFileStream(String filePath){
        File file = new File(filePath);
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

    public ExcleValueHelper loadFile( Workbook wb ){
        // 只操作第一个sheet页
        int firstSheetIndex= defaultSheet ;
        int rows;
        Sheet firstSheet = wb.getSheetAt(firstSheetIndex); //第一个sheet页
        // 获取行数
        rows = firstSheet.getPhysicalNumberOfRows();

        // 获取每行的列数
        int sheetcells[] = new int[rows];

        // 如果大于一行，并且 第一行不为空
        /*if(rows>1&&firstSheet.getRow(0)!=null){
            for(int i=0;i<rows;i++){
                sheetcells[i] =  firstSheet.getRow(i).getPhysicalNumberOfCells();
            }
        }*/

        return loadSheet(firstSheet,sheetcells[0],rows);
        //开始根据行列读取数据


    }

    public ExcleValueHelper loadSheet(Sheet sheet,int x,int y){

        /*int YdefaultIndex = 0;
        int Ydefaultend = y;
        int XdefaultIndex = 0;
        int Xdefaultend = x;*/

        int YdefaultIndex = YStartIndex ;
        int Ydefaultend = YEndINdex ;
        int XdefaultIndex = XStartIndex ;
        int Xdefaultend = XEndIndex ;

        //判断数据范围和指定范围

        //加载数据
        for (int i=YdefaultIndex;i<=Ydefaultend;i++){
            //一行一行的读
            Row row = sheet.getRow(i);
            for (int j=XdefaultIndex;j<=Xdefaultend;j++) {
                Cell cell = row.getCell(j);
                String value="";
                //开始去读单元格
                if(cell != null){
                    //如果是数字
                    if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                        value =  Double.toString(cell.getNumericCellValue());
                    // 一般只用到 数字和字符串
                    }else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                        value = cell.getStringCellValue();
                    }else if(cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
                        value=" ";
                    }else if(cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN){
                        value = Boolean.toString(cell.getBooleanCellValue());
                    }else if(cell.getCellType() == HSSFCell.CELL_TYPE_ERROR){
                        value = cell.getErrorCellValue()+"";
                    }else if(cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
                        // value = "函数";
                        value =  Double.toString(cell.getNumericCellValue());
                    }else {
                        value = "未知";
                    }
                }
                // 这一块逻辑需要移到外面去，属于业务逻辑
                if(value==null||"".equals(value)||" ".equals(value)){
                    value = "0";
                }
                excleValueHelper.setCellValue(i,j,value);
            }
        }

        Double a = new Double(0.0);
        for (int i=YdefaultIndex;i<=Ydefaultend;i++){
            for (int j=XdefaultIndex;j<=Xdefaultend;j++) {
                // println(i+","+j+":" + excleValueHelper.getCellValue(i,j) + "," + Double.valueOf(excleValueHelper.getCellValue(i,j))) ;
                // a += Double.valueOf(excleValueHelper.getCellValue(i,j));
                // println(a.toString());
            }
        }
        return excleValueHelper;
    }

}
