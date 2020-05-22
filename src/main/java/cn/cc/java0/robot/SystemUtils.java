package cn.cc.java0.robot;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * @author AJAXFAN
 *
 * @Date 2010-6-27
 */
public class SystemUtils {
    public static void main(String[] args) {
        setClipbordContents("测试");//向剪贴板中添加内容测试
        setClipbordContents("测试2");//向剪贴板中添加内容测试
    }

    /**
     * 向剪贴板中添加内容
     * @param contents
     */
    public static void setClipbordContents(String contents) {
        StringSelection stringSelection = new StringSelection( contents );
// 系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}