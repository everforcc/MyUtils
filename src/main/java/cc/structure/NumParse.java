package cc.structure;

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

    /**
     * 数字金额大写转换，思想先写个完整的然后将如零拾替换成零
     * 要用到正则表达式
     */
    public static String digitUppercase(double n){
        String fraction[] = {"角", "分"};
        String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        String unit[][] = {{"元", "万", "亿"},
                {"", "拾", "佰", "仟"}};

        String head = n < 0? "负": "";
        n = Math.abs(n);

        String s = "";
        for (int i = 0; i < fraction.length; i++) {
            s += (digit[(int)(Math.floor(n * 10 * Math.pow(10, i)+0.00000001) % 10)] + fraction[i]).replaceAll("(零.)+", "");
        }
        if(s.length()<1){
            s = "整";
        }
        int integerPart = (int)Math.floor(n);

        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
            String p ="";
            for (int j = 0; j < unit[1].length && n > 0; j++) {
                p = digit[integerPart%10]+unit[1][j] + p;
                integerPart = integerPart/10;
            }
            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
        }
        return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
    }

    /**
     * 解析money
     * @param money
     * @param splitStr
     * @return
     */
    public static String parseMoney(String money,String splitStr){
        String result = "";
        String[] splitStrs ={"万","仟","佰","拾","元","角","分"};
        money = money.replace("零","");
        for(int i = 0;i<splitStrs.length;i++){
            if(money.indexOf(splitStrs[i]) > -1){
                result = money.substring(0,money.indexOf(splitStrs[i]));
                money = money.substring(money.indexOf(splitStrs[i])+1,money.length());
                if(splitStrs[i].equals(splitStr)){
                    if("".equals(result)){
                        return "零";
                    }else {
                        return result;
                    }
                }else{
                    result = "零";
                }
            }
        }
        return result;
    }

}
