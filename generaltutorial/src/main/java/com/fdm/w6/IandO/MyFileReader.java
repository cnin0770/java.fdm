package com.fdm.w6.IandO;

import java.io.*;

public class MyFileReader {
    private File file;

    MyFileReader(File file){
        super();
        this.file = file;
    }

    void read() {
        String line = null;
        int i = 0;

        try
        {
            FileReader fileReader = new FileReader(this.file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (true) {
                line = bufferedReader.readLine();
                if (line == null)
                    break;
                else
                    System.out.println(i++ + " read line: " + line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
