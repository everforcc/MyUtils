package cn.cc.design.single.singleton6;

/**
 * @author c.c.
 * @date 2021/3/24
 */
public enum Singleton {

    INSTANCE;
    public void whateverMethod(Singleton singleton) {
        switch (singleton){
            case INSTANCE :
                System.out.println(singleton);

        }
    }

}