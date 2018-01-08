package test.chapter3;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by YangDeming on 2017/12/19.
 */
@FunctionalInterface
public interface BufferReaderProcessor {
    String process(BufferedReader br) throws IOException;
}
