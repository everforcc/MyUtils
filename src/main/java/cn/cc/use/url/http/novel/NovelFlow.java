package cn.cc.use.url.http.novel;

/**
 * @author c.c.
 * @date 2020/12/26
 */
public interface NovelFlow {

    /**
     * 需要拿到首页的小说列表
     * 根据小说名获取连接，拿到第一章,然后开始下载
     * 目前没取到目录，不过用下一章也能用
     */

    // 请求超时问题,这个设置超时时间不管用啊,究竟是网速慢，还是什么慢
    // 还有插图呢, 关键还是目录要拿出来，目录也要拿出来，分页排序，插图按照章节递归命名
    // 虽然拿不到后续的，但是只需要拿到第一章就好， 小说名， 分章节下载，最后再合并 i++ 只有在下一章的时候再加

    // 存三份 html,txt/img,md

    /**
     * 1.首页
     * 2.分类
     * 3.书籍
     * 4.章节
     * 5.下载
     */

    void index(String  url);

    void type(String  url);

    void book(String  url);

    void menu(String  url);

    void down(String  url);

}
