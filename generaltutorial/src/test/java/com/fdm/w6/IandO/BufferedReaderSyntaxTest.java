package com.fdm.w6.IandO;

import com.fdm.w6.IandO.BufferedReaderSyntax;
import org.junit.Test;

public class BufferedReaderSyntaxTest {
    BufferedReaderSyntax bufferedReaderSyntax = new BufferedReaderSyntax();

    @Test
    public void a_test() {
        System.out.println(bufferedReaderSyntax.readFirstLineOfFile());
    }

    @Test
    public void b_test(){
        for (String line: bufferedReaderSyntax.readWholeFileAsList())
            System.out.println(line);
        System.out.println("Lines read complete.");
    }
}
