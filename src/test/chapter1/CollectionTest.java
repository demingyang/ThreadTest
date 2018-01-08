package test.chapter1;

import java.text.Collator;
import java.util.*;

/**
 * Created by YangDeming on 2017/12/19.
 */
public class CollectionTest {
    public static void main(String[] args) {
//        new Comparator<Human>() {
//            @Override
//            public int compare(Human o1, Human o2) {
//                return o1.getName().compareTo(o2.getName());
//            }
//        };
//        o1.getName().compareTo(o2.getName())
        List<Human> list = Arrays.asList(new Human("李三", 12), new Human("张四", 12),new Human("王六", 12));
        Collections.sort(list, new Comparator<Human>() {
            @Override
            public int compare(Human o1, Human o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        System.out.println(list.get(0).getName());
        System.out.println(list.get(1).getName());
        System.out.println(list.get(2).getName());

        //中文排序
        Comparator chinaCom = Collator.getInstance(Locale.CHINA);
        list.sort(new Comparator<Human>() {
            @Override
            public int compare(Human o1, Human o2) {
                return chinaCom.compare(o1.getName(),o2.getName());
            }
        });
        System.out.println(list.get(0).getName());
        System.out.println(list.get(1).getName());
        System.out.println(list.get(2).getName());
    }

}

