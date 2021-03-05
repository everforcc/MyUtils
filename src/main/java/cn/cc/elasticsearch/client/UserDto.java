package cn.cc.elasticsearch.client;

import com.alibaba.fastjson.JSONObject;

/**
 * @author c.c.
 * @date 2021/3/1
 */
public class UserDto {

    String user;
    String post_date;
    String message;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDto(String user, String post_date, String message) {
        this.user = user;
        this.post_date = post_date;
        this.message = message;
    }

    public UserDto() {
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
