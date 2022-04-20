package excle;

import cc.core.date.utils.DateUtils;
import cc.core.file.img.dto.ImgColorDto;
import cc.core.file.utils.PicReadColor;
import cc.maven.excle.utils.writecolor.XSSFWriteColorUtils;
import cc.sysconstant.ConstantFile;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

/**
 * @author everforcc 2021-09-30
 */
public class TwriteColor {

    @Test
    public void t1() throws Exception {
        String fileName = ConstantFile.L1_javaFilePath + ConstantFile.L2_excle + "/uzi死亡分布图-1.jpg";
        PicReadColor rc = new PicReadColor();
//        x = rc.getScreenPixel();
//        System.out.println(x + " - ");
        long readBefore = DateUtils.time();
        Map<Integer,List<ImgColorDto>> integerListMap =
                rc.getImagePixel_2(fileName);
        long readEnd = DateUtils.time();
        System.out.println("使用时间: " + DateUtils.timedif(readBefore,readEnd));
        //imgColorDtoList = new ArrayList<>();
        //imgColorDtoList.add(new ImgColorDto(1,1,(byte)255,(byte)182,(byte)193));

//        for(ImgColorDto e:imgColorDtoList){
//            System.out.println(e);
//        }
        long writeBefore = DateUtils.time();
        XSSFWriteColorUtils.defined(integerListMap,fileName + ".xlsx");
        long writeEnd = DateUtils.time();
        System.out.println("使用时间: " + DateUtils.timedif(writeBefore,writeEnd));
    }

    @Test
    public void t2() throws Exception {
        String fileName = "lc.jpg";
        PicReadColor rc = new PicReadColor();
//        x = rc.getScreenPixel();
//        System.out.println(x + " - ");
        Map<Integer,List<ImgColorDto>> integerListMap =
                rc.getImagePixel_2(ConstantFile.L1_javaFilePath + ConstantFile.L2_excle + fileName);
        //imgColorDtoList = new ArrayList<>();
        //imgColorDtoList.add(new ImgColorDto(1,1,(byte)255,(byte)182,(byte)193));

//        for(ImgColorDto e:imgColorDtoList){
//            System.out.println(e);
//        }
        XSSFWriteColorUtils.defined(integerListMap,fileName + ".xlsx");
    }

}
