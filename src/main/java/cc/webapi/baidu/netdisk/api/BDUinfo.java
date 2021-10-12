package cc.webapi.baidu.netdisk.api;

import cc.webapi.baidu.netdisk.constant.ConstantBase;
import cc.webapi.baidu.netdisk.constant.ConstantRegister;
import cc.webapi.baidu.netdisk.dto.BDUInfoResDto;
import cc.webapi.baidu.netdisk.utils.ApacheUtils;
import com.alibaba.fastjson.JSON;

/**
 * @author everforcc 2021-10-12
 */
public class BDUinfo {

    // 1

    public static void main(String[] args) {
        uinfo(ConstantRegister.access_token);
    }

    public static void uinfo(String access_token){
        String uinfoUrl = String.format(ConstantBase.uinfo,access_token);
        String json = ApacheUtils.get(uinfoUrl);
        System.out.println(JSON.parseObject(json, BDUInfoResDto.class));
    }

}
