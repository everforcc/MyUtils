package cc.maven.excle.utils.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author
 */
@Data
public class EXCLEDto {

    // sheet名字
    //private String sheetName;
    private Map<String,ESheet> stringESheetMap;

    @Data
    public class ESheet{

        // 二选一
        // 行数，值
        private Map<Integer,List<ERow>> integerListMapX;
        // 列数，值
        private Map<Integer,List<ERow>> integerListMapY;

    }

    @Data
    public class ERow{

        private int width;
        private int heigh;
        private EXCLEDataDto excleDataDto;

    }

}
