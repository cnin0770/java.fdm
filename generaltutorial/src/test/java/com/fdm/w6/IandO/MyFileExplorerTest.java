package com.fdm.w6.IandO;

import org.junit.Test;

public class MyFileExplorerTest {

    @Test
    public void file_test(){
        MyFileExplorer myFileExplorer = new MyFileExplorer("./src/resources/temp.xml");
        myFileExplorer.showFileProperties();
        System.out.println();
        myFileExplorer.readFileAsCharacters();
        myFileExplorer.readFileAsBytes();
    }
}
