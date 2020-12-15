package com.fdm.w6.IandO;

import java.io.File;

public class MyFileExplorer {
    private String file_name;
    private File file;

    MyFileExplorer(String file_name) {
        super();
        this.file_name = file_name;
        this.file = new File(file_name);
    }

    void showFileProperties() {
        System.out.println("file exits() = " + file.exists());
        System.out.println("file.isFile() = " + file.isFile());
        System.out.println("file.getAbsolutePat() = " + file.getAbsolutePath());

        System.out.println("file.canWrite() = " + file.canWrite());
        file.setWritable(true);
        System.out.println("file.canWrite() = " + file.canWrite());

        long file_size = file.getTotalSpace();
        file_size = file_size / 1000000;
        System.out.println("file.getTotalSpace(in MB) = " + file_size);
    }

    void readFileAsCharacters() {
        MyFileReader myFileReader = new MyFileReader(this.file);
        myFileReader.read();
    }

    void readFileAsBytes() {
        MyFileInputStream myFileInputStream = new MyFileInputStream(this.file);
        myFileInputStream.read();
    }
}
