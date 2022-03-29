package cc.design.design5;

import java.io.Serializable;

public class Square extends Shape implements Serializable {

    public Square(){
        type = "Square";
    }

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }

    @Override
    public String toString() {
        return "Square{" +
                "type='" + type + '\'' +
                '}';
    }
}
