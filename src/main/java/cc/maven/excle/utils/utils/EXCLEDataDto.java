package cc.maven.excle.utils.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author
 */
@Data
@AllArgsConstructor
public class EXCLEDataDto {

//    private int columnWidth;
//    private int columnHeigh;

    // HSSFCell
    private int dataType;
    private String dataValue;

    private int r;
    private int g;
    private int b;

    public EXCLEDataDto(String dataValue) {
        this.dataValue = dataValue;
    }

    public EXCLEDataDto(String dataValue,int[] rgbAry) {
        this.dataValue = dataValue;
        r = rgbAry[0];
        g = rgbAry[1];
        b = rgbAry[2];
    }

    public EXCLEDataDto() {
    }
}
