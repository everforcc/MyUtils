package cc.java0.generics;

/**
 * 多泛型变量定义及字母规范
 * @author guokailong 2021-09-13
 */
class MorePoint<T,U> {
    private T x;
    private T y;

    private U name;

    public void setX(T x) {
        this.x = x;
    }
    public T getX() {
        return this.x;
    }

    public void setName(U name){
        this.name = name;
    }

    public U getName() {
        return this.name;
    }
}
class TMorePoint{
    public static void main(String[] args) {
        //使用
        MorePoint<Integer,String> morePoint = new MorePoint<Integer, String>();
        morePoint.setX(123);
        morePoint.setName("name");
        System.out.println("morPont.getName:" + morePoint.getName());
        System.out.println("morPont.getX:" + morePoint.getX());
    }
}