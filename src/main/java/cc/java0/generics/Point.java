package cc.java0.generics;

/**
 * 泛型类
 * @author guokailong 2021-09-13
 */
public class Point<T> {
    private T x ;
    private T y ;
    public void setX(T x){//作为参数
        this.x = x ;
    }
    public void setY(T y){
        this.y = y ;
    }
    public T getX(){//作为返回值
        return this.x ;
    }
    public T getY(){
        return this.y ;
    }
}
class TestPoint{
    public static void main(String[] args) {
        //IntegerPoint使用
        Point<Integer> pInteger = new Point<Integer>() ;
        pInteger.setX(new Integer(100)) ;
        System.out.println(pInteger.getX());

        //FloatPoint使用
        Point<Float> pFloat = new Point<Float>() ;
        pFloat.setX(new Float(100.12f)) ;
        System.out.println(pFloat.getX());
    }
}
