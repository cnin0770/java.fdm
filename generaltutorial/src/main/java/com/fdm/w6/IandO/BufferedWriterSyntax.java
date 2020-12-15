package com.fdm.w6.IandO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class BufferedWriterSyntax {
    void writeLineToFile(String line) {
        String file_name = "./src/main/resources/temp.md";
        try {
            Writer writer = new FileWriter(file_name, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(line);
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
