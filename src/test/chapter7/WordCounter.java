package test.chapter7;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by YangDeming on 2017/12/28.
 */
public class WordCounter {
    private final int count;
    private final boolean flag;

    public WordCounter(int count, boolean flag) {
        this.count = count;
        this.flag = flag;
    }

    public WordCounter acccumlate(Character c) {
        if (Character.isWhitespace(c)) {
            return flag ? this : new WordCounter(count, true);
        } else {
            return flag ? new WordCounter(count + 1, false) : this;
        }
    }
    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(count+wordCounter.count,wordCounter.flag);
    }

    public int getCount() {
        return count;
    }

    private static int countWord(Stream<Character> stream){
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::acccumlate, WordCounter::combine);
        return wordCounter.getCount();
    }

    public static void main(String[] args) {
        String s = "sfs sfs sfs";
//        Stream<Character> stream = IntStream.range(0, s.length()).mapToObj(s::charAt);
//
        Spliterator<Character> spliterator = new WordCountSplinterator(s);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);

        System.out.println(countWord(stream));
//        System.out.println(countWord(stream.parallel()));
    }
}
