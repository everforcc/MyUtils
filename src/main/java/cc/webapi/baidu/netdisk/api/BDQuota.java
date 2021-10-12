package cc.webapi.baidu.netdisk.api;

import cc.webapi.baidu.netdisk.constant.ConstantBase;
import cc.webapi.baidu.netdisk.dto.BDQuotaReqDto;
import cc.webapi.baidu.netdisk.utils.ApacheUtils;

/**
 * @author everforcc 2021-10-12
 */
public class BDQuota {

    // 2

    public static void main(String[] args) {
        quota();
    }

    public static void quota(){
        BDQuotaReqDto bdQuotaReqDto = new BDQuotaReqDto();
        String quotaUrl = ConstantBase.quota + bdQuotaReqDto.toParams();
        System.out.println(quotaUrl);
        String json = ApacheUtils.get(quotaUrl);
        System.out.println(json);
    }

}
