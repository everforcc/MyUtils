package cc.advanced.web.craw.webmagic;

import cc.constant.ConstantFile;
import org.apache.commons.collections.CollectionUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

import java.util.List;

/**
 * @author code4crafter@gmail.com
 * @since 0.5.0
 */
public class AngularJSProcessor implements PageProcessor {

    private Site site = Site.me();

    private static final String ARITICALE_URL = "http://angularjs\\.cn/api/article/\\w+";

    private static final String LIST_URL = "http://angularjs\\.cn/api/article/latest.*";

    @Override
    public void process(Page page) {
        if (page.getUrl().regex(LIST_URL).match()) {
            List<String> ids = new JsonPathSelector("$.data[*]._id").selectList(page.getRawText());
            if (CollectionUtils.isNotEmpty(ids)) {
                for (String id : ids) {
                    page.addTargetRequest("http://angularjs.cn/api/article/" + id);
                }
            }
        } else {
            page.putField("title", new JsonPathSelector("$.data.title").select(page.getRawText()));
            page.putField("content", new JsonPathSelector("$.data.content").select(page.getRawText()));
        }

        // 不知道json咋用的还是用alibaba的目前
                 /*List<Selectable> selectable_VolumesList = json.jsonPath("$.Volumes").nodes();
         for(Selectable selectable_Volume:selectable_VolumesList){
             System.out.println(selectable_Volume.jsonPath("$.Volume_name").get());

             List<Selectable> selectableList_ChaptersList = selectable_Volume.jsonPath("$.Chapters").nodes();
             for(Selectable selectableList_Chapters: selectableList_ChaptersList){
                 System.out.print(selectableList_Chapters.jsonPath("$.Chapter_id").get());
                 System.out.println(selectableList_Chapters.jsonPath("$.Chapter_name").get());
             }
         }*/
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new AngularJSProcessor()).addUrl("http://angularjs.cn/api/article/latest?p=1&s=20")
                .addPipeline(new FilePipeline(ConstantFile.L1_javaFilePath + "/java/wemagic"))
                .run();
    }
}

