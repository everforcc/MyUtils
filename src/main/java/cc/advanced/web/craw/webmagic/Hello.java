package cc.advanced.web.craw.webmagic;

import cc.constant.ConstantFile;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;

/**
 * @author c.c.
 * @date 2020/12/11
 */
public class Hello {
    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor())
                //从"https://github.com/code4craft"开始抓
                .addUrl("http://wap.feisuwx.org/xxss-250824-45573978-2/")
                .addPipeline(new FilePipeline(ConstantFile.javaFilePath + "/java/wemagic/1.txt"))
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}
