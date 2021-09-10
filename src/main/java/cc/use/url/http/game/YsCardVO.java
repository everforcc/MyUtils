package cc.use.url.http.game;

import lombok.Data;

import java.util.Objects;

/**
 * @author guokailong 2021-09-10
 */
@Data
public class YsCardVO {

    private String uid;
    private String gacha_type;
    private String item_id;
    private String count;
    private String time;
    private String name;
    private String lang;
    private String item_type;
    private String rank_type;
    private String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YsCardVO ysCardVO = (YsCardVO) o;
        return uid.equals(ysCardVO.uid) && id.equals(ysCardVO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, id);
    }

    @Override
    public String toString() {
        return "YsCardVO{" +
                "uid='" + uid + '\'' +
                ", gacha_type='" + gacha_type + '\'' +
                ", item_id='" + item_id + '\'' +
                ", count='" + count + '\'' +
                ", time='" + time + '\'' +
                ", name='" + name + '\'' +
                ", lang='" + lang + '\'' +
                ", item_type='" + item_type + '\'' +
                ", rank_type='" + rank_type + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
