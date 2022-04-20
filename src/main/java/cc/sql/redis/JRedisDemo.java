package cc.sql.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class JRedisDemo {

    // private static String host = "121.5.106.46";
    private static String host = "127.0.0.1";
    private static int port = 6379;
    private static String pas = "11111111";
    private static Jedis jedis;

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        login(host,port,pas);

//        setStr("key","value");
//        setList("abc",stringList);
        listKeys();
    }

    public static Jedis login(String host,int port,String pas){
        HostAndPort hostAndPort = new HostAndPort(host,port);
        jedis = new Jedis(hostAndPort);
        // 如果 Redis 服务设置了密码，需要下面这行，没有就不需要
        jedis.auth(pas);
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        return jedis;
    }

    public static void setStr(String key,String value){
        jedis.set(key, value);
        System.out.println("redis 存储的字符串为: "+ jedis.get(key));
    }

    public static void setList(String key, List<String> valueList){
        valueList.forEach(e->jedis.lpush(key, e));

        List<String> list = jedis.lrange(key, 0 ,valueList.size()-1);

        list.forEach(e -> System.out.println("从redis取出value值[%s]:" + e));
    }

    public  static void listKeys(){
        // 获取数据并输出
        Set<String> keys = jedis.keys("*");
        Iterator<String> it=keys.iterator() ;
        while(it.hasNext()){
            String key = it.next();
            System.out.println(key);
        }
    }

}
