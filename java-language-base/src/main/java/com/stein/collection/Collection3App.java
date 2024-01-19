package com.stein.collection;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Managing the Content of a Map
 *
 * @author njl
 * @date 2023/2/19
 */
public class Collection3App {
    public static void main(String[] args) {
//		c2();
//		c3();
//		c4();
//		c5();
//		c6();
//		c7();
//		c8();
//		c9();
        c10();
    }

    public static void c10() { // Removing a Value From the Collection of Values
        // Removing a value is not as simple because a value can be found more than once in a map. In that case,
        // removing a value from the collection of values just removes the first matching key/value pair.
        // 从集合中删除 value 只会删除第一个匹配的键值对
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");
        map.put(7, "six");
        // ValueIterator extends HashIterator 最终执行的 remove 方法是 HashIterator 的 remove 方法，内部又执行了
        // removeNode(hash(key), key, null, false, false); 方法，再次体会动态绑定和多态的机制。
        boolean b1 = map.values().remove("six"); // 打断点调试，观察 remove 方法是怎么执行的
        System.out.println("map = " + map); // map = {1=one, 2=two, 3=three, 4=four, 5=five, 7=six}
    }

    public static void c9() { // Getting a View on the Keys, the Values or the Entries of a Map
        // Set<K> keySet(); 返回 Map 的键集视图
        // Collection<V> values(); 返回 Map 的值集视图
        // Set<Map.Entry<K, V>> entrySet(); 返回 Map 的键值对映射条目视图
        // These sets are views backed by the current map. Any change made to the map is reflected in those views.
        // 上面三个返回的集合都是由 Map 支持的，因此对于这些集合的改变也会影响 Map 集合，反之也是一样的。
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");
        map.put(6, "SIX");
        Set<Integer> keys = map.keySet();
        Collection<String> values = map.values();
        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        System.out.println("keys = " + keys); // keys = [1, 2, 3, 4, 5, 6]
        System.out.println("values = " + values); // values = [one, two, three, four, five, SIX]
        System.out.println("entries = " + entries); // entries = [1=one, 2=two, 3=three, 4=four, 5=five, 6=SIX]
        keys.remove(6);
        // You do not have access to all the operations on these sets though. For instance,
        // you cannot add an element to the set of keys, or to the collection of values.
        // If you try to do that, you will get an UnsupportedOperationException.
//		values.add("TEN"); // UnsupportedOperationException
        entries.add(new Map.Entry<Integer, String>() {
            @Override
            public Integer getKey() {
                return 10;
            }

            @Override
            public String getValue() {
                return "TEN";
            }

            @Override
            public String setValue(String value) {
                return null;
            }
        }); // UnsupportedOperationException
        System.out.println("map = " + map); // map = {1=one, 2=two, 3=three, 4=four, 5=five}
    }

    public static void c8() { // Checking for the Content of a Map
        Map<Integer, String> map1 = new HashMap<>();
        map1.put(1, "one");
        map1.put(2, "two");
        map1.put(3, "three");

        // void putAll(Map<? extends K, ? extends V> m);
        HashMap<Integer, String> map2 = new HashMap<>();
        map2.put(1, "ONE");
        map2.put(4, "FOUR");
        // 如果 map1 中的 key 和 map2 中的 key 重复，那么会使用 map2 中的 key 对应的值替换 map1 中 key 对应的值
        map1.putAll(map2);
        System.out.println("map1 = " + map1); // map1 = {1=ONE, 2=two, 3=three, 4=FOUR}

        // boolean isEmpty(); 判断 map1 中是否存在键值对的映射关系
        boolean b1 = map1.isEmpty();
        System.out.println("b1 = " + b1); // false
        // int size(); 返回 map1 中所有的键值对映射数量
        int size = map1.size();
        System.out.println("size = " + size); // 4
        // void clear(); 清空 map1 中所有的键值对映射关系
        map1.clear();
        boolean b2 = map1.isEmpty(); // true
        System.out.println("b2 = " + b2);

    }

    public static void c7() { // Checking for the Presence of a Key or a Value
        // boolean containsKey(Object key);
        // boolean containsValue(Object value);
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        boolean b1 = map.containsKey(1);
        boolean b2 = map.containsKey(4);
        System.out.println("b1 = " + b1); // true
        System.out.println("b2 = " + b2); // false

        boolean b3 = map.containsValue("one");
        boolean b4 = map.containsValue("four");
        System.out.println("b3 = " + b3); // true
        System.out.println("b4 = " + b4); // false
    }

