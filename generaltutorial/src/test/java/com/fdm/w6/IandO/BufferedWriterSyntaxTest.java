package com.fdm.w6.IandO;

import com.fdm.w6.IandO.BufferedWriterSyntax;
import org.junit.Test;

public class BufferedWriterSyntaxTest {
    @Test
    public void a_test(){
        BufferedWriterSyntax fileWriter = new BufferedWriterSyntax();
        fileWriter.writeLineToFile("hola amigo.");
    }
}
