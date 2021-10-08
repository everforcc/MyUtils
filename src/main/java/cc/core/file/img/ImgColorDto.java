package cc.core.file.img;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author everforcc 2021-09-30
 */
@Data
@AllArgsConstructor
public class ImgColorDto {

    public int x;
    public int y;
    public int r;
    public int g;
    public int b;

    @Override
    public String toString() {
        return "ImgColorDto{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", g=" + g +
                ", b=" + b +
                '}';
    }
}
