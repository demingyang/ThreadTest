package test.chapter2;


/**
 * Created by YangDeming on 2017/12/19.
 */
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}

