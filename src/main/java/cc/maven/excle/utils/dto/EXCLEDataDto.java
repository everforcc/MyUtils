package cc.maven.excle.utils.dto;

import lombok.Data;

/**
 * @author
 */
@Data
public class EXCLEDataDto {

//    private int columnWidth;
//    private int columnHeigh;

    // HSSFCell
    private int dataType;
    private String dataValue;

    private int r;
    private int g;
    private int b;

}
