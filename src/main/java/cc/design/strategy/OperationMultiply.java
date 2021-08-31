package cc.design.strategy;

/**
 * @author c.c.
 * @date 2021/3/25
 */
public class OperationMultiply implements Strategy{

    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }

}