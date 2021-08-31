package cc.structure.msgtype.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateJSON {

    /**
     * sf json的使用，其实用起来都差不多
     */

    private static Map<String,String> map=new HashMap<String, String>();
    private static List<Map<String,String>> arrayList=new ArrayList<>();
    private static List<String> objList=new ArrayList<>();

    static {
        map.put("a","aa");
        map.put("b","bb");
        map.put("c", "cc");
        map.put("d","d");
        arrayList.add(map);
        objList.add("a");
        objList.add("b");
        objList.add("c");
    }

    /**
     * 格式化数组
     */
    public static void  arrayJson(){
        JSONArray jsonObject = JSONArray.fromObject(arrayList);
        JSONObject jsonObject1 = JSONObject.fromObject(map);
        System.out.println(jsonObject.toString());
        System.out.println(jsonObject1.toString());

    }

    /*public static void stringJson(){
        JSONObject jsonObject = JSONObject.fromObject(objList);
        System.out.println(jsonObject.toString());
    }*/

    /**
     * 读取json
     * @param json
     */
    public void readJson(String json){
        JSONObject jsonObject = JSONObject.fromObject(json);
        JSONArray jsonArray=new JSONArray();

        //数组操作
        if(jsonObject.has("")) { //先判断是否存在
          jsonArray = jsonObject.getJSONArray(""); // 获得json数组
        }
        //获取数组的内容
        for(int i=0 ; i<jsonArray.size();i++ ){
            JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
        }


        if(jsonObject.has("")) { //先判断是否存在
            JSONObject jsonObject1 = jsonObject.getJSONObject(""); // 再获取一个json对象
        }

        //最终获取每一个值
        if(jsonObject.has("")) { //先判断是否存在
            String string = jsonObject.getString(""); // 最终获取key/value
        }

    }

    public static void main(String[] args) {
        arrayJson();
        //stringJson();
    }
}
