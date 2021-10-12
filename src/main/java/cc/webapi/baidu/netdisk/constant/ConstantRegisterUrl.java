package cc.webapi.baidu.netdisk.constant;

import cc.webapi.baidu.netdisk.utils.RefObjWebParam;

/**
 * @author everforcc 2021-10-12
 */
public class ConstantRegisterUrl {
    public static void main(String[] args) {
        CodeTokenReqDto codeTokenReqDto = new ConstantRegisterUrl.CodeTokenReqDto();
        System.out.println(codeTokenReqDto.toString());
    }

    // 在以下地址授权，需要跳转，前段做，暂时手动操作
    /**
     * http://openapi.baidu.com/oauth/2.0/authorize?response_type=code&client_id=1hsEFmgaYc3vIEhQdHAheNKh0jo2d6eh&redirect_uri=oob&scope=basic,netdisk&display=tv&qrcode=1&force_login=1&device_id=820921428tp8x63q51
     */

    /**
     * 2.3用CODE换取Access_token
     */
     public static String codeTotoken = "https://openapi.baidu.com/oauth/2.0/token";

     public static class CodeTokenReqDto{
         // 固定值
         public String grant_type = ConstantRegister.authorization_code;
         // 用户授权后得到的code
         public String code = "f12bf99bc21b0e7d8b9899dff838736b";
         //
         public String client_id = ConstantRegister.API_Key;
         public String client_secret = ConstantRegister.Secret_Key;
         public String redirect_uri = ConstantRegister.redirect_uri;

         public CodeTokenReqDto() {
         }

         @Override
         public String toString() {
             return RefObjWebParam.getField(this);
         }
     }

    public class RefreshTokenReqDto{
        // 固定值
        public String grant_type = ConstantRegister.authorization_code_refresh;
        // 用户授权后得到的code
        public String refresh_token;
        //
        public String client_id = ConstantRegister.API_Key;
        public String client_secret = ConstantRegister.Secret_Key;

        public RefreshTokenReqDto(String refresh_token) {
            this.refresh_token = refresh_token;
        }

        /**
         * 使用带构造的，差个数据
         */
        public RefreshTokenReqDto() {
        }

        @Override
        public String toString() {
            return RefObjWebParam.getField(this);
        }
    }

    public class CodeTokenResDto{
        // 获取到的授权token，作为调用其他接口访问用户数据的凭证
        private String access_token;
        // access_token的有效期，单位：秒
        private int expires_in;
        // 用于刷新access_token, 有效期为10年
        private String refresh_token;
        // access_token最终的访问权限，即用户的实际授权列表
        private String scope ;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }

        public String getRefresh_token() {
            return refresh_token;
        }

        public void setRefresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public CodeTokenResDto() {
        }
    }

}
