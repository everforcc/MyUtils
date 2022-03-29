package cc.sqlno.redis;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

public class RedisMQ {

    private static final String TOPIC = "runoobChat";
    private final JedisPool jedisPool;

    public RedisMQ(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 发布消息
     *
     * @param topic
     * @param messge
     */
    public void publish(String topic, String messge) {
        Jedis jedis = null;
        if (StringUtils.isBlank(topic)) {
            topic = TOPIC;
        }

        try {
            jedis = jedisPool.getResource();
            jedis.publish(topic, messge);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    /**
     * 订阅消息
     *
     * @param topic
     * @param jedisPubSub
     */
    public void subscribe(String topic, JedisPubSub jedisPubSub) {
        Jedis jedis = null;
        if (StringUtils.isBlank(topic)) {
            topic = TOPIC;
        }
        try {
            jedis = jedisPool.getResource();
            jedis.subscribe(jedisPubSub, topic);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public static class MyjedisPubSub extends JedisPubSub {
        @Override
        public void onMessage(String channel, String message) {
            System.out.println("-------channel is " + channel + " message is " + message);
        }
    }

    /*public static void main(String[] args) {
        //默认连接本地redis,
        // loclhost:6379
        JedisPool jedisPool = new JedisPool("127.0.0.1",6379,"root","11111111");

        RedisMQ publish = new RedisMQ(jedisPool);
        new Thread(new Runnable() {
            @Override
            public void run() {
                publish.subscribe("PID", new MyjedisPubSub());
            }
        }).start();
        for (int i = 0; i < 100; i++) {
            publish.publish("PID", "messge" + i);
        }
    }*/

    public static void main(String[] args) {

        Jedis jedis = JRedisDemo.login("127.0.0.1",6379,"11111111");

        try {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                jedis.subscribe(new MyjedisPubSub(), TOPIC);
            }
        }).start();

    }

}
