package cc.core.file.img;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guokailong 2021-09-30
 */
@Data
@AllArgsConstructor
public class ColorRGB {

    private String en;
    private String zh;
    private String hex;
    private String rgb;

    private static List<ColorRGB> colorRGBList = new ArrayList<>();
    private static Map<String,ColorRGB> stringColorRGBMap = new HashMap<>();

    static {
        /**
         * 处理数据用到的正则
         * 处理excle最后一列
         * ,"\s{1,}(\d{1,3},\d{1,3},\d{1,3})\s{0,}"
         * 处理两个数据之间的空格
         * "\s{1,},
         */
        colorRGBList.add(new ColorRGB("LightPink","浅粉色","#FFB6C1","255,182,193"));
        colorRGBList.add(new ColorRGB("Pink","粉红","#FFC0CB","255,192,203"));
        colorRGBList.add(new ColorRGB("Crimson","猩红","#DC143C","220,20,60"));
        colorRGBList.add(new ColorRGB("LavenderBlush","脸红的淡紫色","#FFF0F5","255,240,245"));
        colorRGBList.add(new ColorRGB("PaleVioletRed","苍白的紫罗兰红色","#DB7093","219,112,147"));
        colorRGBList.add(new ColorRGB("HotPink","热情的粉红","#FF69B4","255,105,180"));
        colorRGBList.add(new ColorRGB("DeepPink","深粉色","#FF1493","255,20,147"));
        colorRGBList.add(new ColorRGB("MediumVioletRed","适中的紫罗兰红色","#C71585","199,21,133"));
        colorRGBList.add(new ColorRGB("Orchid","兰花的紫色","#DA70D6","218,112,214"));
        colorRGBList.add(new ColorRGB("Thistle","蓟","#D8BFD8","216,191,216"));
        colorRGBList.add(new ColorRGB("plum","李子","#DDA0DD","221,160,221"));
        colorRGBList.add(new ColorRGB("Violet","紫罗兰","#EE82EE","238,130,238"));
        colorRGBList.add(new ColorRGB("Magenta","洋红","#FF00FF","255,0,255"));
        colorRGBList.add(new ColorRGB("Fuchsia","灯笼海棠(紫红色)","#FF00FF","255,0,255"));
        colorRGBList.add(new ColorRGB("DarkMagenta","深洋红色","#8B008B","139,0,139"));
        colorRGBList.add(new ColorRGB("Purple","紫色","#800080","128,0,128"));
        colorRGBList.add(new ColorRGB("MediumOrchid","适中的兰花紫","#BA55D3","186,85,211"));
        colorRGBList.add(new ColorRGB("DarkVoilet","深紫罗兰色","#9400D3","148,0,211"));
        colorRGBList.add(new ColorRGB("DarkOrchid","深兰花紫","#9932CC","153,50,204"));
        colorRGBList.add(new ColorRGB("Indigo","靛青","#4B0082","75,0,130"));
        colorRGBList.add(new ColorRGB("BlueViolet","深紫罗兰的蓝色","#8A2BE2","138,43,226"));
        colorRGBList.add(new ColorRGB("MediumPurple","适中的紫色","#9370DB","147,112,219"));
        colorRGBList.add(new ColorRGB("MediumSlateBlue","适中的板岩暗蓝灰色","#7B68EE","123,104,238"));
        colorRGBList.add(new ColorRGB("SlateBlue","板岩暗蓝灰色","#6A5ACD","106,90,205"));
        colorRGBList.add(new ColorRGB("DarkSlateBlue","深岩暗蓝灰色","#483D8B","72,61,139"));
        colorRGBList.add(new ColorRGB("Lavender","薰衣草花的淡紫色","#E6E6FA","230,230,250"));
        colorRGBList.add(new ColorRGB("GhostWhite","幽灵的白色","#F8F8FF","248,248,255"));
        colorRGBList.add(new ColorRGB("Blue","纯蓝","#0000FF","0,0,255"));
        colorRGBList.add(new ColorRGB("MediumBlue","适中的蓝色","#0000CD","0,0,205"));
        colorRGBList.add(new ColorRGB("MidnightBlue","午夜的蓝色","#191970","25,25,112"));
        colorRGBList.add(new ColorRGB("DarkBlue","深蓝色","#00008B","0,0,139"));
        colorRGBList.add(new ColorRGB("Navy","海军蓝","#000080","0,0,128"));
        colorRGBList.add(new ColorRGB("RoyalBlue","皇军蓝","#4169E1","65,105,225"));
        colorRGBList.add(new ColorRGB("CornflowerBlue","矢车菊的蓝色","#6495ED","100,149,237"));
        colorRGBList.add(new ColorRGB("LightSteelBlue","淡钢蓝","#B0C4DE","176,196,222"));
        colorRGBList.add(new ColorRGB("LightSlateGray","浅石板灰","#778899","119,136,153"));
        colorRGBList.add(new ColorRGB("SlateGray","石板灰","#708090","112,128,144"));
        colorRGBList.add(new ColorRGB("DoderBlue","道奇蓝","#1E90FF","30,144,255"));
        colorRGBList.add(new ColorRGB("AliceBlue","爱丽丝蓝","#F0F8FF","240,248,255"));
        colorRGBList.add(new ColorRGB("SteelBlue","钢蓝","#4682B4","70,130,180"));
        colorRGBList.add(new ColorRGB("LightSkyBlue","淡蓝色","#87CEFA","135,206,250"));
        colorRGBList.add(new ColorRGB("SkyBlue","天蓝色","#87CEEB","135,206,235"));
        colorRGBList.add(new ColorRGB("DeepSkyBlue","深天蓝","#00BFFF","0,191,255"));
        colorRGBList.add(new ColorRGB("LightBLue","淡蓝","#ADD8E6","173,216,230"));
        colorRGBList.add(new ColorRGB("PowDerBlue","火药蓝","#B0E0E6","176,224,230"));
        colorRGBList.add(new ColorRGB("CadetBlue","军校蓝","#5F9EA0","95,158,160"));
        colorRGBList.add(new ColorRGB("Azure","蔚蓝色","#F0FFFF","240,255,255"));
        colorRGBList.add(new ColorRGB("LightCyan","淡青色","#E1FFFF","225,255,255"));
        colorRGBList.add(new ColorRGB("PaleTurquoise","苍白的绿宝石","#AFEEEE","175,238,238"));
        colorRGBList.add(new ColorRGB("Cyan","青色","#00FFFF","0,255,255"));
        colorRGBList.add(new ColorRGB("Aqua","水绿色","#00FFFF","0,255,255"));
        colorRGBList.add(new ColorRGB("DarkTurquoise","深绿宝石","#00CED1","0,206,209"));
        colorRGBList.add(new ColorRGB("DarkSlateGray","深石板灰","#2F4F4F","47,79,79"));
        colorRGBList.add(new ColorRGB("DarkCyan","深青色","#008B8B","0,139,139"));
        colorRGBList.add(new ColorRGB("Teal","水鸭色","#008080","0,128,128"));
        colorRGBList.add(new ColorRGB("MediumTurquoise","适中的绿宝石","#48D1CC","72,209,204"));
        colorRGBList.add(new ColorRGB("LightSeaGreen","浅海洋绿","#20B2AA","32,178,170"));
        colorRGBList.add(new ColorRGB("Turquoise","绿宝石","#40E0D0","64,224,208"));
        colorRGBList.add(new ColorRGB("Auqamarin","绿玉碧绿色","#7FFFAA","127,255,170"));
        colorRGBList.add(new ColorRGB("MediumAquamarine","适中的碧绿色","#00FA9A","0,250,154"));
        colorRGBList.add(new ColorRGB("MediumSpringGreen","适中的春天的绿色","#F5FFFA","245,255,250"));
        colorRGBList.add(new ColorRGB("MintCream","薄荷奶油","#00FF7F","0,255,127"));
        colorRGBList.add(new ColorRGB("SpringGreen","春天的绿色","#3CB371","60,179,113"));
        colorRGBList.add(new ColorRGB("SeaGreen","海洋绿","#2E8B57","46,139,87"));
        colorRGBList.add(new ColorRGB("Honeydew","蜂蜜","#F0FFF0","240,255,240"));
        colorRGBList.add(new ColorRGB("LightGreen","淡绿色","#90EE90","144,238,144"));
        colorRGBList.add(new ColorRGB("PaleGreen","苍白的绿色","#98FB98","152,251,152"));
        colorRGBList.add(new ColorRGB("DarkSeaGreen","深海洋绿","#8FBC8F","143,188,143"));
        colorRGBList.add(new ColorRGB("LimeGreen","酸橙绿","#32CD32","50,205,50"));
        colorRGBList.add(new ColorRGB("Lime","酸橙色","#00FF00","0,255,0"));
        colorRGBList.add(new ColorRGB("ForestGreen","森林绿","#228B22","34,139,34"));
        colorRGBList.add(new ColorRGB("Green","纯绿","#008000","0,128,0"));
        colorRGBList.add(new ColorRGB("DarkGreen","深绿色","#006400","0,100,0"));
        colorRGBList.add(new ColorRGB("Chartreuse","查特酒绿","#7FFF00","127,255,0"));
        colorRGBList.add(new ColorRGB("LawnGreen","草坪绿","#7CFC00","124,252,0"));
        colorRGBList.add(new ColorRGB("GreenYellow","绿黄色","#ADFF2F","173,255,47"));
        colorRGBList.add(new ColorRGB("OliveDrab","橄榄土褐色","#556B2F","85,107,47"));
        colorRGBList.add(new ColorRGB("Beige","米色(浅褐色)","#6B8E23","107,142,35"));
        colorRGBList.add(new ColorRGB("LightGoldenrodYellow","浅秋麒麟黄","#FAFAD2","250,250,210"));
        colorRGBList.add(new ColorRGB("Ivory","象牙","#FFFFF0","255,255,240"));
        colorRGBList.add(new ColorRGB("LightYellow","浅黄色","#FFFFE0","255,255,224"));
        colorRGBList.add(new ColorRGB("Yellow","纯黄","#FFFF00","255,255,0"));
        colorRGBList.add(new ColorRGB("Olive","橄榄","#808000","128,128,0"));
        colorRGBList.add(new ColorRGB("DarkKhaki","深卡其布","#BDB76B","189,183,107"));
        colorRGBList.add(new ColorRGB("LemonChiffon","柠檬薄纱","#FFFACD","255,250,205"));
        colorRGBList.add(new ColorRGB("PaleGodenrod","灰秋麒麟","#EEE8AA","238,232,170"));
        colorRGBList.add(new ColorRGB("Khaki","卡其布","#F0E68C","240,230,140"));
        colorRGBList.add(new ColorRGB("Gold","金","#FFD700","255,215,0"));
        colorRGBList.add(new ColorRGB("Cornislk","玉米色","#FFF8DC","255,248,220"));
        colorRGBList.add(new ColorRGB("GoldEnrod","秋麒麟","#DAA520","218,165,32"));
        colorRGBList.add(new ColorRGB("FloralWhite","花的白色","#FFFAF0","255,250,240"));
        colorRGBList.add(new ColorRGB("OldLace","老饰带","#FDF5E6","253,245,230"));
        colorRGBList.add(new ColorRGB("Wheat","小麦色","#F5DEB3","245,222,179"));
        colorRGBList.add(new ColorRGB("Moccasin","鹿皮鞋","#FFE4B5","255,228,181"));
        colorRGBList.add(new ColorRGB("Orange","橙色","#FFA500","255,165,0"));
        colorRGBList.add(new ColorRGB("PapayaWhip","番木瓜","#FFEFD5","255,239,213"));
        colorRGBList.add(new ColorRGB("BlanchedAlmond","漂白的杏仁","#FFEBCD","255,235,205"));
        colorRGBList.add(new ColorRGB("NavajoWhite","Navajo白","#FFDEAD","255,222,173"));
        colorRGBList.add(new ColorRGB("AntiqueWhite","古代的白色","#FAEBD7","250,235,215"));
        colorRGBList.add(new ColorRGB("Tan","晒黑","#D2B48C","210,180,140"));
        colorRGBList.add(new ColorRGB("BrulyWood","结实的树","#DEB887","222,184,135"));
        colorRGBList.add(new ColorRGB("Bisque","(浓汤)乳脂,番茄等","#FFE4C4","255,228,196"));
        colorRGBList.add(new ColorRGB("DarkOrange","深橙色","#FF8C00","255,140,0"));
        colorRGBList.add(new ColorRGB("Linen","亚麻布","#FAF0E6","250,240,230"));
        colorRGBList.add(new ColorRGB("Peru","秘鲁","#CD853F","205,133,63"));
        colorRGBList.add(new ColorRGB("PeachPuff","桃色","#FFDAB9","255,218,185"));
        colorRGBList.add(new ColorRGB("SandyBrown","沙棕色","#F4A460","244,164,96"));
        colorRGBList.add(new ColorRGB("Chocolate","巧克力","#D2691E","210,105,30"));
        colorRGBList.add(new ColorRGB("SaddleBrown","马鞍棕色","#8B4513","139,69,19"));
        colorRGBList.add(new ColorRGB("SeaShell","海贝壳","#FFF5EE","255,245,238"));
        colorRGBList.add(new ColorRGB("Sienna","黄土赭色","#A0522D","160,82,45"));
        colorRGBList.add(new ColorRGB("LightSalmon","浅鲜肉(鲑鱼)色","#FFA07A","255,160,122"));
        colorRGBList.add(new ColorRGB("Coral","珊瑚","#FF7F50","255,127,80"));
        colorRGBList.add(new ColorRGB("OrangeRed","橙红色","#FF4500","255,69,0"));
        colorRGBList.add(new ColorRGB("DarkSalmon","深鲜肉(鲑鱼)色","#E9967A","233,150,122"));
        colorRGBList.add(new ColorRGB("Tomato","番茄","#FF6347","255,99,71"));
        colorRGBList.add(new ColorRGB("MistyRose","薄雾玫瑰","#FFE4E1","255,228,225"));
        colorRGBList.add(new ColorRGB("Salmon","鲜肉(鲑鱼)色","#FA8072","250,128,114"));
        colorRGBList.add(new ColorRGB("Snow","雪","#FFFAFA","255,250,250"));
        colorRGBList.add(new ColorRGB("LightCoral","淡珊瑚色","#F08080","240,128,128"));
        colorRGBList.add(new ColorRGB("RosyBrown","玫瑰棕色","#BC8F8F","188,143,143"));
        colorRGBList.add(new ColorRGB("IndianRed","印度红","#CD5C5C","205,92,92"));
        colorRGBList.add(new ColorRGB("Red","纯红","#FF0000","255,0,0"));
        colorRGBList.add(new ColorRGB("Brown","棕色","#A52A2A","165,42,42"));
        colorRGBList.add(new ColorRGB("FireBrick","耐火砖","#B22222","178,34,34"));
        colorRGBList.add(new ColorRGB("DarkRed","深红色","#8B0000","139,0,0"));
        colorRGBList.add(new ColorRGB("Maroon","栗色","#800000","128,0,0"));
        colorRGBList.add(new ColorRGB("White","纯白","#FFFFFF","255,255,255"));
        colorRGBList.add(new ColorRGB("WhiteSmoke","白烟","#F5F5F5","245,245,245"));
        colorRGBList.add(new ColorRGB("Gainsboro","Gainsboro","#DCDCDC","220,220,220"));
        colorRGBList.add(new ColorRGB("LightGrey","浅灰色","#D3D3D3","211,211,211"));
        colorRGBList.add(new ColorRGB("Silver","银白色","#C0C0C0","192,192,192"));
        colorRGBList.add(new ColorRGB("DarkGray","深灰色","#A9A9A9","169,169,169"));
        colorRGBList.add(new ColorRGB("Gray","灰色","#808080","128,128,128"));
        colorRGBList.add(new ColorRGB("DimGray","暗淡的灰色","#696969","105,105,105"));
        colorRGBList.add(new ColorRGB("Black","纯黑","#000000","0,0,0"));
    }

    static {
        colorRGBList.forEach(e ->stringColorRGBMap.put(e.getRgb(),e));
    }

    public static List<ColorRGB> getThisList(){
        return colorRGBList;
    }

    public static ColorRGB findColorRGBByRGB(String rgb){
        return stringColorRGBMap.get(rgb);
    }

}
