package test.chapter2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by YangDeming on 2017/12/19.
 */
public class ChoseApple {
//    @Test
//    public void choseRedApple(){
//        List<Apple> list  = new ArrayList<>();
//        for (Apple apple:list) {
//            if("green".equals(apple.getColor())){
//                list.add(apple);
//            }
//        }
//    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    @Test
    public void test() {
        List<Apple> inventory = Arrays.asList(new Apple("green"), new Apple("red"));
        List<Apple> redApple = filter(inventory, (Apple a) -> "red".equals(a.getColor()));
        System.out.println(redApple.get(0).getColor());
    }


}
