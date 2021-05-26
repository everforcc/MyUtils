package cn.cc.dsf.excle.use;

import cn.cc.dsf.excle.utils.ExcleValueHelper;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Yukino
 * 2020/4/29
 */
public class ExcleModelHelper {

    // 设计通用的excle数据读取问题

    //https://blog.csdn.net/baidu_40931662/article/details/98473227

    //用来生成费控工时sql

    //String sql = "insert into Accsh_Depsorttochannel values ('202003', 'FYFT', 'C001', 'HO', ',%s', ',%s', ,%s);";
    String sql = "insert into Accsh_Depsorttochannel values ('202003', 'FYFT', 'C001', 'HO', '%s', '%s', %s);";
    String sql2 = "insert into Accsh_Costtochannel values ('202003', 'FYFT', 'C001', 'HO', '%s', '%s', %s);";

    // excle路径
    String filePath="C:\\Users\\Yukino\\Desktop\\";

    // excle的name        生成的表名
    Map<String, String> map = new HashMap<String, String>()
    {{
        put("1.工时汇总2020-5-1测试new.xlsx", "tablename");
    }};
    ExcleValueHelper excleValueHelper = new ExcleValueHelper();


    public void flow(){
        for(Map.Entry<String,String> entry:map.entrySet() ){
            String fileName  = entry.getKey();
            println("文件名:" + filePath + fileName);
            int fileType = checkFileType(fileName);
            FileInputStream fileInputStream = getFileStream(fileName);
            Workbook wb = getWorkbook(fileType, fileInputStream);

            // 获取到了excle的输入流 ，开始处理数据
            loadFile(wb,entry.getValue());
        }
    }

    // 通用读取excle的数据
    private void loadFile(Workbook wb, String value) {
        // 获取sheet页的数量
        int sheetSum = wb.getNumberOfSheets();
        Sheet sheet;
        // 遍历sheet页读取 --- start
        for(int sheetNum=0;sheetNum<1;sheetNum++){ // 先只操作第一页
            println("当前是第"+sheetNum+"页");
            sheet = wb.getSheetAt(sheetNum); //第一个sheet页
            int rowSum = sheet.getPhysicalNumberOfRows();
            println("一共有"+rowSum+"行");
            for(int rowNum=0;rowNum<rowSum;rowNum++){

                Row row=sheet.getRow(rowNum);
                if(row!=null) {
                    int lineSum = row.getPhysicalNumberOfCells();
                    println("第" + rowNum + "行有" + lineSum + "列");
                    // 读取每个单元格
                    for(int lineNum=0;lineNum<lineSum;lineNum++){
                        Cell cell = row.getCell(lineNum);
                        if(cell==null){
                            println("第" + rowNum + "行,第" + lineNum+ "列为空");
                           continue;
                        }
                        String v = this.readCell(cell);
                        //System.out.println(lineNum+","+rowNum+":"+v);
                        excleValueHelper.setCellValue(lineNum,rowNum,v);
                    }
                }else {
                    println("第" + rowNum + "为空");
                }
            }
        }
        // 遍历sheet页读取 --- end
        // 数据坐标
        // y 5-13         x 3 9
        // 取值范围 x [3,9]
        //          y [2,13]
        // 这种是一行来算
        /*for(int y=2;y<=13;y++){
            for(int x=3;x<=9;x++ ){
                System.out.println(x+","+y+":"+excleValueHelper.getCellValue(x,y));
            }
        }*/
        BigDecimal a ;
        //但是现在需要一列来算
        /*for(int x=3;x<=9;x++ ){
            BigDecimal count= new BigDecimal(0);
            for(int y=5;y<=13;y++){
                System.out.println(String.format(sql,excleValueHelper.getCellValue(x,1),excleValueHelper.getCellValue(2,y),excleValueHelper.getCellValue(x,y)));
                a = new BigDecimal(excleValueHelper.getCellValue(x,y));
                count = count.add(a);
                //System.out.println(count);
            }
            System.out.println("--第"+x+"列的和为:"+count);
        }*/

        //F004
        for(int x=20;x<=20;x++ ){
            BigDecimal count= new BigDecimal(0);
            for(int y=5;y<=13;y++){
                //System.out.println(sql2+excleValueHelper.getCellValue(x,1)+excleValueHelper.getCellValue(2,y)+excleValueHelper.getCellValue(x,y));
                System.out.println(String.format(sql2,excleValueHelper.getCellValue(x,1),excleValueHelper.getCellValue(2,y),excleValueHelper.getCellValue(x,y)));
                a = new BigDecimal(excleValueHelper.getCellValue(x,y));
                count = count.add(a);
                //System.out.println(count);
            }
            System.out.println("--第"+x+"列的和为:"+count);
        }
    }

    public String readCell(Cell cell){
        String value = null;
        if(cell != null){
            //如果是数字
            if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                //if(c==0){
                if(0==0){
                    BigDecimal a = new BigDecimal(cell.getNumericCellValue());
                    value = a.setScale(6,BigDecimal.ROUND_HALF_DOWN)+"";
                    //value=Math.round(cell.getNumericCellValue())+"";
                }else{
                    value=cell.getNumericCellValue()+"";
                }
                //println("value:"+value);
                //stringBuilder[r].append(", '"+value+"'");
            }else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                // 去除所有空格 value=cell.getStringCellValue().replaceAll(" ","");
                value=cell.getStringCellValue();
                //System.out.println("vvvv:"+value);
                //stringBuilder[r].append(", '"+value+"'");
            }else if(cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
                value="0";
                //stringBuilder[r].append(", '"+value+"'");
                //println("空白:"+"行"+r+",列"+c);
            }else if(cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN){
                println();
                println("错误1");
            }else if(cell.getCellType() == HSSFCell.CELL_TYPE_ERROR){
                println();
                println("错误2");
            }else if(cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
                println();
                println("错误3:");
            }else {
                println();
                println("错误4");
            }
        }
        return value;
    }

    //
    /**
     * 控制输出都用这个测试 成功后直接屏蔽掉
     * @param str
     */
    public void println(String str){
        //System.out.println(str);
    }
    public void println(){ System.out.println(); }
    public void print(String str){ System.out.print(str); }
    public Workbook getWorkbook(int fileType, InputStream inputStream){
        Workbook wb = null;
        switch (fileType){
            case 2003:
                try {
                    wb = new HSSFWorkbook(inputStream);
                } catch (IOException e) {
                    println("生成2003work出错");
                    e.printStackTrace();
                }
                break;
            case 2007:
                try {
                    wb = new XSSFWorkbook(inputStream);
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
        if(isExcel2003(name)){ return 2003; }
        if(isExcel2007(name)){ return 2007; }
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
}
