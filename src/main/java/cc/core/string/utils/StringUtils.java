package cc.core.string.utils;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Yukino
 * 2020/6/10
 */
public class StringUtils {

    /**
     * 字符串数组排序
     * https://www.cnblogs.com/starcrm/p/10736749.html
     */


    public static void main(String[] args) {
        //testT();
    }
    // 测试下面两个方法


    /**
     *  用来截取字符串，或者填充字符串到指定的长度
     * @param str
     * @param maxLength
     * @return
     */
    public static String subBytes(String str,int maxLength){
        // 存临时的字符串
        StringBuffer stringBuffer = new StringBuffer("");
        //每次截取的字符串
        String strTemp;
        // 当前,截取字符串的长度
        int inxTemp;
        // 用来记录字符串的长度,字节数，可能会有中文等
        int index = 0;
        try {
            // 用来遍历字符串
            for(int i=0;i<str.length();i++){
                strTemp = str.substring(i,i+1);
                inxTemp = strTemp.getBytes("GBK").length;
                // 判断接下来的动作
                if((inxTemp + index)==maxLength){
                    // 如果正好就推出
                    return stringBuffer.append(strTemp).toString();
                //
                }else if((inxTemp + index) > maxLength){
                    //如果超过最大的那么就用上次的stringBuffer 加空格
                    for(int j=0 ; j < maxLength - index;j++){
                        stringBuffer.append(ConstantString.space);
                    }
                    return stringBuffer.toString();
                }
                //如果小于就 add字符串的临时变量继续
                stringBuffer.append(strTemp);
                index+=inxTemp;
            }

            // 如果遍历完成还没到最大数
            for(int j=0;j<maxLength-index;j++){
                stringBuffer.append(ConstantString.space);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }

    /**
     *  取出当前字符串的长度
     * @param remarkStr
     * @return
     */
    public static int strLength(String remarkStr){
        int lengthVal= 0;
        if(remarkStr!=null){
            for (int i = 0; i < remarkStr.length(); i++) {
                try{
                    String temp = remarkStr.substring(i, i + 1);
                    if(temp.matches(ConstantString.chineseUnicode) || temp.getBytes("GBK").length > 1){
                        lengthVal=lengthVal+2;
                    }else{
                        lengthVal=lengthVal+1;
                    }
                }catch (Exception e) {
                    lengthVal=lengthVal+2;
                }
            }
        }
        return lengthVal;
    }


    // 去重后排序,可以先取出对应的数字，然后按照大小排序
    /**
     * 给String去重，目前仅支持字母， 用来检查字符都包含哪些
     * @param string
     * @return
     */
    public static String strDistinct(String string){
        StringBuffer stringBuffer = new StringBuffer();
        Set<Character> set = new TreeSet<>();
        //String a="abca";
        char[] chars = string.toCharArray();
        for(char c: chars){
            System.out.println(c + ">>>:" + new Integer(c));
            set.add(new Character(c));
        }
        Iterator<Character> iterator =   set.iterator();
        while (iterator.hasNext()) {
            Character s = iterator.next();
            stringBuffer.append(s);
            System.out.println(s+" ");
        }
        return stringBuffer.toString();
    }



     void split(){
        // 测试split
        // 使用索引访问用 String 的 split 方法得到的数组时，需做最后一个分隔符后有无
        // 内容的检查，否则会有抛 IndexOutOfBoundsException 的风险。
        String a = "a,,,b,c,d,";
        String arry[] = a.split(",");
        System.out.println(arry.length);
     }

     // substring

}
