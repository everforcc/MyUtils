package cc.structure.msgtype.json.jsonpath;

import cc.constant.ConstantFile;
import cc.resource.PropertiesJSON;
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
            // https://github.com/json-path/JsonPath
            // 两种读取方式
            //tJsonPath_1();
            //tJsonPath_2();

            // 预读
            //tJsonPath_read();

            // 流试api
            //tJsonPath_flow();

            // 映射到某个简单对象
            //tJsonPath_Mapping();

            // 映射到pojo
            //tJsonPath_MappingPojo();

            // 泛型
            //tJsonPath_MappingTypeRef();

            // 更新json信息
            tJsonPath_update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String result = PropertiesJSON.jsonPath();
    private static String jsonPathExample = PropertiesJSON.jpexample();

    private static void tJsonPath_1(){
        System.out.println(result);
        DocumentContext documentContext = JsonPath.parse(result);
        String string = documentContext.read("$.data.durl[0].url");
        System.out.println(string);
    }

    private static void tJsonPath_2() throws IOException {
        List<String> authors = JsonPath.read(jsonPathExample, "$.store.book[*].author");
        System.out.println(authors);
    }

    // 如果仅是单次使用是OK的，如果是多次使用的话，为了避免每次解析json都需要调用JsonPath.read(...)，你可以先解析json
    private static void tJsonPath_read() throws IOException {
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(jsonPathExample);
        String author0 = JsonPath.read(document, "$.store.book[0].author");
        String author1 = JsonPath.read(document, "$.store.book[1].author");
        System.out.println(author0);
        System.out.println(author1);
    }

    // JsonPath还提供了流式的API。这也是最灵活的一种。
    private static void tJsonPath_flow() throws IOException {

        ReadContext ctx = JsonPath.parse(jsonPathExample);
        List<String> authorsOfBooksWithISBN = ctx.read("$.store.book[?(@.isbn)].author");

        List<Map<String, Object>> expensiveBooks = JsonPath
//                .using(configuration)
                .parse(jsonPathExample)
                .read("$.store.book[?(@.price > 10)]", List.class);

        System.out.println(authorsOfBooksWithISBN);
        System.out.println(expensiveBooks);
    }

    // 默认情况下，MappingProvider SPI提供了一个简单的对象映射器。这使您可以指定所需的返回类型，并且MappingProvider将尝试执行映射。在下面的示例中，演示了Long和Date之间的映射。
    private static void tJsonPath_Mapping() throws IOException {
        String json = "{\"date_as_long\" : 1411455611975}";
        Date date = JsonPath.parse(json).read("$['date_as_long']", Date.class);
        System.out.println(date);
    }

    // 如果将JsonPath配置为使用JacksonMappingProvider或GsonMappingProvider，您甚至可以将JsonPath输出直接映射到POJO。
    private static void tJsonPath_MappingPojo() throws IOException {

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

        Book book = JsonPath.parse(jsonPathExample).read("$.store.book[0]", Book.class);
        System.out.println(book);
    }

    // 要获取完整的泛型类型信息，请使用TypeRef。
    static void tJsonPath_MappingTypeRef() throws IOException {

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

        List<String> titles = JsonPath.parse(jsonPathExample).read("$.store.book[*].title", typeRef);
        System.out.println(titles);
    }

    // 向json中指定位置添加内容
    static void tJsonPath_update() throws IOException {

        DocumentContext document = JsonPath.parse(jsonPathExample).put("$.store.book[*]", "kind", "paper");
        System.out.println(document.jsonString());

    }

}