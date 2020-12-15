package com.fdm.w6.IandO;

import java.io.*;

public class MyFileInputStream {
    private File file;

    MyFileInputStream(File file) {
        super();
        this.file = file;
    }

    void read() {
        int bytes = 0;
        int i = 0;

        try {
            FileInputStream fileInputStream = new FileInputStream(this.file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            while (true) {
                bytes = bufferedInputStream.read();
                if (bytes < 0)
                    break;
                else
                    System.out.format(++i + " read bytes: 0x%02x\n", bytes);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
