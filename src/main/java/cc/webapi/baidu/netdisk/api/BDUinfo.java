package cc.webapi.baidu.netdisk.api;

import cc.webapi.baidu.netdisk.constant.ConstantBase;
import cc.webapi.baidu.netdisk.constant.ConstantRegister;
import cc.webapi.baidu.netdisk.dto.BDUInfoReqDto;
import cc.webapi.baidu.netdisk.utils.ApacheUtils;
import com.alibaba.fastjson.JSON;

/**
 * @author everforcc 2021-10-12
 */
public class BDUinfo {

    // 1

    public static void main(String[] args) {
        uinfo();
    }

    public static void uinfo(){
        BDUInfoReqDto bduInfoReqDto = new BDUInfoReqDto();
        String uinfoUrl = ConstantBase.uinfo + bduInfoReqDto.toParams();
        System.out.println(uinfoUrl);
        String json = ApacheUtils.get(uinfoUrl);
        System.out.println(json);
        BDUInfoReqDto.BDUInfoResDto bduInfoResDto = JSON.parseObject(json, BDUInfoReqDto.BDUInfoResDto.class);
        System.out.println(bduInfoResDto.toString());
    }

}
