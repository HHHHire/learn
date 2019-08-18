package demo;

/**
 * @author ddh
 * @date 2019/8/18 20:18
 * @description 限制参数类型
 **/
public class Maximum {
    /**
     * 比较最大值
     */
    private static <T extends Comparable<T>> T maximum(T x, T y, T z) {
        T max = x;
        if (y.compareTo(max) > 0) {
            max = y;
        }
        if (z.compareTo(max) > 0) {
            max = z;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maximum(3,4,5));
        System.out.println(maximum(3.4,4.2,3.1));
        System.out.println(maximum("pap", "adf", "as"));
    }
}
