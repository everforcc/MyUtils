package cc.frame.spring;

import cc.frame.spring.scan.ScanAllClass;

import java.io.IOException;

/**
 * @author everforcc 2021-09-29
 */
public class SpringRun {

    public static void main(String[] args) {
        try {
            ScanAllClass.getAllClassByInterface(SpringRun.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
