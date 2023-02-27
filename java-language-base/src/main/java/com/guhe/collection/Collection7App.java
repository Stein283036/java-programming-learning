package com.guhe.collection;

import java.util.*;

/**
 * Comparator & Comparable
 *
 * @author njl
 * @date 2023/2/27
 */
public class Collection7App {
	public static void main(String[] args) {
//		c1();
		c2();
	}

	public static void c2() {
		// Comparator 的陷阱
		Player player1 = new Player(59, "John", Integer.MAX_VALUE);
		Player player2 = new Player(67, "Roger", -1);

		List<Player> players = Arrays.asList(player1, player2);
		Comparator<Player> comparator = (p1, p2) -> p1.getRanking() - p2.getRanking();
		players.sort(comparator);
		// players = [Player{ranking=59, name='John', age=2147483647}, Player{ranking=67, name='Roger', age=-1}]
		// 因为整数的溢出，Integer.MAX_VALUE - (-1) 会小于 0，因此它小于 -1，最终的排序结果不是我们所期望的。
		System.out.println(Integer.MAX_VALUE - (-1)); // -2147483648
		System.out.println("players = " + players);
	}

	public static void c1() {
		List<Player> footballTeam = new ArrayList<>();
		Player player1 = new Player(59, "John", 20);
		Player player2 = new Player(67, "Roger", 22);
		Player player3 = new Player(45, "Steven", 24);
		footballTeam.add(player1);
		footballTeam.add(player2);
		footballTeam.add(player3);

		System.out.println("Before Sorting : " + footballTeam);
		// 以 Lambda 表达式的方式 使用 Java 8 的 Comparator 静态方法生成自定义类的比较器
		Comparator<Player> rankingComparator = Comparator.comparing(Player::getRanking);
		Comparator<Player> ageComparator = Comparator.comparing(Player::getAge);
		Collections.sort(footballTeam, ageComparator);
//		footballTeam.sort(ageComparator);
		System.out.println("After Sorting : " + footballTeam);

	}
}

class Player {
	private int ranking;
	private String name;
	private int age;

	public Player(int ranking, String name, int age) {
		this.ranking = ranking;
		this.name = name;
		this.age = age;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getRanking() {
		return ranking;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return "Player{" +
				"ranking=" + ranking +
				", name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}
