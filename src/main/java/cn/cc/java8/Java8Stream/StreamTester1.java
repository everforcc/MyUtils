package cn.cc.java8.Java8Stream;



import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamTester1 {

/*    +--------------------+       +------+   +------+   +---+   +-------+
      |     stream of elements +-----> |filter+-> |sorted+-> |map+-> |collect|
      +--------------------+       +------+   +------+   +---+   +-------+        
      
      List<Integer> transactionsIds = 
				widgets`.stream()
				             .filter(b -> b.getColor() == RED)
				             .sorted((x,y) -> x.getWeight() - y.getWeight())
				             .mapToInt(Widget::getWeight)
				             .sum();
				             
				             */

	public static void main(String[] args) {
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

		//1.forEach
		//Stream 提供了新的方法 'forEach' 来迭代流中的每个数据。
		filtered.stream().forEach(System.out::println);

		//2.map
		//map 方法用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素对应的平方数：
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		// 获取对应的平方数
		List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
		squaresList.stream().forEach(System.out::println);

		//3.filter
		//filter 方法用于通过设置的条件过滤出元素。以下代码片段使用 filter 方法过滤出空字符串： filter(string -> !string.isEmpty())

		//4.limit
		//limit 方法用于获取指定数量的流。 以下代码片段使用 limit 方法打印出 10 条数据：
		Random random = new Random();
		random.ints().limit(10).forEach(System.out::println);

		//5.sorted
		//sorted 方法用于对流进行排序。以下代码片段使用 sorted 方法对输出的 10 个随机数进行排序：
		System.out.println("排序");
		random.ints().limit(10).sorted().forEach(System.out::println);

		//6.parallel
		//并行（parallel）程序,parallelStream 是流并行处理程序的代替方法。以下实例我们使用 parallelStream 来输出空字符串的数量：
		List<String> stringsparallel = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		// 获取空字符串的数量
		int count = (int) stringsparallel.parallelStream().filter(string -> string.isEmpty()).count();
		System.out.println("parallel总数:"+count);
	}


	public void CollectorsTester() {
		//7.Collectors
		//Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：
		List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

		System.out.println("筛选列表: " + filtered);
		String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining("-"));
		System.out.println("合并字符串: " + mergedString);
	}


	public void CollectorsTester1(){
		//8.统计
		//另外，一些产生统计结果的收集器也非常有用。它们主要用于int、double、long等基本类型上，它们可以用来产生类似如下的统计结果。

		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

		IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

		System.out.println("列表中最大的数 : " + stats.getMax());
		System.out.println("列表中最小的数 : " + stats.getMin());
		System.out.println("所有数之和 : " + stats.getSum());
		System.out.println("平均数 : " + stats.getAverage());
		System.out.println("一共有几个数:"+stats.getCount());
	}


}
