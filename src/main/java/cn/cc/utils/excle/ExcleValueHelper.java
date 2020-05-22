package cn.cc.utils.excle;

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

}
