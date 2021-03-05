package cn.cc.elasticsearch.api;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptService;


import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.elasticsearch.common.xcontent.XContentFactory.*;
/**
 * @author c.c.
 * @date 2021/3/4
 */
public class UpdateAPI {

    /**
     * 插入的几种方法
     * 如果没有的话可以判断先插入
     */

    public static void main(String[] args) {
        //updateUpdateRequest();
        //updatePrepareUpdate();
        //updateByScript();
        //updateMergeDocument();
        upsert();
    }

    private static TransportClient client = ElasticsearchAPI.init();

    public static void updateUpdateRequest(){
        UpdateResponse updateResponse;
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("persons");
        updateRequest.type("earth");
        updateRequest.id("AXf8Xdv1H2Yra7uNHkW2");
        try {
            updateRequest.doc(jsonBuilder()
                    .startObject()
                    .field("gender", "female")
                    .endObject());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            updateResponse = client.update(updateRequest).get();
            System.out.println(updateResponse.status());
            System.out.println(updateResponse.getGetResult());
            System.out.println(updateResponse.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    public static void updatePrepareUpdate(){

        /* 代码和命令都不能执行 脚本 */
        /*client.prepareUpdate("ttl", "doc", "1")
                .setScript(new Script(
                        "ctx._source.gender = \"male\"",
                        ScriptService.ScriptType.FILE, null, null))
                .get();*/

        try {
            UpdateResponse updateResponse = client.prepareUpdate("persons", "earth", "AXf8Xdv1H2Yra7uNHkW2")
                    .setDoc(jsonBuilder()
                            .startObject()
                            .field("gender", "male")
                            .endObject())
                    .get();

            System.out.println(updateResponse.status());
            System.out.println(updateResponse.getId());
            System.out.println(updateResponse);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateByScript() {
        UpdateResponse updateResponse = null;
        UpdateRequest updateRequest = new UpdateRequest("persons", "earth", "1")
                .script(new Script("ctx._source.gender = \"female\""));
        
        try {
            updateResponse = client.update(updateRequest).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(updateResponse.toString());

    }

    // 通过文档 部分更新
    public static void updateMergeDocument(){
        UpdateResponse updateResponse = null;
        UpdateRequest updateRequest = null;
        try {
            updateRequest = new UpdateRequest("persons", "earth", "1")
                    .doc(jsonBuilder()
                            .startObject()
                            .field("gender", "male")
                            .endObject());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            updateResponse = client.update(updateRequest).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(updateResponse.toString());
    }


    // If the document does not exist, the one in indexRequest will be added
    public static void upsert(){
        UpdateResponse updateResponse = null;
        IndexRequest indexRequest = null;
        UpdateRequest updateRequest = null;

        try {
            indexRequest = new IndexRequest("index", "type", "1")
                    .source(jsonBuilder()
                            .startObject()
                            .field("name", "Joe Smith")
                            .field("gender", "male")
                            .endObject());

            updateRequest = new UpdateRequest("index", "type", "1")
                    .doc(jsonBuilder()
                            .startObject()
                            .field("gender", "female")
                            .endObject())
                    .upsert(indexRequest);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            updateResponse = client.update(updateRequest).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(updateResponse);

    }


}
