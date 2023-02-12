package generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author njl
 * @date 2023/1/29
 */
public class GenericsApp {
	public static void main(String[] args) {
//		generics1();
//		generics2();
//		generics3();
		generics4();
	}

	public static void generics4() {
		ArrayList<String> list1 = new ArrayList<>();
		list1.add("a");
		list1.add("b");
		list1.add("c");

		ArrayList<Integer> list2 = new ArrayList<>();
		list2.add(1);
		list2.add(2);
		list2.add(3);

		ArrayList<Double> list3 = new ArrayList<>();
		list3.add(1.1);
		list3.add(1.2);
		list3.add(1.3);

//		System.out.println(getFirstElement(list1)); // ? 相当于 String
		System.out.println(getFirstElement(list2)); // ? 相当于 Integer
		System.out.println(getFirstElement(list3)); // ? 相当于 Double
	}

	/**
	 *
	 */
	// idea 报'getFirstElement(List<? extends Number>)' clashes with 'getFirstElement(List<?>)'; both methods have same erasure
	public static Number getFirstElement(List<? extends Number> numbers) {
		return numbers.get(0);
	}

	/**
	 * 使用泛型通配符 ?
	 */
//	public static Object getFirstElement(List<?> list) {
//		return list.get(0);
//	}

	public static void generics3() {
		Dog qiuqiu = new Dog("球球", 2, "金毛");
		CommonResult<Dog> result = CommonResult.success(qiuqiu);
		System.out.println("result = " + result);
		CommonResult<Object> error = CommonResult.error(500);
		System.out.println("error = " + error);
		CommonResult<Object> error1 = CommonResult.error(result);
		System.out.println("error1 = " + error1);
	}

	public static void generics2() {
		int max1 = maximum(1, 2, 3);
		System.out.println("max1 = " + max1);
		double max2 = maximum(1.5, -2.9, 7.5);
		System.out.println("max2 = " + max2);
		String max3 = maximum("pear", "apple", "orange");
		System.out.println("max3 = " + max3);
	}

	public static void generics1() {
		Integer[] intArray = {1, 2, 3};
		Double[] doubleArray = {1.1, 2.2, 3.3, 4.4};
		Character[] charArray = {'H', 'E', 'L', 'L', 'O'};
		printArray(intArray);
		printArray(doubleArray);
		printArray(charArray);
	}

	/**
	 * 通配符泛型参数
	 */
	public static <T extends Comparable<T>> T maximum(T x, T y, T z) {
		T max = x; // 假设 max 是最大值
		if (y.compareTo(max) > 0) { // 因为泛型 T 参数对象实现了 Comparable接口，且又是和同类型比较的
			max = y;
		}
		if (z.compareTo(max) > 0) {
			max = z;
		}
		return max;
	}

	public static <E> void printArray(E[] genericArr) {
		if (genericArr.getClass().isArray()) {
			for (E e : genericArr) {
				System.out.println("e = " + e);
			}
		}
	}
}
