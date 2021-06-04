package Question1;

public class testMain {

    public static void main(String[] args) {
        //main to test the function of LinkedHashMap with Chaining

        LinkedHashMapWithChaining<String,String> map = new LinkedHashMapWithChaining<String,String>();
        map.put("one","ONE");
        map.put("two","TWO");
        map.put("three","THREE");
        map.put("four","FOUR");
        map.put("five","FIVE");
        map.put("six","SIX");
        System.out.println(map);
        map.remove("three");
        System.out.println(map);
        System.out.println(map.get("five"));
        System.out.println(map.containsKey("four"));
        System.out.println(map.containsValue("SIX"));
        System.out.println(map.containsValue("NINE"));

        System.out.println("");

        LinkedHashMapWithChaining<Integer, Integer> map2 = new LinkedHashMapWithChaining<Integer, Integer>();

        map2.put(25, 12);
        map2.put(21, 121);
        map2.put(35, 151);
        map2.put(33, 15);
        map2.put(30, 89);

        System.out.println(map2.toString());

        System.out.println(map2.keySet());
        System.out.println(map2.values());

    }
}
