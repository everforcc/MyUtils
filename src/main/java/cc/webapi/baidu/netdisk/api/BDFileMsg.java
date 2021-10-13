package cc.webapi.baidu.netdisk.api;

import cc.webapi.baidu.netdisk.constant.ConstantBase;
import cc.webapi.baidu.netdisk.dto.BDXpanfileReqDto;
import cc.webapi.baidu.netdisk.utils.ApacheUtils;
import cc.webapi.baidu.netdisk.utils.BDFlagEnum;
import com.alibaba.fastjson.JSON;

/**
 * @author everforcc 2021-10-12
 */
public class BDFileMsg {

    // 3
    public static void main(String[] args) {
        // 3.1 获取文件列表
         xpanfile();
        // 3.2 递归获取文件列表

    }

    public static void xpanfile(){
        BDXpanfileReqDto bdXpanfileDto = new BDXpanfileReqDto("list");
        bdXpanfileDto.setWeb(BDFlagEnum.t);
        //bdXpanfileDto.setDir("/移动硬盘");
        String xpanfile = ConstantBase.xpanFile + bdXpanfileDto.toParams();
        System.out.println(xpanfile);
        String json = ApacheUtils.get(xpanfile);
        System.out.println(json);
        BDXpanfileReqDto.BDXpanfileResDto bdXpanfileResDto = JSON.parseObject(json,BDXpanfileReqDto.BDXpanfileResDto.class);
        System.out.println(bdXpanfileResDto.toString());
    }

}
