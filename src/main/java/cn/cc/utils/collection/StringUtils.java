package cn.cc.utils.collection;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class StringUtils {


    /**
     * 集合相关的工具类
     */


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
}
