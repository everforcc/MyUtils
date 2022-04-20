package cc.advanced.web.craw.jdkcraw;

import cc.sysconstant.ConstantFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * 根据图片的URL下载
 * 
 * @author Administrator
 *
 */
public class DownImgByImgURL {

	public static void main(String[] args) {
		List<String> list=new ArrayList<>();
		//list.add("https://pic1.zhimg.com/v2-e81db7b3d5c98aed59cbf5e3d59f7bc4_1440w.jpg");
		list.add("http://10.248.103.90:9002/SunTRM/servlet/GetImage?UhFOUNO1mCHrmC61m1UgpPEfcuzaVP67JuaRVP6RDuA4Vh7ipPmRNvIaSuEfJOt-UhMnUcDaXg6RpPtaUu6RpPtaUuAeScMaJufGSPDfX17GpPMeVhA2VhFOUg71VdL1mCHrJs0tJsHsJs6RSNHwmNFaSg5wUhHzmu51UNaamBygSu_aUPHvmCIBSsMxmdLsmBZsUCmvS1OOmgIfJN2w_gHGSgUf_4t4SP5E_h2tmsU4mhmqXhziIgU-VhAxUPzBXifrDCOrIgU-VhAxVt7qSPtfKNmzJiyqU1UAXgE5UPFeNhfqUNO1mCezJN51JNmrHCHO8B0r8B0rIiMRpuAqKc2rpCfHAPadXgGl8PZO2Ner_5aHpvF1d5FdAP_ypOak");
		//list.add("http://10.248.103.90:9002/SunTRM/servlet");
		Download(list,1);
	}

	/**
	 * 步奏4
	 */
	/**
	 * 根据图片的URL进行下载
	 * 
	 * @param listImgSrc
	 */
	public static void Download(List<String> listImgSrc, int filedir) {
		try {

			// 总时间
			Date begindate = new Date();
			System.out.println("预测有多少图片:" + listImgSrc.size());
			for (String url : listImgSrc) {
				// 下载开始时间
				Date begindate2 = new Date();

				/**
				 * 截取网络图片的名字和参数
				 */
				String imageName = url.substring(url.lastIndexOf("/") + 1, url.length());
				imageName="a.png";
				System.out.println("imageName" + imageName);
				/**
				 * 判断imageName的后缀名,如果带参数去掉参数作为名字
				 */

				if (imageName.contains("?")) {
					System.out.println("处理字符串");
					imageName = imageName.substring(0, imageName.lastIndexOf("@"));
					System.out.println("imageName" + imageName);
				}

				URL uri = new URL(url);

				InputStream in = uri.openStream();

				/**
				 * 创建文件夹和文件名
				 */
				File saveFile = new File(ConstantFile.L1_javaFilePath + "");
				if (!saveFile.exists()) {
					saveFile.mkdir();
				}
				FileOutputStream fo = new FileOutputStream(new File(ConstantFile.L1_javaFilePath + "" + filedir + imageName));

				/**
				 * 以流的方式进行下载
				 */
				byte[] buf = new byte[1024];
				int length = 0;
				while ((length = in.read(buf, 0, buf.length)) != -1) {
					fo.write(buf, 0, length);
				}
				in.close();
				fo.close();
				System.out.println("第" + filedir + "次");
				System.out.println(imageName + "下载完成");

				/**
				 * 计算下载所用时间
				 */
				Date overdate2 = new Date();
				double time = overdate2.getTime() - begindate2.getTime();
				System.out.println("耗时：" + time / 1000 + "s");
			}

			/**
			 * 计算总时常类似日志的作用
			 */
			Date overdate = new Date();
			double time = overdate.getTime() - begindate.getTime();
			System.out.println("总耗时：" + time / 1000 + "s");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("进入异常");
		}
	}

}
