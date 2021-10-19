package cc.webapi.baidu.netdisk.dto;

import cc.webapi.baidu.netdisk.constant.ConstantRegister;
import cc.webapi.baidu.netdisk.utils.RefObjWebParam;

/**
 * @author everforcc 2021-10-12
 */
public class BDQuotaReqDto {

    //文档 https://pan.baidu.com/union/doc/Cksg0s9ic

    private String access_token = ConstantRegister.access_token;

    /**
     * 是否检查免费信息，0为不查，1为查，默认为0
     */
    private int checkfree = 1;

    /**
     * 是否检查过期信息，0为不查，1为查，默认为0
     */
    private int checkexpire = 0;

    public String getAccess_token() {
        return access_token;
    }

    /*public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }*/

    public int getCheckfree() {
        return checkfree;
    }

    public void setCheckfree(int checkfree) {
        this.checkfree = checkfree;
    }

    public int getCheckexpire() {
        return checkexpire;
    }

    public void setCheckexpire(int checkexpire) {
        this.checkexpire = checkexpire;
    }

    public BDQuotaReqDto() {
    }

    /*@Override
    public String toString() {
        return RefObjWebParam.getField(this);
    }*/

    public String toParams() {
        return RefObjWebParam.getField(this);
    }

    public static class BDSizeRes{

        /**
         * 总空间大小，单位B
         */
        private int total;

        /**
         * 7天内是否有容量到期
         */
        private boolean expire;

        /**
         * 已使用大小，单位B
         */
        private int used;

        /**
         * 剩余大小，单位B
         */
        private int free;

        public BDSizeRes() {
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public boolean isExpire() {
            return expire;
        }

        public void setExpire(boolean expire) {
            this.expire = expire;
        }

        public int getUsed() {
            return used;
        }

        public void setUsed(int used) {
            this.used = used;
        }

        public int getFree() {
            return free;
        }

        public void setFree(int free) {
            this.free = free;
        }
    }

}
