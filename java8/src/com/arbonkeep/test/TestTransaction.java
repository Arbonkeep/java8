package com.arbonkeep.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class TestTransaction {
	
	List<Transaction> transactions = null;
	
	@Before
	public void before(){
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");
		
		transactions = Arrays.asList(
				new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000),
				new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710),
				new Transaction(mario, 2012, 700),
				new Transaction(alan, 2012, 950)
		);
	}
	
	//1. 找出2011年发生的所有交易，并按交易额排序（从低到高）
	@Test
	public void test1() {
		transactions.stream()
				    .filter((t) -> t.getYear() == 2011)
				    .sorted((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue()) )
					.forEach(System.out::println);

	}
	
	//2. 交易员都在哪些不同的城市工作过？
	@Test
	public void test2() {
		transactions.stream()
				    .map((t) -> t.getTrader().getCity())
					.distinct()
		 			.forEach(System.out::println);
	}
	
	//3. 查找所有来自剑桥的交易员，并按姓名排序
	@Test
	public void test3() {
		transactions.stream()
					.filter((t) -> t.getTrader().getCity().equals("Cambridge"))
					.map((t) -> t.getTrader())
					.sorted((t1, t2) -> t1.getName().compareTo(t2.getName()))
					.forEach(System.out::println);
	}

	//4. 返回所有交易员的姓名字符串，按字母顺序排序
	@Test
	public void test4() {
		//直接打印
		transactions.stream()
				 	.map((t) -> t.getTrader().getName())
					.sorted()
					.forEach(System.out::println);

		System.out.println("-----------------------");

		//所有名字在一行
		String str = transactions.stream()
				.map((t) -> t.getTrader().getName())
				.sorted()
				.reduce("", String::concat);//直接调用String中的concat方法拼接成字符串

		System.out.println(str);

		System.out.println("------------------------");

		//所有字母进行排序
		transactions.stream()
					.map((t) -> t.getTrader().getName())
					.flatMap(TestTransaction::filterCharactor)
					.sorted()//可以修改实现忽略大小写
					.forEach(System.out::print);

	}

	//声明一个获取字符串中每一个字符，并将字符存入集合，将流返回作为返回值的方法(可以修改实现忽略大小写)
	public static Stream<Character> filterCharactor(String str) {
		List<Character> list = new ArrayList<>();
		for (Character c : str.toCharArray()) {
			list.add(c);
		}
		return list.stream();
	}
	
	//5. 有没有交易员是在米兰工作的？boolean
	@Test
	public void test5() {
		boolean b = transactions.stream()
				.anyMatch((t) -> t.getTrader().getCity().equals("Milan"));

		System.out.println(b);
	}

	//6. 打印生活在剑桥的交易员的所有交易额
	@Test
	public void test6() {
		transactions.stream()
					.filter((t) -> t.getTrader().getCity().equals("Cambridge"))
					.map((t) -> t.getValue())
					.forEach(System.out::println);
	}

	//7. 所有交易中，最高的交易额是多少
	@Test
	public void test() {
		Optional<Integer> max = transactions.stream()
				.map((t) -> t.getValue())
				.max(Integer::compareTo);

		System.out.println(max.get());
	}
	
	//8. 找到交易额最小的交易
	@Test
	public void test8() {
		Optional<Transaction> min = transactions.stream()
				.min((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue()));

		System.out.println(min.get());
	}


}