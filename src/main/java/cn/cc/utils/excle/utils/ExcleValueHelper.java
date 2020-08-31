package cn.cc.utils.excle.utils;

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
     *  取出指定坐标的值
     * @param rowNum
     * @param lineNum
     * @return
     */
    public String getCellValue(int rowNum,int lineNum){
        key = new HashMap<>();
        key.put(rowNum,lineNum);
        return map.get(key);
    }


    //大概有这几种模式

    //a-z   1-26
    //aa-az 26+ 1-26   26+26 + 1-26
    //aaa-aaz
    // 其实就是26进制

    public int byteToInt(String str){
        final int dif = 64;
        // 一组是26个
        final int length= 26 ;
        //几个字节
        byte[] bytes = str.getBytes();
        //a,aa,aaa
        int level = bytes.length;
        int result = 0 ;
        /*for(int i=level;i>0;i--){
            result += new Double(Math.pow(length,(level-1))).intValue() * (bytes[level-1]-dif) ;
        }*/

        for(int i=0;i<level;i++){
            // ABC 从最小的位数开始加 C+B+A
            result += new Double(Math.pow(length,(i))).intValue() * (bytes[level-i-1]-dif);
            //System.out.println("result:"+result);
        }

        return result;
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
        System.out.println(this.byteToInt("W"));
        System.out.println(this.byteToInt("Z"));
        System.out.println(this.byteToInt("AD"));

    }

}

