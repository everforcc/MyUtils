package cc.structure.msgtype.json.alifast;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class FastJsonUtils {

    /**
     * 大致有以下几种
     * 1. str转 JSONObject
     * 2. str转 JSONArray
     * 3. str转 Obj
     * 4. str转 List<JSONObject>
     * 5. List<JSONObject>转 JSONObject[]
     * 6. Obj转 str
     * 7. 从JSONObject取出信息
     * 8. 便利JSON（相同结构）取出信息
     * 9. map转 JSONObject
     */
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("key","value");
        System.out.println(objToJSONStr(map));
    }

    /**
     * 1. json字符串-简单对象型与JSONObject之间的转换
     */
    public static JSONObject strToJSONObject(String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        //JSONObject jsonObject1 = JSONObject.parseObject(JSON_OBJ_STR); //因为JSONObject继承了JSON，所以这样也是可以的
        return jsonObject;
    }

    /**
     * 2. json字符串-数组类型与JSONArray之间的转换
     */
    public static JSONArray strToJSONArray(String json) {
        JSONArray jsonArray = JSON.parseArray(json);
        //JSONArray jsonArray1 = JSONArray.parseArray(JSON_ARRAY_STR);//因为JSONArray继承了JSON，所以这样也是可以的
        return jsonArray;
    }

    /**
     * 3. json字符串-字符串与T对象之间的转换
     */
    public static <T extends Object> T strToObj(String json,Class<T> t) { // <T extends Object>
        return JSON.parseObject(json,t);
        //JSONArray jsonArray1 = JSONArray.parseArray(JSON_ARRAY_STR);//因为JSONArray继承了JSON，所以这样也是可以的
    }

    /**
     * 4.5.
     * json数组转jsonObject集合
     * 某些情况下所有数据返回会被公共框架包裹 []
     * 如果是数组会在原先数组下，再包裹一个[] 所以要取出为list
     * @param jsonArray
     */
    private void parseArrayToObjList(JSONArray jsonArray){
        //JSONArray jsonArray = JSONArray.parseArray(str);
        log.info(jsonArray.toJSONString());
        String json = jsonArray.getJSONObject(0).getString("key");
        List<JSONObject> datas = JSON.parseArray(json, JSONObject.class);
        JSONObject[] jsonAry = new JSONObject[datas.size()];
        System.out.println(datas.toArray(jsonAry));
    }

    /**
     * 6. Obj转 str
     * @param obj
     */
    public static <T extends Object>String objToJSONStr(T obj){
       return JSON.toJSON(obj).toString();
    }

    /**
     * 7. 从JSONObject取出信息
     */
    public static void jsonObjectForMsg(JSONObject jsonObject) {
        String teacherName = jsonObject.getString("String");
        Integer teacherAge = jsonObject.getInteger("Integer");
        JSONObject course = jsonObject.getJSONObject("JSONObject");
        JSONArray students = jsonObject.getJSONArray("JSONArray");
    }

    /**
     * 8.
     * 如果有变长但是数据类型相同的可以用这种方式来取值
     * 例如某些旧系统或者哪些,这种格式的，就要便利来取出
     * {
     *     "name[0]":"1",
     *     "name[1]":"2",
     *     "name[2]":"3"
     * }
     * @param json
     */
    public static void jsonForeach(String json){

        Map<String, Object> jsonMap = strToObj(json,Map.class);

        for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
            entry.getValue();
            entry.getKey();
        }
    }

    /**
     * 9. map转JSONObject
     * @param map
     * @return
     */
    public static JSONObject mapToJsonObject(Map<String,Object> map){
        return new JSONObject(map);
    }



}