package com.fdm.w6.IandO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class User {
    static int user_count = 0;
    static final String FILE_NAME = "./src/main/resources/user_registry.md";
    int user_id;
    String user_name;
    String user_address;
    String user_email;

    User(String name, String address, String email) {
        this.user_id = ++user_count;
        this.user_name = name;
        this.user_address = address;
        this.user_email = email;
    }

    private User(int id, String name, String address, String email){
        this.user_id = id;
        this.user_name = name;
        this.user_address = address;
        this.user_email = email;
    }

    public String toLine() {
        return String.format("%d,%s,%s,%s", this.user_id, this.user_name, this.user_address, this.user_email);
    }

    public String toString(){
        return String.format("- id: %d, name: %s, address: %s, email: %s", this.user_id, this.user_name, this.user_address, this.user_email);
    }

    void toFile() {
        try {
            Writer writer = new FileWriter(FILE_NAME, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(this.toLine());
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    static User parseFromLine(String line) {
        String[] info = line.split(",");
        int user_id = Integer.parseInt(info[0]);
        String user_name = info[1];
        String user_address = info[2];
        String user_email = info[3];
        return new User(user_id, user_name, user_address, user_email);
    }

    static List<User> readFile() {
        List<User> users = new ArrayList<User>();
        try {
            Reader reader = new FileReader(FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            while (line != null) {
                users.add(parseFromLine(line));
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
        return users;
    }
}
