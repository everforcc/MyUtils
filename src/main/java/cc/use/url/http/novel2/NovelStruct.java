package cc.use.url.http.novel2;

import java.util.List;
import java.util.Map;

/**
 * @author c.c.
 * @date 2020/12/26
 */
public class NovelStruct {

    // 组织流程运转的数据

    /* 网站根目录 */
    private String root;
    /* 分类 */
    private List<String> typeList;
    /* 书籍url等 */
    private List<String> bookList;
    /* 第一个存书名，后续章节，但是章节名需要自定义，所以还是map */
    private Map<String,String> map;
    /* 书籍每一章节信息 */
    private List<String> contentList;

    public NovelStruct() {
    }

    public NovelStruct(String root, List<String> typeList, List<String> bookList, Map<String,String> map, List<String> contentList) {
        this.root = root;
        this.typeList = typeList;
        this.bookList = bookList;
        this.map = map;
        this.contentList = contentList;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public List<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }

    public List<String> getBookList() {
        return bookList;
    }

    public void setBookList(List<String> bookList) {
        this.bookList = bookList;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public List<String> getContentList() {
        return contentList;
    }

    public void setContentList(List<String> contentList) {
        this.contentList = contentList;
    }
}
