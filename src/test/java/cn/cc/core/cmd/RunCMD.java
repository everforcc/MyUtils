package cn.cc.core.cmd;

import cc.java0.cmd.CmdUtils;
import org.junit.jupiter.api.Test;

/**
 * @author c.c.
 * @date 2021/2/25
 */
public class RunCMD {

    String json = "{" +
            "  \"mappings\": {" +
            "    \"person\": {" +
            "      \"properties\": {" +
            "        \"user\": {" +
            "          \"type\": \"text\"," +
            "          \"analyzer\": \"ik_max_word\"," +
            "          \"search_analyzer\": \"ik_max_word\"" +
            "        }," +
            "        \"title\": {" +
            "          \"type\": \"text\"," +
            "          \"analyzer\": \"ik_max_word\"," +
            "          \"search_analyzer\": \"ik_max_word\"" +
            "        }," +
            "        \"desc\": {" +
            "          \"type\": \"text\"," +
            "          \"analyzer\": \"ik_max_word\"," +
            "          \"search_analyzer\": \"ik_max_word\"" +
            "        }" +
            "      }" +
            "    }" +
            "  }" +
            "}";

    String jsonDto = "{" +
            "  \\\"user\\\": \\\"张三\\\"," +
            "  \\\"title\\\": \\\"工程师\\\"," +
            "  \\\"desc\\\": \\\"数据库管理\\\"" +
            "}";

    @Test
    void t1(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" curl -X PUT ");
        stringBuilder.append(" \"localhost:9200/accounts\" ");
        //
        stringBuilder.append(" -H \"content-Type:application/json\" ");
        stringBuilder.append(" -d  ");
        stringBuilder.append(json);

        CmdUtils.execCmd(stringBuilder.toString());
    }

    @Test
    void t2(){
        // 如果一切正常，Elastic 就会在默认的9200端口运行。这时，打开另一个命令行窗口，请求该端口，会得到说明信息。
        String command_1 = "curl ''localhost:9200''";

        // 二、基本概念
        // 下面的命令可以查看当前节点的所有 Index。
        String command_2 = "curl -X GET \"http://localhost:9200/_cat/indices?v\"";
        // 下面的命令可以列出每个 Index 所包含的 Type。
        String command_3 = "curl \"localhost:9200/_mapping?pretty=true\"";

        // 三、新建和删除 Index
        // 新建 Index，可以直接向 Elastic 服务器发出 PUT 请求。下面的例子是新建一个名叫weather的 Index
        String command_4 = "curl -X PUT \"localhost:9200/weather\"";
        // 然后，我们发出 DELETE 请求，删除这个 Index。
        String command_5 = "curl -X DELETE \"localhost:9200/weather\"";

        // 四、中文分词设置
        String command_6 = "curl -X PUT \"localhost:9200/accounts\" -H \"content-Type:application/json\"-d \"%s\"";

        //  五、数据操作
        String command_7 = "curl -X PUT \"localhost:9200/accounts/person/1\" -H \"content-Type:application/json\"-d \"%s\"";

        //exec(command_1);
        //exec(String.format(command_6,json));
        exec(String.format(command_7,jsonDto));
    }

    void exec(String command){
        System.out.println(command);
        CmdUtils.execCmd(command);
    }

}
