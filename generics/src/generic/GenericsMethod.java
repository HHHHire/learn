package generic;

/**
 * @author ddh
 * @date 2019/8/18 20:10
 * @description 泛型方法
 **/
public class GenericsMethod {
    private static <E> void printArray(E[] inputArray) {
        for (E element : inputArray) {
            System.out.println(element);
        }
    }

    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3, 4, 5};
        String[] stringArray = {"adf", "daa"};
        Double[] doubleArray = {1.1, 2.2, 3.3, 4.4};
        Character[] charArray = {'h', 'a', 's'};


        printArray(intArray);
        printArray(doubleArray);
        printArray(charArray);
        printArray(stringArray);
    }
}
