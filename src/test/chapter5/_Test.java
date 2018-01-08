package test.chapter5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by YangDeming on 2017/12/22.
 */
public class _Test {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950));

        //找出2011年所有的交易并按交易额排序
//        List<Transaction> tr11 = transactions.stream()
//                .filter(a -> a.getYear() == 2011)
//                .sorted(Comparator.comparing(Transaction::getValue))
//                .map(a -> {
//                    System.out.print(a.toString());
//                    return a;
//                }).collect(toList());


        //交易员在哪些不同的城市工作过
//        List<String> trC = transactions.stream()
//                .map(a->a.getTrader().getCity())
//                .distinct()
//                .map(a->{
//                    System.out.println(a);
//                    return a;
//                })
//                .collect(toList());
        //查找所有来自于剑桥的交易员,并按姓名排序
//        List<Trader> traders = transactions.stream()
//                .map(a->a.getTrader())
//                .filter(a->"Cambridge".equals(a.getCity()))
//                .distinct()
//                .sorted(Comparator.comparing(Trader::getName))
//                .map(a->{
//                    System.out.println(a.getName());
//                    return a;
//                })
//                .collect(toList());

        //返回所有交易员的姓名字符串，按字母顺序排序
//        String trdStr = transactions.stream()
//                .map(a->a.getTrader().getName())
//                .distinct()
//                .sorted()
//                .reduce("",(n1,n2)->n1+n2);
//        System.out.println(trdStr);

        //打印生活在剑桥的交易员
        transactions.stream()
                .filter(a->"Cambridge".equals(a.getTrader().getCity()))
                .map(a->a.getValue())
                .forEach(System.out::print);
    }
}
