package com.bkhech.home.practice.javacore.java8;

import java.util.HashMap;
import java.util.Map;

public class MapTest {


	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<>();

		for (int i = 0; i < 10; i++) {
			map.putIfAbsent(i, "val" + i);
		}

		map.forEach((id,val)->System.out.println(val));
		map.computeIfPresent(3,(num,val)->val +num);
		System.out.println(map.get(3)); // val33

		map.computeIfPresent(9,(num,val)->null);
		System.out.println(map.containsKey(9)); // false

		map.computeIfAbsent(23,num ->"val"+num *2);
		System.out.println(map.containsKey(23)); // true
		System.out.println(map.get(23)); // val46

		// 已经存在就不变，不存在才替换
		map.computeIfAbsent(3,num ->"bam");
		System.out.println(map.get(3)); // val33

		// key 和value都匹配时才删除
		map.remove(3,"val3");
		map.get(3); // val33

		map.remove(3,"val33");
		map.get(3); // null

		map.getOrDefault(42,"not found"); // not found

		// 如果原来不存在，就设置成指定的value
		map.merge(9,"BB",(value,newValue)->value.concat(newValue)); //newValue="BB"
		System.out.println(map.get(9)); // val9

		// 如果key存在，则把value设置成重新计算后的结果
		map.merge(9,"AA",(value,newValue)->value.concat(newValue));
		System.out.println(map.get(9)); // val9val9
	}

}
