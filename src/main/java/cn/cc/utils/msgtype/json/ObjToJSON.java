package cn.cc.utils.msgtype.json;

import net.sf.json.JSONObject;

/**
 * Yukino
 * 2020/5/22
 */
public class ObjToJSON {


    public static void main(String[] args) {
        PrpjEcdInvoiceOcrQueryDto prpjEcdInvoiceOcrQueryDto = new PrpjEcdInvoiceOcrQueryDto();
        JSONObject jsonObject = JSONObject.fromObject(prpjEcdInvoiceOcrQueryDto);
        System.out.println(jsonObject.toString());
    }
}
