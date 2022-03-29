package cc.design.design5;

public class PrototypePatternDemo {
    public static void main(String[] args) {
//        ShapeCache.loadCache();
//
//        Shape clonedShape = ShapeCache.getShape("1");
//        System.out.println("Shape : " + clonedShape.getType());
//
//        Shape clonedShape2 = ShapeCache.getShape("2");
//        System.out.println("Shape : " + clonedShape2.getType());
//
//        Shape clonedShape3 = ShapeCache.getShape("3");
//        System.out.println("Shape : " + clonedShape3.getType());
        Circle circle = new Circle();
        System.out.println(circle.clo);
        Circle circleClone = (Circle)circle.clone();
        System.out.println(circleClone.clo);
        circleClone.clo = "def";
        System.out.println(circle.clo);
        System.out.println(circleClone.clo);
    }
}