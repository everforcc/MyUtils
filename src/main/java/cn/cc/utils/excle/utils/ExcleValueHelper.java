package cn.cc.utils.excle.utils;

import cn.cc.core.base.CharUtils;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Yukino
 * 2020/4/27
 */
public class ExcleValueHelper {

    // 用来协助处理excle的数据，只存一个sheet页

    // 行
    private int rowNum;
    // 列
    private int lineNum;

    private String strValue;

    private int intValue;

    // 用嵌套map来定位     x,y >> value   这种形式
    Map<Map<Integer,Integer>,String> map ;

    Map<Integer,Integer> key = new HashMap<>();

    public ExcleValueHelper() {
        map = new HashMap<Map<Integer,Integer>,String>();
    }

    public int size(){
        return map.size();
    }

    /**
     *  用来存入Excle的所有的值
     * @param rowNum
     * @param lineNum
     */
    public void setCellValue(int rowNum,int lineNum,String value){
        key = new HashMap<>();
        key.put(rowNum,lineNum);
        map.put(key,value);
    }

    /**
     *  excle中的横纵坐标 第一个单元格 1，A
     * @param xNum y
     * @param yStr x
     * @return
     */
    public String getCellValue(int xNum,String yStr){
        key = new HashMap<>();
        key.put(xNum-1,ExcleValueHelper.byteToInt(yStr)-1);
        return map.get(key);
    }

    // 这个是计算后的坐标 第一个单元格0,0
    public String getCellValue(int xNum,int yNum){
        key = new HashMap<>();
        key.put(xNum,yNum);
        return map.get(key);
    }

    //大概有这几种模式

    //a-z   1-26
    //aa-az 26+ 1-26   26+26 + 1-26
    //aaa-aaz
    // 其实就是26进制

    public static int byteToInt(String str){
        /*final int dif = 64;
        // 一组是26个
        final int length= 26 ;
        //几个字节
        byte[] bytes = str.getBytes();
        //a,aa,aaa
        int level = bytes.length;
        int result = 0 ;
        *//*for(int i=level;i>0;i--){
            result += new Double(Math.pow(length,(level-1))).intValue() * (bytes[level-1]-dif) ;
        }*//*

        for(int i=0;i<level;i++){
            // ABC 从最小的位数开始加 C+B+A
            result += new Double(Math.pow(length,(i))).intValue() * (bytes[level-i-1]-dif);
            //System.out.println("result:"+result);
        }
*/
        // System.out.println(str+","+CharUtils.byteToInt(str));
        return CharUtils.byteToInt(str);
    }

    @Test
    void a1(){

        //1
        /*System.out.println(this.byteToInt("A"));
        //1+26
        System.out.println(this.byteToInt("AA"));
        //26~26~
        System.out.println(this.byteToInt("AAA"));*/
        // a-z               26~0+x
        // a-z >> a-z        26~L + x
        // a-z >> a-z >>a-z  26*

        //26进制
        System.out.println(this.byteToInt("a"));
        System.out.println(this.byteToInt("A"));
        System.out.println(this.byteToInt("A"));

    }

}

