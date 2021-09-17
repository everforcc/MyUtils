package cc.structure.msgtype.json.jsonpath;

import cc.constant.ConstantFile;
import com.jayway.jsonpath.*;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author c.c.
 *
 * @date 2021/1/12
 */
public class JsonPathAPI {

    public static void main(String[] args) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String result = "{\"code\":0,\"message\":\"0\",\"ttl\":1,\"data\":{\"from\":\"local\",\"result\":\"suee\",\"message\":\"\",\"quality\":208,\"format\":\"mp4\",\"timelength\":370451,\"accept_format\":\"mp4\",\"accept_description\":[\"高清 1080P\"],\"accept_quality\":[208],\"video_codecid\":7,\"seek_param\":\"start\",\"seek_type\":\"second\",\"durl\":[{\"order\":1,\"length\":370451,\"size\":55501663,\"ahead\":\"\",\"vhead\":\"\",\"url\":\"https://upos-sz-mirrorcos.bilivideo.com/upgcxcode/46/96/2239646/2239646-1-208.mp4?e=ig8euxZM2rNcNbRMnWdVhwdlhWKHhwdVhoNvNC8BqJIzNbfq9rVEuxTEnE8L5F6VnEsSTx0vkX8fqJeYTj_lta53NCM=\\u0026uipk=5\\u0026nbs=1\\u0026deadline=1610426980\\u0026gen=playurl\\u0026os=cosbv\\u0026oi=3524704618\\u0026trid=0f87a92a69e342e689da0d0e7cf62f45T\\u0026platform=html5\\u0026upsig=472fc16bf3a4132792a9c208107a42d7\\u0026uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform\\u0026mid=58572396\\u0026orderid=0,1\\u0026logo=80000000\",\"backup_url\":null}],\"support_formats\":[{\"quality\":208,\"format\":\"mp4\",\"new_description\":\"1080P 高清\",\"display_desc\":\"1080P\",\"superscript\":\"\"}]}}";

    static void t1(){
        System.out.println(result);
        DocumentContext documentContext = JsonPath.parse(result);
        String string = documentContext.read("$.data.durl[0].url");
        System.out.println(string);
    }

    private static String json = "";

    static void t20() throws IOException {
        File file = new File( ConstantFile.javaFilePath + "/java/test/test.json");
        String json = FileUtils.readFileToString(file);

        List<String> authors = JsonPath.read(json, "$.store.book[*].author");
        System.out.println(authors);
    }

    // 如果仅是单次使用是OK的，如果是多次使用的话，为了避免每次解析json都需要调用JsonPath.read(...)，你可以先解析json
    static void t21() throws IOException {
        File file = new File(ConstantFile.javaFilePath + "/java/test/test.json");
        String json = FileUtils.readFileToString(file);
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);
        String author0 = JsonPath.read(document, "$.store.book[0].author");
        String author1 = JsonPath.read(document, "$.store.book[1].author");
        System.out.println(author0);
        System.out.println(author1);
    }

    // JsonPath还提供了流式的API。这也是最灵活的一种。
    static void t22() throws IOException {
        File file = new File(ConstantFile.javaFilePath + "/java/test/test.json");
        String json = FileUtils.readFileToString(file);
        ReadContext ctx = JsonPath.parse(json);
        List<String> authorsOfBooksWithISBN = ctx.read("$.store.book[?(@.isbn)].author");
        List<Map<String, Object>> expensiveBooks = JsonPath
//                .using(configuration)
                .parse(json)
                .read("$.store.book[?(@.price > 10)]", List.class);
        System.out.println(authorsOfBooksWithISBN);
        System.out.println(expensiveBooks);
    }

    // 默认情况下，MappingProvider SPI提供了一个简单的对象映射器。这使您可以指定所需的返回类型，并且MappingProvider将尝试执行映射。在下面的示例中，演示了Long和Date之间的映射。
    static void t23() throws IOException {
        String json = "{\"date_as_long\" : 1411455611975}";
        Date date = JsonPath.parse(json).read("$['date_as_long']", Date.class);
        System.out.println(date);
    }

    // 如果将JsonPath配置为使用JacksonMappingProvider或GsonMappingProvider，您甚至可以将JsonPath输出直接映射到POJO。
    static void t24() throws IOException {
        File file = new File(ConstantFile.javaFilePath + "/java/test/test.json");
        String json = FileUtils.readFileToString(file);
        Configuration.setDefaults(new Configuration.Defaults() {

            private final JsonProvider jsonProvider = new JacksonJsonNodeJsonProvider();
            private final MappingProvider mappingProvider = new JacksonMappingProvider();

            @Override
            public JsonProvider jsonProvider() {
                return jsonProvider;
            }

            @Override
            public MappingProvider mappingProvider() {
                return mappingProvider;
            }

            @Override
            public Set<Option> options() {
                return EnumSet.noneOf(Option.class);
            }
        });

        Book book = JsonPath.parse(json).read("$.store.book[0]", Book.class);
        System.out.println(book);
    }

    // 要获取完整的泛型类型信息，请使用TypeRef。
    static void t25() throws IOException {
        File file = new File(ConstantFile.javaFilePath + "/java/test/test.json");
        String json = FileUtils.readFileToString(file);
        Configuration.setDefaults(new Configuration.Defaults() {

            private final JsonProvider jsonProvider = new JacksonJsonNodeJsonProvider();
            private final MappingProvider mappingProvider = new JacksonMappingProvider();

            @Override
            public JsonProvider jsonProvider() {
                return jsonProvider;
            }

            @Override
            public MappingProvider mappingProvider() {
                return mappingProvider;
            }

            @Override
            public Set<Option> options() {
                return EnumSet.noneOf(Option.class);
            }
        });
        TypeRef<List<String>> typeRef = new TypeRef<List<String>>() {};

        List<String> titles = JsonPath.parse(json).read("$.store.book[*].title", typeRef);
        System.out.println(titles);
    }

    // 向json中指定位置添加内容
    static void t26() throws IOException {
        File file = new File(ConstantFile.javaFilePath + "/java/test/test.json");
        String json = FileUtils.readFileToString(file);
        DocumentContext document = JsonPath.parse(json).put("$.store.book[*]", "kind", "paper");
        System.out.println(document.jsonString());

    }

}