package cn.cc.core.msgtype.del;

import cn.cc.core.msgtype.del.PrpjEcdInvoiceOcr;
import cn.cc.core.msgtype.del.PrpjEcdInvoiceOcrDto;
import cn.cc.core.msgtype.del.PrpjEcdInvoiceOcrQueryDto;
import cn.cc.core.msgtype.del.UserInfo;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Yukino
 * 2020/5/22
 */
public class ObjToJSON {


    public static void main(String[] args) {
        PrpjEcdInvoiceOcrQueryDto prpjEcdInvoiceOcrQueryDto = new PrpjEcdInvoiceOcrQueryDto();

        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        prpjEcdInvoiceOcrQueryDto.setDeductionform(list);
        prpjEcdInvoiceOcrQueryDto.setUserDto(new UserInfo());

        List<PrpjEcdInvoiceOcrDto> prpjEdInvoiceOcr = new ArrayList<PrpjEcdInvoiceOcrDto>();
        prpjEdInvoiceOcr.add(new PrpjEcdInvoiceOcrDto());
        prpjEdInvoiceOcr.add(new PrpjEcdInvoiceOcrDto());

        prpjEcdInvoiceOcrQueryDto.setPrpjEdInvoiceOcr(prpjEdInvoiceOcr);

        JSONObject jsonObject = JSONObject.fromObject(prpjEcdInvoiceOcrQueryDto);
        //System.out.println(jsonObject.toString());

        Map<String, Object> ecdInvoiceMap = new HashMap<String, Object>();
        List<PrpjEcdInvoiceOcr> ecdInvoices = new ArrayList<PrpjEcdInvoiceOcr>();
        ecdInvoices.add(new PrpjEcdInvoiceOcr());
        ecdInvoices.add(new PrpjEcdInvoiceOcr());
        ecdInvoiceMap.put("ecdInvoice", ecdInvoices);
        ecdInvoiceMap.put("code","0");
        ecdInvoiceMap.put("message","成功");
        System.out.println(JSONObject.fromObject(ecdInvoiceMap).toString());
    }
}