    public static void c6() { // Removing a Key from a Map
        // V remove(Object key)
        // remove，返回与 key 关联的值
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        String s1 = map.remove(3); // three
        String s2 = map.remove(4); // null
        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);
        // default boolean remove(Object key, Object value) JDK8
        // 只有 key 和 value 全部匹配的时候才会从 Map 中删除键值对的映射
        boolean b1 = map.remove(2, "TWO"); // false
        boolean b2 = map.remove(1, "one"); // true
        System.out.println("b1 = " + b1);
        System.out.println("b2 = " + b2);
    }

    public static void c5() { // Getting a Value From a Key
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        // Map get
        String one = map.get(1); // key exists in the map
        String four = map.get(4); // key doesn't exist in the map
        System.out.println("one = " + one); // one
        System.out.println("four = " + four); // null

        // Map getOrDefault
        // 如果要获取的 value 与它相关联的 key 不在 Map 中，那么返回指定的默认值 defaultValue，这里是 four
        String defaultFour = map.getOrDefault(4, "four");
        System.out.println("defaultFour = " + defaultFour); // four

        // 使用 Stream 流
        List<String> values =
                IntStream
                        .range(0, 5)
                        .mapToObj(key -> map.getOrDefault(key, "UNDEFINED"))
                        .collect(Collectors.toList());

        System.out.println("values = " + values); // values = [UNDEFINED, one, two, three, UNDEFINED]
    }

    public static void c4() {
        Map<String, Integer> map = new HashMap<>();

        map.put("one", 1);
        map.put("two", null);
        map.put("three", 3);
        map.put("four", null);
        map.put("five", 5);

        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            map.putIfAbsent(key, -1); // 如果 key 对应的 value 为 null，那么用默认的 -1 替换
        }

        for (int value : map.values()) { // NPE
            System.out.println("value = " + value);
        }
    }

    public static void c3() { // Adding a Key Value Pair to a Map
        HashMap<String, String> map = new HashMap<>();
        String s1 = map.put("Dog", "猫");
        String s2 = map.put("Dog", "狗");
        System.out.println("s1 = " + s1); // s1 = null
        System.out.println("s2 = " + s2); // s2 = 猫

        String s4 = map.putIfAbsent(null, "空"); // s4 = null
        System.out.println("s4 = " + s4);
        String s5 = map.putIfAbsent(null, "NULL"); // null 对应的 value 不为空，因此不会添加用新的 value 替换
        System.out.println("s5 = " + s5); // s5 = 空
        System.out.println("map = " + map); // map = {null=空, Dog=狗}
        String s6 = map.putIfAbsent("good", null);
        String s7 = map.putIfAbsent("good", "好");
        System.out.println("s6 = " + s6);
        System.out.println("s7 = " + s7);
        System.out.println("map = " + map); // map = {null=空, Dog=狗, good=好}
    }

    public static void c2() { // Exploring the Map interface
        // Map 接口中定义了一个内部接口 Entry 用来模型化键值对，表示一个条目
        // K getKey(); 用来获得 key，V getValue(); 用来获得值，V setValue(V value); 修改值，返回旧值
        // The Map.Entry objects you can get from a given map are views on the content of the map.
        // Modifying the value of an entry object is thus reflected in the map and the other way round.
        // This is the reason why you cannot change the key in this object: it could corrupt your map.
        HashMap<String, String> map = new HashMap<>();
        map.put("China", "中国");
        map.put("United States", "美国");
        map.put("Russia", "英国");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            map.put("Russia", "俄罗斯"); // 修改 entry 对象的值也会映射到目标 map 中的 key 对应的 value 值，反过来也是如此
        }
        System.out.println("map = " + map); // map = {United States=美国, China=中国, Russia=俄罗斯}
    }

    public static void c1() { // Storing Key/Value Pairs in a Map
        // The relationship between a key and its bound value follows these two simple rules.
        // A key can be bound to only one value
        // A value can be bound to several keys.
        // The set of all the keys cannot have any duplicates, so it has the structure of a Set
        // The set of all the key/value pairs cannot have duplicates either, so it also has the structure of a Set
        // The set of all the values may have duplicates, so it has the structure of a plain Collection.
        HashMap<String, String> map = new HashMap<>();
        Set<String> keys = map.keySet(); // 键集不能存在重复的元素
        Collection<String> values = map.values(); // 值集可以存在重复的元素
        Set<Map.Entry<String, String>> entries = map.entrySet(); // 类目集不能存在重复的类目（Entry）
        // 选择可变的对象作为 Map 的 key 是允许的，但不推荐并且非常危险，一旦一个键已经添加到了 Map 中，修改它会
        // 导致它的 hashCode 值发生变化以及它的相等性。这可能无法在通过这个修改后的对象获得 Map 中映射的 value 了，
        // 也有可能会返回一个不同的值。通常推荐使用 String 作为 Map 的键，因为 String 是 immutable class。
    }
}
