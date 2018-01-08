package test.chapter7;

/**
 * 一个String中的单词数
 * Created by YangDeming on 2017/12/28.
 */
public class CountWord {
    public static void main(String[] args) {
        int i = countWord("dfsadfdsa  afsa");
        System.out.println(i);
    }

    private static int countWord(String s) {
        int count = 0;
        boolean falg = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                falg = true;
            }else {
                if(falg)count++;
                falg = false;
            }
        }
        return count;
    }
}
