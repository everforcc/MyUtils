package cc.java0.annotation.base2;

/**
 * @author everforcc 2021-09-08
 */
public abstract class Const {

    // 可以用来为注解属性赋值
    public static class ParamValidation{
        public static final String ID_IS_NULL = "id 不能为null";
        // 定时任务 @Scheduled(cron = )
        public static final String TEN_SECONDS = "*/10 * * * *";
        public static final String ONE_MINUTE = "";
        public static final String FIVE_MINUTES = "";
        public static final String ONE_HOUR = "";
        public static final String THREE_HOUR = "";
    }
}
