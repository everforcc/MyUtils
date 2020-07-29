package cn.cc.core.http.crawler.webmagic;

/**
 * Yukino
 * 2020/5/12
 */
public class Scheduler {
    /**
     * Scheduler负责管理待抓取的URL，以及一些去重的工作。WebMagic默认提供了JDK的内存队列来管理URL，并用集合来进行去重。也支持使用Redis进行分布式管理。

     除非项目有一些特殊的分布式需求，否则无需自己定制Scheduler。
     */
}
