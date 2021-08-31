package cc.core.file.utils;

/**
 * @author c.c.
 * @date 2020/12/18
 */
public class NumParse {
    public static void main(String[] args) {
        chineseToNum(123456789);
    }

    private static String[] chineseNum = {"零","一","二","三","四","五","六","七","八","九"};
    private static String[] chineseUnit = {"","十","百","千","万","十","百","千","亿"};
    private static String[] numUpper = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};

    public static void chineseToNum(int num){
        String n = new String(num+"");
        char[] chars = n.toCharArray();
        int length = chars.length;
        for(int i=0;i<length;i++){
            System.out.print(chineseNum[chars[i]-48]);
            System.out.print(chineseUnit[length - i -1 ]);
            //System.out.println(chars[i]-48);
        }
        System.out.println();
        for(int i=0;i<length;i++){
            System.out.print(numUpper[chars[i]-48]);
            System.out.print(chineseUnit[length - i -1 ]);
            //System.out.println(chars[i]-48);
        }
    }

}
