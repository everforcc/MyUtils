package cc.webapi.baidu.netdisk.dto;

import cc.webapi.baidu.netdisk.constant.ConstantRegister;
import cc.webapi.baidu.netdisk.utils.BDFlagEnum;
import cc.webapi.baidu.netdisk.utils.BDOrderEnum;
import cc.webapi.baidu.netdisk.utils.DateUtils;
import cc.webapi.baidu.netdisk.utils.RefObjWebParam;
import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author everforcc 2021-10-12
 */
public class BDXpanfileReqDto {

    /**
     * 接口鉴权参数
     */
    private String access_token = ConstantRegister.access_token;

    /**
     * 参数确定了不同的接口
     */
    private String method;


    /**
     * 需要list的目录，以/开头的绝对路径, 默认为/
     */
    private String dir;

    /**
     排序字段：默认为name
     time表示先按文件类型排序，后按修改时间排序
     name表示先按文件类型排序，后按文件名称排序
     size表示先按文件类型排序， 后按文件大小排序
     */
    private String order;

    /**
     * 该KEY存在为降序，否则为升序 （注：排序的对象是当前目录下所有文件，不是当前分页下的文件）
     */
    private String desc;

    private Integer start;

    private Integer limit;

    /**
     * 值为web时， 返回dir_empty属性 和 缩略图数据
     */
    private String web;

    /**
     * 是否只返回文件夹，0 返回所有，1 只返回目录条目，且属性只返回path字段。
     */
    private Integer folder;

    /**
     * 是否返回 dir_empty 属性，0 不返回，1 返回
     */
    private Integer showempty;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(BDOrderEnum order) {
        switch (order) {
            case time:
                this.order = "time";
                break;
            case name:
                this.order = "name";
                break;
            case size:
                this.order = "size";
                break;
        }
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(BDFlagEnum desc) {
        switch (desc) {
            case t:
                this.desc = "";
                break;
        }
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(BDFlagEnum web) {
        switch (web) {
            case t:
                this.web = "0";
                break;
        }
    }

    public Integer getFolder() {
        return folder;
    }

    public void setFolder(BDFlagEnum folder) {
        switch (folder) {
            case t:
                this.folder = 1;
                break;
            case f:
                this.folder = 0;
                break;
        }
    }

    public Integer getShowempty() {
        return showempty;
    }

    public void setShowempty(BDFlagEnum showempty) {
        switch (showempty) {
            case t:
                this.showempty = 1;
                break;
            case f:
                this.showempty = 0;
                break;
        }
    }

    public BDXpanfileReqDto() {
    }

    public BDXpanfileReqDto(String method) {
        this.method = method;
    }

    public String toParams() {
        return RefObjWebParam.getField(this);
    }

    public static class BDXpanfileResDto{

        private String errno;
        private String guid_info;
        private String request_id;
        private String guid;
        private List<BDXpanfileRes> list;

        public String getErrno() {
            return errno;
        }

        public void setErrno(String errno) {
            this.errno = errno;
        }

        public String getGuid_info() {
            return guid_info;
        }

        public void setGuid_info(String guid_info) {
            this.guid_info = guid_info;
        }

        public String getRequest_id() {
            return request_id;
        }

        public void setRequest_id(String request_id) {
            this.request_id = request_id;
        }

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public List<BDXpanfileRes> getList() {
            return list;
        }

        public void setList(List<BDXpanfileRes> list) {
            this.list = list;
        }

        public BDXpanfileResDto() {
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }

    }

    private static class BDXpanfileRes{

        /**
         * 文件在云端的唯一标识ID
         */
        private String fs_id;

        /**
         * 文件的绝对路径
         */
        private String path;

        /**
         * 文件名称
         */
        private String server_filename;

        /**
         * 文件大小，单位B
         */
        private String size;

        /**
         * 文件在服务器修改时间
         */
        private String server_mtime;

        /**
         * 文件在服务器创建时间
         */
        private String server_ctime;

        /**
         * 文件在客户端修改时间
         */
        private String local_mtime;

        /**
         * 文件在客户端创建时间
         */
        private String local_ctime;

        /**
         * 是否目录，0 文件、1 目录
         */
        private String isdir;
        private static Map<String,String> isdirMap = new HashMap<>();
        static {
            isdirMap.put("0","文件");
            isdirMap.put("1","目录");
        }

        /**
         * 文件类型，1 视频、2 音频、3 图片、4 文档、5 应用、6 其他、7 种子
         */
        private String category;
        private static Map<String,String> categoryMap = new HashMap<>();
        static {
            categoryMap.put("1","视频");
            categoryMap.put("2","音频");
            categoryMap.put("3","图片");
            categoryMap.put("4","文档");
            categoryMap.put("5","应用");
            categoryMap.put("6","其他");
            categoryMap.put("7","种子");
        }


        /**
         * 文件的md5值，只有是文件类型时，该KEY才存在
         */
        private String md5;

        /**
         * 该目录是否存在子目录， 只有请求参数带WEB且该条目为目录时，该KEY才存在， 0为存在， 1为不存在
         */
        private String dir_empty;
        private static Map<String,String> dir_emptyMap = new HashMap<>();
        static {
            dir_emptyMap.put("0","存在子目录");
            dir_emptyMap.put("1","不存在子目录");
        }

        /**
         * 只有请求参数带WEB且该条目分类为图片时，该KEY才存在，包含三个尺寸的缩略图URL
         */
        private List<Map<String,String>> thumbs;

        public String getFs_id() {
            return fs_id;
        }

        public void setFs_id(String fs_id) {
            this.fs_id = fs_id;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getServer_filename() {
            return server_filename;
        }

        public void setServer_filename(String server_filename) {
            this.server_filename = server_filename;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = FileUtils.byteCountToDisplaySize(Long.valueOf(size));
        }

        public String getServer_mtime() {
            return server_mtime;
        }

        public void setServer_mtime(String server_mtime) {
            this.server_mtime = DateUtils.format(server_mtime);
        }

        public String getServer_ctime() {
            return server_ctime;
        }

        public void setServer_ctime(String server_ctime) {
            this.server_ctime = DateUtils.format(server_ctime);
        }

        public String getLocal_mtime() {
            return local_mtime;
        }

        public void setLocal_mtime(String local_mtime) {
            this.local_mtime = DateUtils.format(local_mtime);
        }

        public String getLocal_ctime() {
            return local_ctime;
        }

        public void setLocal_ctime(String local_ctime) {
            this.local_ctime = DateUtils.format(local_ctime);
        }

        public String getIsdir() {
            return isdir;
        }

        public void setIsdir(String isdir) {
            this.isdir = isdirMap.get(isdir);
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = categoryMap.get(category);
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public String getDir_empty() {
            return dir_empty;
        }

        public void setDir_empty(String dir_empty) {
            this.dir_empty = dir_emptyMap.get(dir_empty);
        }

        public List<Map<String,String>> getThumbs() {
            return thumbs;
        }

        public void setThumbs(List<Map<String,String>> thumbs) {
            this.thumbs = thumbs;
        }

        public BDXpanfileRes() {
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

}

