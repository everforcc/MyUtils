package cn.cc.java0.awt;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;


/**
 * 获取桌面尺寸获取图片尺寸
 *
 * @author cc
 *
 */
public class ShowImage extends Frame {

	String filename;

	public ShowImage(String filename) {
		try {
			this.filename = filename;

			// 获取系统尺寸
			Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
			int h = (int) ss.getHeight();
			int w = (int) ss.getWidth();

			// 获取图片尺寸
			BufferedImage sourceImg = ImageIO.read(new FileInputStream(
					new File(filename)));
			int width = sourceImg.getWidth();
			int height = sourceImg.getHeight();

			// 设置系统图片出现位置
			this.setBounds(w / 2 - width / 2, h / 2 - height / 2, width, height);

			// 设置显示
			this.setVisible(true);
			Thread.sleep(2000);
			this.setVisible(false);

		} catch (Exception e) {

		}

	}

	public void paint(Graphics g) {

		Image image = this.getToolkit().getImage(filename);

		g.drawImage(image, 0, 0, this);

	}

	public static void main(String[] args) {

		new ShowImage("F://st.gif");

	}

}
