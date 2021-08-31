package cc.core.string.use;

import cc.core.string.utils.ConstantString;
import cc.core.string.utils.StringUtils;

/**
 * @author c.c.
 * @date 2020/12/10
 */
public class StringUse {
    public static void main(String[] args) {
        // distinct();
        fixStr();
    }

    private static void distinct(){
        String s="UhFOUNO1mCHrmC61m1UgpPEfcuzaVP67JuaRVP6RDuA4Vh7ipPmRNvIaSuEfJOt-UhMnUcDaXg6RpPtaUu6RpPtaUuAeScMaJufGSPDfX17GpPMeVhA2VhFOUg71VdL1mCHrJs0tJsHsJs6RSNHwmNFaSg5wUhHzmu51UNaamBygSu_aUPHvmCIBSsMxmdLsmBZsUCmvS1OOmgIfJN2w_gHGSgUf_4t4SP5E_h2tmsU4mhmqXhziIgU-VhAxUPzBXifrDCOrIgU-VhAxVt7qSPtfKNmzJiyqU1UAXgE5UPFeNhfqUNO1mCezJN51JNmrHCHO8B0r8B0rIiMRpuAqKc2rpCfHAPadXgGl8PZO2Ner_5aHpvF1d5FdAP_ypOak";
        System.out.println(StringUtils.strDistinct(s));
        //System.out.println(StringUtils.strDistinct(s).length());
    }

    public static void testT(){
        // 打印九九乘法表
        for(int i = 1; i <= 9; i++) {
            for(int j = 1; j <= i; j++) {
                System.out.print(i+"*"+j+"="+i*j+ ConstantString.autoComplete) ;
            }
            System.out.println();
        }

    }

    static void fixStr(){
        String str="34F,Shanghai World Financial Center";
        int maxLength = 70;
        String result = StringUtils.subBytes(str,maxLength);
        System.out.println(StringUtils.strLength(str));
        System.out.println(result+";");

        String str1="34F,Shanghai World Financial Center,100 Century Avenue,Shanghai,China";
        int str2=70;
        String result1 = StringUtils.subBytes(str1,str2);
        System.out.println(StringUtils.strLength(str1));
        System.out.println(result1+";");
        byte a= 'A';
        System.out.println();
        System.out.println(new Integer(a));
        System.out.println(a-64);
    }

}
