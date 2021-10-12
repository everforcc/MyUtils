package cc.webapi.baidu.netdisk.dto;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

/**
 * @author everforcc 2021-10-12
 */
@Getter
@Setter
public class BDUInfoResDto {


    /**
     * 头像地址
     */
    private String avatar_url;

    /**
     * 百度账号
     */
    private String baidu_name;

    /**
     * 网盘账号
     */
    private String netdisk_name;

    /**
     * 用户ID
     */
    private String uk;

    /**
     * 会员类型，0普通用户、1普通会员、2超级会员
     */
    private String vip_type;

    /**
     错误码	错误描述
     42905	查询用户名失败，可重试
     */
    private String errmsg;
    private String errno;

    private String request_id;

    public BDUInfoResDto() {
    }

    public BDUInfoResDto(String json) {
        JSON.parseObject(json, BDUInfoResDto.class);
    }

    @Override
    public String toString() {
        return "UInfoDto{" +
                "avatar_url='" + avatar_url + '\'' +
                ", baidu_name='" + baidu_name + '\'' +
                ", netdisk_name='" + netdisk_name + '\'' +
                ", uk='" + uk + '\'' +
                ", vip_type='" + vip_type + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", errno='" + errno + '\'' +
                ", request_id='" + request_id + '\'' +
                '}';
    }
}
