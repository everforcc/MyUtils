package cc.maven.excle.use;

import cc.constant.ConstantFile;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Yukino
 * 2020/4/26
 */
public class CostmcsBUDGET01 {
    //用来生成费控预算sql

    // excle路径
    String filePath= ConstantFile.javaFilePath + "/费控费用预算/20200427预算导入/2020导入excle/";
    //String fileList[]={"2020年AD&IT预算（不含滴滴）---导费控.xlsx","2020年HR预算---导费控.xlsx"};
    //表名
    //String tableName[] = {"ADIT_BUDGET",""};
    //String insertBegin[] = {" insert into ADIT_BUDGET  values  ",""};

    // 换成map       key excle名 value tablename
    Map<String, String> map = new HashMap<String, String>()
    {{
        put("ADIT.xlsx", "ADIT_BUDGET");
        put("HR.xlsx", "HR_BUDGET");
        //asset.xlsx
        put("asset.xlsx", "asset");
    }};

    int num=0;


    //@Test
    public void flow1(){
        for(Map.Entry<String,String> entry:map.entrySet() ){
            String fileName  = entry.getKey();
            println("文件名:" + filePath + fileName);
            int fileType = checkFileType(fileName);
            FileInputStream fileInputStream = getFileStream(fileName);
            Workbook wb = getWorkbook(fileType, fileInputStream);
            loadFile(wb,entry.getValue());
        }
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
        // 存入Map
        Map<Map<Integer,Integer>,String> map_xyv = new HashMap<Map<Integer,Integer>,String>();
        Map<Integer,Integer> key_xy = new HashMap<>();
        for(Integer r=0; r<sheetrows[useSheetRowsNum]; r++){ // 每列从上倒下
            stringBuilder[r] = new StringBuilder().append("insert into " + tableName + " values (");
            Row row = sheet_1.getRow(r);
            if(row==null){
                continue;
            }
            // 第一个sheet 页 的第一行
            String value = "未知";
            for(Integer c=0; c<sheetcells[0][0] ; c++){// 每一行 从左到有
                // 每次开始前先 为空 主要是excle的数据可能不全
                value="";
                key_xy = new HashMap<>();
                Cell cell = row.getCell(c);
                if(cell != null){
                    //如果是数字
                    if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                        if(c==0){
                            value=Math.round(cell.getNumericCellValue())+"";
                            //value=cell.getNumericCellValue()+"";
                        }else{
                            value=cell.getNumericCellValue()+"";
                        }
                        System.out.println("value:"+value);
                        stringBuilder[r].append(", '"+value+"'");
                    }else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                        // 去除所有空格
                        value=cell.getStringCellValue().replaceAll(" ","");
                        stringBuilder[r].append(", '"+value+"'");
                    }else if(cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
                        value="0";
                        stringBuilder[r].append(", '"+value+"'");
                        //println("空白:"+"行"+r+",列"+c);
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
                //存数
                key_xy.put(r,c);
                map_xyv.put(key_xy,value);
                println("r:"+r+",c:"+c+",v:"+value);
            }
            stringBuilder[r].append(");");
            //替换掉第一次产生的 ，
            insertsql[r]=stringBuilder[r].toString().replaceAll("\\(,","(");
        }
        //

        // 输出拼接的 insert sql
        // 并写入文件
        /*for(String s:insertsql){
            println(s);

        }*/
        List<String> ECBGTPLANDETAIL=new ArrayList<>();
        StringBuilder sqlInsert=new StringBuilder();
        String headerid ="BGT00002020000001";

        for(Integer i=2;i<sheetrows[useSheetRowsNum];i++){// 行
            for(Integer j=2;j<sheetcells[0][0];j++){// 列
                key_xy = new HashMap<>();
                key_xy.put(i,j);
                //System.out.println(i+"行"+j+"列"+map_xyv.get(key_xy));
                String v=map_xyv.get(key_xy);
                key_xy=new HashMap<>();
                //拼接sql
                //System.out.println("v:"+v);
                if(v!=null&&!"".equals(v)&&!"null".equals(v)){
                    if(!v.equals("0.0")){
                        sqlInsert=new StringBuilder();
                        //  取出所需的字段
                        // 660
                        key_xy = new HashMap<>();
                        key_xy.put(i,0);
                        String itemcode=map_xyv.get(key_xy);
                        System.out.println("itemcode:"+ itemcode);
                        // BJ
                        key_xy = new HashMap<>();
                        key_xy.put(0,j);
                        String articlecode=map_xyv.get(key_xy);
                        println("articlecode:"+articlecode);
                        // year
                        key_xy = new HashMap<>();
                        key_xy.put(i,1);
                        println(map_xyv.get(key_xy));
                        //key_xy = new HashMap<>();
                        String yearPeriod=map_xyv.get(key_xy);
                        println("yearPeriod:"+yearPeriod);
                        String year = yearPeriod.substring(0,4);
                        println("year:"+year);
                        // 月份
                        //key_xy = new HashMap<>();
                        String period = yearPeriod.substring(yearPeriod.length()-4,yearPeriod.length()-2);
                        println("period:"+period);
                        // BJGM0
                        key_xy = new HashMap<>();
                        key_xy.put(1,j);
                        String depcode = map_xyv.get(key_xy);
                        // F01
                        key_xy = new HashMap<>();
                        key_xy.put(0,j);
                        String F01 = map_xyv.get(key_xy);

                        num++;
                        println("num:"+num);
                        sqlInsert.append("insert into ECBGTPLANDETAIL values (");
                        sqlInsert.append("'"+headerid+"',");
                        sqlInsert.append("'"+ num +"',");
                        //2110000000
                        sqlInsert.append("("+"select e.comcode from ecdcompany e where e.articlecode='"+articlecode+"'"+"),");
                        sqlInsert.append("'"+year+"',");
                        sqlInsert.append("'"+period+"',");
                        sqlInsert.append("'"+itemcode+"',");
                        // bgditemname
                        sqlInsert.append("'',");
                        sqlInsert.append("'CNY',");
                        sqlInsert.append("1.000000,");
                        //sqlInsert.append("1.000000,");

                        sqlInsert.append(""+v+",");
                        sqlInsert.append(""+v+",");
                        sqlInsert.append("0,");
                        sqlInsert.append("'',");
                        sqlInsert.append("'"+depcode+"',");
                        sqlInsert.append("'"+F01+"',");

                        for(int f=0;f<7;f++){ sqlInsert.append("'0',"); }
                        sqlInsert.append("'"+depcode+"',");
                        for(int f=0;f<21;f++){sqlInsert.append("'',");}

                        sqlInsert.append("'2000000000',");
                        sqlInsert.append("to_date('27-04-2020', 'dd-mm-yyyy')");
                        sqlInsert.append(");");
                        ECBGTPLANDETAIL.add(sqlInsert.toString());
                    }
                }
            }
        }

        for(String s:ECBGTPLANDETAIL){
            //println(s);

            //System.out.println(s);
            //System.out.println(s);
        }

    }


}
