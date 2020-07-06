package cn.cc.core.string;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Yukino
 * 2020/6/10
 */
public class StringUtils {

    /* 用来处理string的工具类*/

    /**
     * 截取字符串
     */


    // 中文 英文 数字

    @Test
    void a2(){

        String str="34F,Shanghai World Financial Center";
        int maxLength = 70;
        String result = subBytes(str,maxLength);
        System.out.println(result+";");

        String str1="34F,Shanghai World Financial Center,100 Century Avenue,Shanghai,China";
        int str2=70;
        String result1 = subBytes(str1,str2);
        System.out.println(result1+";");

        byte a= 'A';
        //System.out.println(a-64);


    }

    public String subBytes(String str,int maxLength){
        // 存临时的字符串
        StringBuffer stringBuffer = new StringBuffer("");
        //每次截取的字符串
        String strTemp;
        // 截取字符串的长度
        int inxTemp;
        // 用来记录字符串的长度
        int index = 0;
        try {
            // 用来遍历字符串
            for(int i=0;i<str.length();i++){
                strTemp = str.substring(i,i+1);
                inxTemp = strTemp.getBytes("GBK").length;
                // 判断接下来的动作
                if((inxTemp+index)==maxLength){
                    // 如果正好就推出
                    return stringBuffer.append(strTemp).toString();
                }else if((inxTemp+index)>maxLength){
                    //如果超过最大的那么就用上一个加空格
                    for(int j=0;j<maxLength-index;j++){
                        stringBuffer.append(" ");
                    }
                    return stringBuffer.toString();
                }
                //如果小于就 add字符串的临时变量继续
                stringBuffer.append(strTemp);
                index+=inxTemp;
            }
            // 遍历完成还没到最大数
            for(int j=0;j<maxLength-index;j++){
                stringBuffer.append(" ");
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }


    /**
     * 给String去重，目前仅支持字母
     * @param string
     * @return
     */
    public String strDistinct(String string){
        StringBuffer stringBuffer = new StringBuffer();
        Set<Character> set = new TreeSet<>();
        //String a="abca";
        char[] chars = string.toCharArray();
        for(char c: chars){
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

    @Test
    void a1(){
        String s="UhFOUNO1mCHrmC61m1UgpPEfcuzaVP67JuaRVP6RDuA4Vh7ipPmRNvIaSuEfJOt-UhMnUcDaXg6RpPtaUu6RpPtaUuAeScMaJufGSPDfX17GpPMeVhA2VhFOUg71VdL1mCHrJs0tJsHsJs6RSNHwmNFaSg5wUhHzmu51UNaamBygSu_aUPHvmCIBSsMxmdLsmBZsUCmvS1OOmgIfJN2w_gHGSgUf_4t4SP5E_h2tmsU4mhmqXhziIgU-VhAxUPzBXifrDCOrIgU-VhAxVt7qSPtfKNmzJiyqU1UAXgE5UPFeNhfqUNO1mCezJN51JNmrHCHO8B0r8B0rIiMRpuAqKc2rpCfHAPadXgGl8PZO2Ner_5aHpvF1d5FdAP_ypOak";
        System.out.println(strDistinct(s));
        System.out.println(strDistinct(s).length());
    }

}
