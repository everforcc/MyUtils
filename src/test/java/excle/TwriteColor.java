package excle;

import cc.core.file.img.dto.ImgColorDto;
import cc.core.file.utils.PicReadColor;
import cc.maven.excle.utils.writecolor.XSSFWriteColorUtils;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

/**
 * @author everforcc 2021-09-30
 */
public class TwriteColor {

    @Test
    public void t1() throws Exception {
        String fileName = "lc-压缩002.jpg";
        PicReadColor rc = new PicReadColor();
//        x = rc.getScreenPixel();
//        System.out.println(x + " - ");
        Map<Integer,List<ImgColorDto>> integerListMap = rc.getImagePixel_2("C:\\test\\dir\\" + fileName);
        //imgColorDtoList = new ArrayList<>();
        //imgColorDtoList.add(new ImgColorDto(1,1,(byte)255,(byte)182,(byte)193));

//        for(ImgColorDto e:imgColorDtoList){
//            System.out.println(e);
//        }
        XSSFWriteColorUtils.defined(integerListMap,fileName + ".xlsx");
    }

    @Test
    public void t2() throws Exception {
        String fileName = "lc.jpg";
        PicReadColor rc = new PicReadColor();
//        x = rc.getScreenPixel();
//        System.out.println(x + " - ");
        Map<Integer,List<ImgColorDto>> integerListMap = rc.getImagePixel_2("C:\\test\\dir\\" + fileName);
        //imgColorDtoList = new ArrayList<>();
        //imgColorDtoList.add(new ImgColorDto(1,1,(byte)255,(byte)182,(byte)193));

//        for(ImgColorDto e:imgColorDtoList){
//            System.out.println(e);
//        }
        XSSFWriteColorUtils.defined(integerListMap,fileName + ".xlsx");
    }

}
