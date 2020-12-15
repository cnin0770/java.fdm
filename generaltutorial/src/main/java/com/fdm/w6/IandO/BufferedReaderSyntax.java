package com.fdm.w6.IandO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class BufferedReaderSyntax {
    String file_name = "./src/main/resources/temp.md";
    String first_line = null;

    String readFirstLineOfFile() {
        try {
            Reader reader = new FileReader(file_name);
            BufferedReader bufferedReader = new BufferedReader(reader);
            first_line = bufferedReader.readLine();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return first_line;
    }

    List<String> readWholeFileAsList() {
        List<String> lines = new ArrayList<String>();

        try {
            Reader reader = new FileReader(file_name);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            while (line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return lines;
    }
}
