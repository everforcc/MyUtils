package java0;

/**
 * @author c.c.
 * @date 2021/8/24
 */
public class DemoPattens {

    private DemoPattens(){}

    private static class DemoPattensHoler{
        private static DemoPattens INSTANCE = new DemoPattens();

        static {
            System.out.println("------");
        }
    }

    private static DemoPattens getInstance(){
        return DemoPattensHoler.INSTANCE;
    }

}
