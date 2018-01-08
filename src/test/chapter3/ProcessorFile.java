package test.chapter3;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by YangDeming on 2017/12/19.
 */
public class ProcessorFile {
    public static String processorFile(BufferReaderProcessor brp) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("E:/codeTest/src/data")));
        return brp.process(br);
    }

    @Test
    public void test() throws IOException {
        String aline = processorFile((BufferedReader br) -> br.readLine());
        System.out.println(aline);
    }
}


