package cc.core.enume;

/**
 * @author everforcc 2021-09-01
 */
public enum ObjEnum {

    //public static String Manage = "1";

    DANCE_USER_TYPE("Manage","1");

    private String type;
    private String code;

    ObjEnum(String type, String code) {
        this.type = type;
        this.code = code;
    }

    public static String getValue(String type) {
        ObjEnum[] danceEnums = values();
        for (ObjEnum danceEnum : danceEnums) {
            if (danceEnum.type().equals(type)) {
                return danceEnum.code();
            }
        }
        return "3";
    }

    public String type() {
        return this.type;
    }

    public String code() {
        return this.code;
    }

    public static String Manage(){
        ObjEnum[] danceEnums = values();
        return danceEnums[0].code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
