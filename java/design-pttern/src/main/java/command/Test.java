package command;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/12/29 17:44
 */
public class Test {
    public static void main(String[] args) throws Exception {
        System.out.println(delZero("700011100"));
        String str = "还";
        System.out.println(new String(str.getBytes("utf-8"), "gbk"));
        System.out.println(new String(str.getBytes("ISO8859-1"), "ISO8859-1"));
    }

    public static String delZero(String str) {
        for (String r = str; ; ) {
            if (r.endsWith("0")) {
                r = r.substring(0, r.length() - 1);
            } else {
                return r;
            }
        }
    }
}
