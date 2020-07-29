package cn.cc.entity;

import java.util.Date;

/**
 * 常见数据类型
 */
public class ObjectField {

    private Byte aByte;

    private Short aShort;

    private Integer aInteger;

    private Long aLong;

    private Float aFloat;

    private Double aDouble;

    private Character aCharacter;

    private Boolean aBoolean;

    private String aString;

    private Date aDate;

    public ObjectField() {
    }

    public ObjectField(Byte aByte, Short aShort, Integer aInteger, Long aLong, Float aFloat, Double aDouble, Character aCharacter, Boolean aBoolean, String aString, Date aDate) {
        this.aByte = aByte;
        this.aShort = aShort;
        this.aInteger = aInteger;
        this.aLong = aLong;
        this.aFloat = aFloat;
        this.aDouble = aDouble;
        this.aCharacter = aCharacter;
        this.aBoolean = aBoolean;
        this.aString = aString;
        this.aDate = aDate;
    }

    public Byte getaByte() {
        return aByte;
    }

    public void setaByte(Byte aByte) {
        this.aByte = aByte;
    }

    public Short getaShort() {
        return aShort;
    }

    public void setaShort(Short aShort) {
        this.aShort = aShort;
    }

    public Integer getaInteger() {
        return aInteger;
    }

    public void setaInteger(Integer aInteger) {
        this.aInteger = aInteger;
    }

    public Long getaLong() {
        return aLong;
    }

    public void setaLong(Long aLong) {
        this.aLong = aLong;
    }

    public Float getaFloat() {
        return aFloat;
    }

    public void setaFloat(Float aFloat) {
        this.aFloat = aFloat;
    }

    public Double getaDouble() {
        return aDouble;
    }

    public void setaDouble(Double aDouble) {
        this.aDouble = aDouble;
    }

    public Character getaCharacter() {
        return aCharacter;
    }

    public void setaCharacter(Character aCharacter) {
        this.aCharacter = aCharacter;
    }

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public String getaString() {
        return aString;
    }

    public void setaString(String aString) {
        this.aString = aString;
    }

    public Date getaDate() {
        return aDate;
    }

    public void setaDate(Date aDate) {
        this.aDate = aDate;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
