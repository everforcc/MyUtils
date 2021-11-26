package cc.advanced.web.craw.webmagic;

import cc.constant.ConstantFile;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author c.c.
 * @date 2020/12/11
 */
public class FeisuwxProcessor implements PageProcessor {

    private static String url = "http://wap.feisuwx.org/xxss-250824-45573978-2/";

    // 设置请求头等信息
    private Site site = Site
            .me()
            .setDomain("wap.feisuwx.org")
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");


    // 数据解析与存储
    @Override
    public void process(Page page) {
        page.putField("content",page.getHtml().xpath("//div[@id='chaptercontent']"));
    }

    // 加载请求 信息
    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        // 加载接口
        Spider.create(new FeisuwxProcessor())
              .addUrl(url) // 请求地址
              //.addPipeline(new FilePipeline(ConstantFile.javaFilePath + "/java/wemagic")) // 保存地址
              .addPipeline(new JsonFilePipeline(ConstantFile.javaFilePath + "/java/wemagic"))
              .run(); //启动
    }

}
