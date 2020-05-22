package cn.cc.entity;

public class FileSysMsg {

    private Integer id;

    private String fileRealName;

    private String fileVirtualName;

    private String fileRealRoute;

    private String fileVirtualRoute;

    private String fileSize;

    private String fileType;

    private String fileCreateTime;

    public FileSysMsg() {
    }

    public FileSysMsg(Integer id, String fileRealName, String fileVirtualName, String fileRealRoute, String fileVirtualRoute, String fileSize, String fileType, String fileCreateTime) {
        this.id = id;
        this.fileRealName = fileRealName;
        this.fileVirtualName = fileVirtualName;
        this.fileRealRoute = fileRealRoute;
        this.fileVirtualRoute = fileVirtualRoute;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.fileCreateTime = fileCreateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileRealName() {
        return fileRealName;
    }

    public void setFileRealName(String fileRealName) {
        this.fileRealName = fileRealName;
    }

    public String getFileVirtualName() {
        return fileVirtualName;
    }

    public void setFileVirtualName(String fileVirtualName) {
        this.fileVirtualName = fileVirtualName;
    }

    public String getFileRealRoute() {
        return fileRealRoute;
    }

    public void setFileRealRoute(String fileRealRoute) {
        this.fileRealRoute = fileRealRoute;
    }

    public String getFileVirtualRoute() {
        return fileVirtualRoute;
    }

    public void setFileVirtualRoute(String fileVirtualRoute) {
        this.fileVirtualRoute = fileVirtualRoute;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileCreateTime() {
        return fileCreateTime;
    }

    public void setFileCreateTime(String fileCreateTime) {
        this.fileCreateTime = fileCreateTime;
    }
}
