package test.chapter6;

/**
 * Created by YangDeming on 2017/12/26.
 */
public class PrimeTest {
    public static void main(String[] args) {
        String s = "6月";
        int i = s.indexOf("月");
        System.out.println(i);
        System.out.println(s.substring(0,i));
    }
}
