package cn.cc.core.basemethod;

/**
 * 判断数据类型
 */
import java.util.Scanner;

public class Scan {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// 判断是否输入整数
		while (!input.hasNextInt()) {
			System.out.println("进入循环");
			input = new Scanner(System.in);
		}
		int a = input.nextInt();
		System.out.println(a);

	}
}
