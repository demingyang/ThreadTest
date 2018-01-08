package test.chapter12;

import java.time.*;
import java.time.temporal.ChronoField;

/**
 * Created by YangDeming on 2017/12/28.
 */
public class DateTest {
    public static void main(String[] args) {
//        LocalDate localDate = LocalDate.of(2014,3,18);
//        System.out.println(localDate.getYear());
//        System.out.println(localDate.lengthOfMonth());
//        System.out.println(localDate);
//        System.out.println(LocalDate.now());
//        System.out.println(localDate.get(ChronoField.YEAR));
//
//
//        LocalTime localTime = LocalTime.now();
//        System.out.println(localTime);
//        System.out.println(LocalDateTime.of(localDate,localTime));
//        System.out.println(localDate.atTime(localTime));

        Instant now = Instant.now();
        System.out.println(now);
        System.out.println(Instant.ofEpochMilli(3));

        Duration duration = Duration.ofMillis(3);
        System.out.println(duration);


    }
}
