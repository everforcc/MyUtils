package cc.webapi.baidu.netdisk.dto;

import cc.webapi.baidu.netdisk.constant.ConstantRegister;
import cc.webapi.baidu.netdisk.utils.RefObjWebParam;
import com.alibaba.fastjson.JSON;

/**
 * @author everforcc 2021-10-12
 */
public class BDUInfoReqDto {

    private String method = "uinfo";
    private String access_token = ConstantRegister.access_token;

    public String getMethod() {
        return method;
    }

    public String getAccess_token() {
        return access_token;
    }

    public BDUInfoReqDto() {
    }

    public String toParams() {
        return RefObjWebParam.getField(this);
    }

    public static class BDUInfoResDto{

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

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getBaidu_name() {
            return baidu_name;
        }

        public void setBaidu_name(String baidu_name) {
            this.baidu_name = baidu_name;
        }

        public String getNetdisk_name() {
            return netdisk_name;
        }

        public void setNetdisk_name(String netdisk_name) {
            this.netdisk_name = netdisk_name;
        }

        public String getUk() {
            return uk;
        }

        public void setUk(String uk) {
            this.uk = uk;
        }

        public String getVip_type() {
            return vip_type;
        }

        public void setVip_type(String vip_type) {
            this.vip_type = vip_type;
        }

        public String getErrmsg() {
            return errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }

        public String getErrno() {
            return errno;
        }

        public void setErrno(String errno) {
            this.errno = errno;
        }

        public String getRequest_id() {
            return request_id;
        }

        public void setRequest_id(String request_id) {
            this.request_id = request_id;
        }

        public BDUInfoResDto() {
        }

        public BDUInfoResDto(String json) {
            JSON.parseObject(json, BDUInfoReqDto.class);
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }
}
