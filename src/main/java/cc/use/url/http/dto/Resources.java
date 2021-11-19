package cc.use.url.http.dto;

import cc.core.io.PrintWriterUtils;
import com.alibaba.fastjson.JSON;

public class Resources {

    /**
     * 公共资源所需要的属性
     * 1. 网络资源示例
     */

    private String webRoot;
    private String webType;
    private String url;
    // 可以设置为md格式
    private String content;
    private String encFileName;
    private String decFileName;
    // txt,game,video，pic
    private String fileType;

    // 文件夹分级
    private String[] dir;
    private String bdPath;
    private String diskPath;

    public Resources() {
    }

    public Resources(String webRoot, String webType, String url, String content, String encFileName, String decFileName, String fileType, String[] dir, String bdPath, String diskPath) {
        this.webRoot = webRoot;
        this.webType = webType;
        this.url = url;
        this.content = content;
        this.encFileName = encFileName;
        this.decFileName = decFileName;
        this.fileType = fileType;
        this.dir = dir;
        this.bdPath = bdPath;
        this.diskPath = diskPath;
    }

    public String getWebRoot() {
        return webRoot;
    }

    public void setWebRoot(String webRoot) {
        this.webRoot = webRoot;
    }

    public String getWebType() {
        return webType;
    }

    public void setWebType(String webType) {
        this.webType = webType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEncFileName() {
        return encFileName;
    }

    public void setEncFileName(String encFileName) {
        this.encFileName = encFileName;
        // 在此解密文件名
    }

    public String getDecFileName() {
        return decFileName;
    }

    public void setDecFileName(String decFileName) {
        this.decFileName = decFileName;
        // 在此加密文件名

    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String[] getDir() {
        return dir;
    }

    public void setDir(String[] dir) {
        this.dir = dir;
    }

    public String getBdPath() {
        return bdPath;
    }

    public void setBdPath(String bdPath) {
        this.bdPath = bdPath;
    }

    public String getDiskPath() {
        return diskPath;
    }

    public void setDiskPath(String diskPath) {
        this.diskPath = diskPath;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static void main(String[] args) {
        Resources resources = new Resources();
        resources.setWebRoot("www.baidu.com\r\n");
        resources.setWebType("游戏");
        resources.setUrl("www.baidu.com/page");
        resources.setContent("<font face=\"SimSun\" size=3>\n" +
                "\n" +
                "- 普通的一行\n" +
                "\n" +
                "1. 1\n" +
                "2. 22\n" +
                "3. 333\n" +
                "4. 下面是个图片\n" +
                "\n" +
                "![link](https://gitee.com/MyYukino/media/raw/master/picture/%E9%9D%A2%E5%8C%85.jpg)\n" +
                "\n" +
                "</font>");
        resources.setDecFileName("文件名:加密前");
        resources.setEncFileName("文件名:加密后");
        resources.setFileType("例如游戏，pc，krkr，视频的话就是分类，热血");

        resources.setDir(new String[]{"目录父", "目录子"});
        resources.setBdPath("根目录+dir split");
        resources.setDiskPath("根目录+dir split");
        System.out.println(resources);

        PrintWriterUtils.fileWriter("C:/test/wx/",resources.getDecFileName() + ".txt",resources.toString());
        PrintWriterUtils.fileWriter("C:/test/wx/",resources.getDecFileName() + ".md",resources.getContent());
    }
}
