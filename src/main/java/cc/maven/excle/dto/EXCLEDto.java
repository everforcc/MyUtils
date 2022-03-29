package cc.maven.excle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author
 */
@Data
@NoArgsConstructor
public class EXCLEDto {

    // sheet名字
    //private String sheetName;
    private Map<String,ESheet> stringESheetMap;

    @Data
    @NoArgsConstructor
    public static class ESheet{

        // 二选一
        // 行数，值
        private Map<Integer,List<ERow>> integerListMapX;
        // 列数，值
        private Map<Integer,List<ERow>> integerListMapY;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ERow{

        private int width;
        private int heigh;
        private EXCLEDataDto excleDataDto;

        public ERow(EXCLEDataDto excleDataDto) {
            this.excleDataDto = excleDataDto;
        }
    }

}
