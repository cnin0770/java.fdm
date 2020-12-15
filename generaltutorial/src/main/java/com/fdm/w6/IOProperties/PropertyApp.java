package com.fdm.w6.IOProperties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class PropertyApp {
    private String name;
    private String type;
    private String age;

    void getPropValues(){
        InputStream inputStream = null;
        try {
            Properties prop = new Properties();
            String propertiesFileName = "animal.properties";

            inputStream = this.getClass().getClassLoader().getResourceAsStream(propertiesFileName);

            if (inputStream != null) prop.load(inputStream);
            else throw new FileNotFoundException(String.format("file %s not found.", propertiesFileName));

            this.name = prop.getProperty("name");
            this.type = prop.getProperty("type");
            this.age = prop.getProperty("age");

            inputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    void showProperty(){
        this.getPropValues();
        System.out.println(this.toString());
    }

    public String toString(){
        return String.format("name: %s, type: %s, age: %s", this.name, this.type, this.age);
    }
}
