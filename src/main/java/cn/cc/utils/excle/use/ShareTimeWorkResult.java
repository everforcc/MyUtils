package cn.cc.utils.excle.use;

import cn.cc.utils.excle.utils.ExcleValueHelper;
import cn.cc.utils.excle.utils.LoadExcle;
import cn.cc.utils.fileio.io.InputStream_IO;
import org.junit.jupiter.api.Test;

import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class ShareTimeWorkResult {
    //  费用分摊工时表，写段代码用代码生成
    // sheet1 分为三块    1.人力成本(左上)  2.九大渠道(左下) 3.右

    /* 当前月份 */
    public static final String yearMonth = "202009";
    // 要处理的文件
    public static final String fileName = "F:\\1.中科软\\2.费用分摊\\2020年末总结\\1.工时汇总2020年末测试.xlsx" ;
    // 输出文件路径
    public static final String filePath = "E:"+File.separator+"linshi"+File.separator+"share"+File.separator+"01"+File.separator ;
    // 表示每块区域的坐标
    // public static final String[] sheet1_HO={"C","S","2","14"};

    /*
     * 1. sheet1的左上和左下，SU SZ 的MKT替换为 SU 和SZ 。
     *
     * */
    public static void main(String[] args) {
        // 渠道匹配问题
        // Non JIA
        //Branch Internet
        //Treaty Non Motor
        //IBG
        //Treaty Motor
        //PICC
        //Direct Motor
        //BD
        //JIA
        // 1. sheet1 需要替换 SU 和 SZ 随后解决
        /*final String[] sheet1_HO_channel={"C","S","2","14","HO"};
        final String[] sheet1_SMD_channel={"C","I","18","30","SMD"};
        final String[] sheet1_GD_channel={"C","I","34","46","GD"};
        final String[] sheet1_SZ_channel={"C","D","50","62","SZ"};
        final String[] sheet1_BJ_channel={"C","I","66","78","BJ"};
        final String[] sheet1_JS_channel={"C","I","82","94","JS"};
        final String[] sheet1_SU_channel={"C","D","98","110","SU"};
        sheet1_channel(sheet1_HO_channel);
        sheet1_channel(sheet1_SMD_channel);
        sheet1_channel(sheet1_GD_channel);
        sheet1_channel(sheet1_SZ_channel);
        sheet1_channel(sheet1_BJ_channel);
        sheet1_channel(sheet1_JS_channel);
        sheet1_channel(sheet1_SU_channel);
        // 2. sheet1 左上
        final String[] sheet1_HO_cost={"C","S","2","5","HO"};
        final String[] sheet1_SMD_cost={"C","I","18","21","SMD"};
        final String[] sheet1_GD_cost={"C","I","34","37","GD"};
        final String[] sheet1_SZ_cost={"C","D","50","53","SZ"};
        final String[] sheet1_BJ_cost={"C","I","66","69","BJ"};
        final String[] sheet1_JS_cost={"C","I","82","85","JS"};
        final String[] sheet1_SU_cost={"C","D","98","101","SU"};
        sheet1_cost(sheet1_HO_cost);
        sheet1_cost(sheet1_SMD_cost);
        sheet1_cost(sheet1_GD_cost);
        sheet1_cost(sheet1_SZ_cost);
        sheet1_cost(sheet1_BJ_cost);
        sheet1_cost(sheet1_JS_cost);
        sheet1_cost(sheet1_SU_cost);
        //3. sheet1 右边
        final String[] sheet1_HO_asset={"C","W","6","14","HO"};
        final String[] sheet1_SMD_asset={"C","M","22","30","SMD"};
        final String[] sheet1_GD_asset={"C","M","38","46","GD"};
        final String[] sheet1_SZ_asset={"C","G","54","62","SZ"};
        final String[] sheet1_BJ_asset={"C","M","70","78","BJ"};
        final String[] sheet1_JS_asset={"C","M","86","94","JS"};
        final String[] sheet1_SU_asset={"C","G","102","110","SU"};
        sheet1_asset(sheet1_HO_asset);
        sheet1_asset(sheet1_SMD_asset);
        sheet1_asset(sheet1_GD_asset);
        sheet1_asset(sheet1_SZ_asset);
        sheet1_asset(sheet1_BJ_asset);
        sheet1_asset(sheet1_JS_asset);
        sheet1_asset(sheet1_SU_asset);*/

        // sheet2
        /*sheet2();*/

        // sheet4  HO可能要更具SMD（SD）设置
        final String[] sheet4_JIA={"A","L","3","9","1"};
        //sheet4(sheet4_JIA);
        final String[] sheet4_NonJIA={"M","Y","3","9","0"};
        sheet4(sheet4_NonJIA);

        //System.out.println(formatToNumber(new BigDecimal(1)));

    }


    /**
     * excle每个块的左下角
     * @param sheet1_
     */
    public static void sheet1_channel(String[] sheet1_){

        // StringBuffer sql = new StringBuffer("delete from Accsh_Depsorttochannel  where yearmonth='" + yearMonth + "'; \r\n");
        StringBuffer sql = new StringBuffer("-- " + sheet1_[4] + " \r\n");
        // 起始坐标的位置 和长度 ，但其实换成xyz也不错
        LoadExcle loadExcle = new LoadExcle( fileName ,sheet1_[0],sheet1_[1],Integer.valueOf(sheet1_[2]),Integer.valueOf(sheet1_[3]));
        ExcleValueHelper excleValueHelper = loadExcle.flow();

        excleValueHelper.setCellValue(Integer.valueOf(sheet1_[2])-1,ExcleValueHelper.byteToInt(sheet1_[1]),"ZBSY");

        int realStartY = Integer.valueOf(sheet1_[2]) + 3;
        int realEndY = Integer.valueOf(sheet1_[3]);
        // 两层遍历 x外层，因为一列为一个整体
        // X 便利实际 数字 数据，坐标，只需要取出来即可
        for(int x = ExcleValueHelper.byteToInt(sheet1_[0]) ; x <= ExcleValueHelper.byteToInt(sheet1_[1]) ; x++) {

            // 四舍五入以及，和为1
            BigDecimal ary[] = new BigDecimal[ realEndY - realStartY];
            int i = 0;
            BigDecimal total = new BigDecimal(0);
            BigDecimal max = new BigDecimal(0);
            int maxIndex = -1;
            if(x != ExcleValueHelper.byteToInt(sheet1_[1])) {
                // y
                for (int y = realStartY; y < realEndY; y++) {
                    // 算出最大值，求和，记录坐标
                    ary[i] = new BigDecimal(formatToNumber(new BigDecimal(excleValueHelper.getCellValue(y, x))));
                    if (ary[i].compareTo(max) == 1) {
                        maxIndex = i;
                        max = ary[i];
                    }
                    total = total.add(ary[i]);
                    i++;
                }
                // 一列结束，生成sql 最大值
                ary[maxIndex] = ary[maxIndex].subtract(total.subtract(new BigDecimal(1)));
            }else {
                for (int y = realStartY; y < realEndY; y++) {
                    // NBSY
                    if (excleValueHelper.getCellValue(i + 3 + Integer.valueOf(sheet1_[2]), 2).equals("Branch Non JIA")) {
                        ary[i] = new BigDecimal(formatToNumber(new BigDecimal(1)));
                    } else {
                        ary[i] = new BigDecimal(formatToNumber(new BigDecimal(0)));
                    }
                    i++;
                }
            }
            // 公共的io方法
            BigDecimal all =  new BigDecimal(0);
            String sort="";
            for(int resultIndex=0;resultIndex<ary.length;resultIndex++){
                // println(ary[resultIndex]);
                // all = all.add(ary[resultIndex]);
                // 具体每行sql
                // 拼接sql
                if("SU".equals(sheet1_[4])||"SZ".equals(sheet1_[4])){
                    sort = sheet1_[4];
                }else {
                    sort = excleValueHelper.getCellValue(Integer.valueOf(sheet1_[2])-1,x);
                }

                sql.append("insert into Accsh_Depsorttochannel values ('" + yearMonth + "', 'FYFT', 'C001', '" + sheet1_[4] + "', '" +
                        sort + "', '" +
                        excleValueHelper.getCellValue(resultIndex + 3 + Integer.valueOf(sheet1_[2]),2) + "', " + ary[resultIndex] + ");");
                sql.append("\r\n");
            }

        }
        // sql.append("");
        System.out.println(sql);

        /**
         * 业务问题， 最后替换JIA和Non JIA
         */

        //InputStream_IO.IO_PrintWriter(new File(filePath + "sheet1_channel.sql"),sqlReplaceKey(sql));

    }

    // {"C","S","2","5","HO"};
    /* 左上 人力成本 */
    public static void sheet1_cost(String[] sheet1_){

        StringBuffer sql = new StringBuffer("-- " + sheet1_[4] + " \r\n");
        // 读取excle
        LoadExcle loadExcle = new LoadExcle( fileName ,sheet1_[0],sheet1_[1],Integer.valueOf(sheet1_[2]),Integer.valueOf(sheet1_[3]));
        ExcleValueHelper excleValueHelper = loadExcle.flow();
        // 计算和为1的数组长度
        int realStartX = ExcleValueHelper.byteToInt(sheet1_[0]);
        int realEndX = ExcleValueHelper.byteToInt(sheet1_[1]);
        // 二维数组存储人力成本的值
        BigDecimal result[][] = new BigDecimal[3][realEndX-realStartX];
        // 两层遍历 x外层，因为一列为一个整体
        // X 便利实际 数字 数据，坐标，只需要取出来即可
        int colum = 0;
        for(int y = Integer.valueOf(sheet1_[2]) ; y < Integer.valueOf(sheet1_[3]) ; y++) {// 一行一组

            // 四舍五入以及，和为1
            BigDecimal ary[] = new BigDecimal[ realEndX - realStartX];
            int i = 0;
            BigDecimal total = new BigDecimal(0);
            BigDecimal max = new BigDecimal(0);
            int maxIndex = -1;
            // y
            for (int x = realStartX; x < realEndX; x++) {
                // 算出最大值，求和，记录坐标
                ary[i] = new BigDecimal(formatToNumber(new BigDecimal(excleValueHelper.getCellValue(y, x))));
                if(ary[i].compareTo(max)==1){
                    maxIndex = i;
                    max = ary[i];
                }
                total = total.add(ary[i]);
                i++;
            }

            if(maxIndex==-1){
                printErrln("第"+ y +"行数据有误!");
                continue;
            }

            // 一列结束，生成sql
            ary[maxIndex] = ary[maxIndex].subtract(total.subtract(new BigDecimal(1)));
            // 公共的io方法
            BigDecimal all =  new BigDecimal(0);
            for(int resultIndex=0;resultIndex<ary.length;resultIndex++){
                println(ary[resultIndex]);
                all = all.add(ary[resultIndex]);
                result[colum][resultIndex] = ary[resultIndex];
            }
            colum ++;
        }
        String sort="";
        for(int i=0;i<result[0].length;i++){

            if("SU".equals(sheet1_[4])||"SZ".equals(sheet1_[4])){
                sort = sheet1_[4];
            }else {
                sort = excleValueHelper.getCellValue(Integer.valueOf(sheet1_[2])-1,i+3);
            }

            sql.append(" insert into Accdepconsume values ('" + yearMonth + "', 'FYFT', 'C001','" + sheet1_[4] + "','"
                    // Integer.valueOf(sheet1_[0])),1
                    + sort + "'");
            for(int j=0;j<result.length;j++){
                sql.append("," + result[j][i] );
            }
            sql.append(", '');");
            sql.append("\r\n");
        }
        sql.append("insert into Accdepconsume values ('" + yearMonth + "', 'FYFT', 'C001', '" + sheet1_[4] + "', 'ZBSY', 0.000000, 0.000000, 0.000000, '');--ZBSY就是其他，默认都存在");
        System.out.println(sql);

        /**
         * 业务问题， 最后替换JIA和Non JIA
         */

        //InputStream_IO.IO_PrintWriter(new File(filePath + "sheet1_cost.sql"),sqlReplaceKey(sql));

    }

    // {"C","W","6","14","HO"};
    /* 右 资产 */
    public static void sheet1_asset(String[] sheet1_){

        StringBuffer sql = new StringBuffer("-- " + sheet1_[4] + " \r\n");
        // 起始坐标的位置 和长度 ，但其实换成xyz也不错
        LoadExcle loadExcle = new LoadExcle( fileName ,sheet1_[0],sheet1_[1],Integer.valueOf(sheet1_[2]),Integer.valueOf(sheet1_[3]));
        ExcleValueHelper excleValueHelper = loadExcle.flow();
        // println(excleValueHelper.getCellValue(6,"U"));
        int realStartY = Integer.valueOf(sheet1_[2]) - 1;
        int realEndY = Integer.valueOf(sheet1_[3]);
        // 两层遍历 x外层，因为一列为一个整体
        // X 便利实际 数字 数据，坐标，只需要取出来即可
        int F004 = 4;
        for(int x = ExcleValueHelper.byteToInt(sheet1_[1])-3 ; x < ExcleValueHelper.byteToInt(sheet1_[1]) ; x++) {

            // 四舍五入以及，和为1
            BigDecimal ary[] = new BigDecimal[ realEndY - realStartY];
            int i = 0;
            BigDecimal total = new BigDecimal(0);
            BigDecimal max = new BigDecimal(0);
            int maxIndex = -1;
            // y
            for (int y = realStartY; y < realEndY; y++) {
                // 算出最大值，求和，记录坐标
                ary[i] = new BigDecimal(formatToNumber(new BigDecimal(excleValueHelper.getCellValue(y, x))));
                if(ary[i].compareTo(max)==1){
                    maxIndex = i;
                    max = ary[i];
                }
                total = total.add(ary[i]);
                i++;
            }

            if(maxIndex==-1){
                printErrln("第"+ x +"列数据有误!");
                continue;
            }

            // 一列结束，生成sql
            ary[maxIndex] = ary[maxIndex].subtract(total.subtract(new BigDecimal(1)));
            // 公共的io方法
            BigDecimal all =  new BigDecimal(0);
            for(int resultIndex=0;resultIndex<ary.length;resultIndex++){
                sql.append("insert into Accsh_Costtochannel values ('" + yearMonth + "', 'FYFT', 'C001', '" + sheet1_[4] + "', '" +
                        "F00" + F004 + "', '" +
                        excleValueHelper.getCellValue(resultIndex - 1 + Integer.valueOf(sheet1_[2]),2) + "', " + ary[resultIndex] + ");");

                sql.append("\r\n");
            }
            // F004,F005,F006编号
            F004++;
        }

        System.out.println(sql);

        /**
         * 业务问题， 最后替换JIA和Non JIA
         */

        InputStream_IO.IO_PrintWriter(new File(filePath + "sheet1_asset.sql"),sqlReplaceKey(sql));

    }

    public static void sheet2() {
        StringBuffer sql = new StringBuffer("-- \r\n");
        LoadExcle loadExcle = new LoadExcle( fileName ,"A","J",1,8);
        loadExcle.setDefaultSheet(2);
        ExcleValueHelper excleValueHelper = loadExcle.flow();
        // System.out.println(excleValueHelper.getCellValue(2,2));

        int realStartY = 1;
        int realEndY = 8;
        BigDecimal ary[][] = new BigDecimal[9][7];
        int maxIndexX =-1 ;
        int maxIndexy =-1 ;
        BigDecimal total = new BigDecimal(0);
        BigDecimal max = new BigDecimal(0);
        // 两层遍历 x外层，因为一列为一个整体
        // X 便利实际 数字 数据，坐标，只需要取出来即可
        for(int x = ExcleValueHelper.byteToInt("A") ; x < ExcleValueHelper.byteToInt("J") ; x++) {
            // 四舍五入以及，和为1
            int i = 0;
            // y
            for (int y = realStartY; y < realEndY; y++) {
                // 算出最大值，求和，记录坐标
                ary[x-1][i] = new BigDecimal(formatToNumber(new BigDecimal(excleValueHelper.getCellValue(y, x))));
                // System.out.println(x-1 +"," +i+","+y+","+ary[x-1][i]);
                if(ary[x-1][i].compareTo(max)==1){
                    maxIndexX = x-1;
                    maxIndexy = y;
                    max = ary[x-1][i];
                }
                total = total.add(ary[x-1][i]);
                i++;
            }
        }
        if(maxIndexy==-1){
            printErrln("第"+ maxIndexy +"行数据有误!");
            return;
        }
        // 这个是所有数据的和为1
        //System.out.println("------"+ary[maxIndexX][maxIndexy]);
        // 一列结束，生成sql
        ary[maxIndexX][maxIndexy] = ary[maxIndexX][maxIndexy].subtract(total.subtract(new BigDecimal(1)));
        //System.out.println("------"+ary[maxIndexX][maxIndexy]);
        // 公共的io方法
        BigDecimal all =  new BigDecimal(0);

        for(int aryX=0 ; aryX<9;aryX++) {
            for (int aryY = 0; aryY < 7; aryY++) {
                /*println(aryX+","+aryY+"="+ary[aryX][aryY]);
                all=all.add(ary[aryX][aryY]);*/
                // 有xy，很容易取出所有数据
                sql.append(" insert into Accsh_Tocenterchanel values ('" + yearMonth + "', 'FYFT', 'C001', '" + excleValueHelper.getCellValue(aryY+1,0)+ "', '");
                sql.append(excleValueHelper.getCellValue(0,aryX + 1)+"'," + ary[aryX][aryY] + ");" );
                sql.append("\r\n");
            }
        }

        System.out.println("all="+all);

        System.out.println(sql);
        /**
         * 业务问题， 最后替换JIA和Non JIA
         */

        InputStream_IO.IO_PrintWriter(new File(filePath + "sheet2.sql"),sqlReplaceKey(sql));

    }


    public static void sheet4(String[] sheet1_){
        StringBuffer sql = new StringBuffer("-- \r\n");
        LoadExcle loadExcle = new LoadExcle( fileName ,"A","Z",3,9);
        loadExcle.setDefaultSheet(4);
        ExcleValueHelper excleValueHelper = loadExcle.flow();

        // 计算和为1的数组长度
        int realStartX = ExcleValueHelper.byteToInt(sheet1_[0]);
        int realEndX = ExcleValueHelper.byteToInt(sheet1_[1]);
        // 一行100
        /*BigDecimal result[] = new BigDecimal[realEndX-realStartX];*/

        // X 便利实际 数字 数据，坐标，只需要取出来即可
        int colum = 0;
        for(int y = Integer.valueOf(sheet1_[2]) ; y < Integer.valueOf(sheet1_[3]) ; y++) {// 一行一组

            // 四舍五入以及，和为1
            BigDecimal ary[] = new BigDecimal[ realEndX - realStartX];
            int i = 0;
            BigDecimal total = new BigDecimal(0);
            BigDecimal max = new BigDecimal(0);
            int maxIndex = -1;
            // y
            for (int x = realStartX; x < realEndX; x++) {
                // 算出最大值，求和，记录坐标
                ary[i] = new BigDecimal(formatToNumber(new BigDecimal(excleValueHelper.getCellValue(y, x))));
                if(ary[i].compareTo(max)==1){
                    maxIndex = i;
                    max = ary[i];
                }
                total = total.add(ary[i]);
                i++;
            }
            if(maxIndex==-1){
                printErrln("第"+ y +"行数据有误!");
                continue;
            }
            // 一列结束，生成sql
            ary[maxIndex] = ary[maxIndex].subtract(total.subtract(new BigDecimal(1)));
            // 公共的io方法
            BigDecimal all =  new BigDecimal(0);
            int TYPECODE=1;
            for(int resultIndex=Integer.valueOf(ExcleValueHelper.byteToInt(sheet1_[0]))-1;resultIndex<Integer.valueOf(ExcleValueHelper.byteToInt(sheet1_[0]))-1+ary.length;resultIndex++){
                //println(ary[resultIndex]+","+excleValueHelper.getCellValue(2,resultIndex+1)+","+excleValueHelper.getCellValue(y,0));
                //all = all.add(ary[resultIndex]);
                sql.append(" insert into Accsh_Todeptoriskcode values ('" + yearMonth + "', 'FYFT', 'C001', '" + excleValueHelper.getCellValue(y,0) + "', '" +
                        excleValueHelper.getCellValue(y,0) +formatToNumberThree(TYPECODE)+"','" + excleValueHelper.getCellValue(2,resultIndex+1)
                        + "'," + ary[resultIndex-Integer.valueOf(ExcleValueHelper.byteToInt(sheet1_[0]))+1] +",'"+ sheet1_[4] +"');");
                sql.append("\r\n");
                TYPECODE++;
            }
            colum ++;
            System.out.println("all:"+all);
        }
        System.out.println(sql);

        InputStream_IO.IO_PrintWriter(new File(filePath + "sheet4_"+sheet1_[4]+".sql"),sqlReplaceKey(sql));
    }


    /******************** 工具方法 *****************/

    // 四舍五入六位数
    public static String formatToNumber(BigDecimal obj) {
        DecimalFormat df = new DecimalFormat("#.000000");
        if(obj.compareTo(BigDecimal.ZERO)==0) {
            return "0.000000";
        }else if(obj.compareTo(BigDecimal.ZERO)>0&&obj.compareTo(new BigDecimal(1))<0){
            return "0"+df.format(obj).toString();
        }else {
            return df.format(obj).toString();
        }
    }

    // 格式化三位数
    public static String formatToNumberThree(int obj) {
        DecimalFormat df = new DecimalFormat("000");
        return df.format(obj).toString();
    }

    // sql关键字替换
    static String sqlReplaceKey(StringBuffer sql){
        return sql.toString().replace("&","'||chr(38)||'").replace("Branch JIA","JIA")
                .replace("Branch Non JIA","Non JIA").replace("BD（HO）","BD")
                .replace("IBG（HO）","IBG").replace("PICC（GD）","PICC")
                .replace("日系业务\n" +
                        "（JIA）","JIA")
                .replace("非日系业务\n" +
                        "（Non JIA）","Non JIA")
                .replace("Internet业务\n" +
                        "（Branch Internet）","Branch Internet")
                .replace("车险直保业务\n" +
                        "（Branch Direct Motor）","Direct Motor")
                .replace("车险分入业务*\n" +
                        "（Treaty Motor）","Treaty Motor")
                .replace("非车险分入业务*\n" +
                        "（Treaty Non Motor）","Treaty Non Motor");
    }

    /*static String sqlReplaceChannel(String sql){
        // 类似JIA这些
        return sql.toString().replace("&","'||chr(38)||'");
    }*/

    /**
     * 控制输出都用这个测试 成功后直接屏蔽掉
     */
    public static void println(String str){ System.out.println(str); }
    public static void println(BigDecimal str){ System.out.println(str); }
    public static void println(){ System.out.println(); }
    public static void print(String str){ System.out.print(str); }
    public static void printErrln(String str){ System.err.println(str); }
    /******************** 工具方法 *****************/
}
