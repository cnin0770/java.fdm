package com.fdm.w6.IandO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Scanner;

class UserRegistry {
    Scanner scanner = new Scanner(System.in);
    static int countCharacter(char c) {
        final String FILE_NAME = "./src/main/resources/temp.xml";
        int char_count = 0;
        int i;
        try {
            Reader reader = new FileReader(FILE_NAME);
            while ((i = reader.read()) > 0) if ((char) i == c) ++char_count;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return char_count;
    }

    void logUser(){
        while (true) {
            System.out.printf("name: ");
            String user_name = scanner.nextLine();
            System.out.printf("address: ");
            String user_address = scanner.nextLine();
            System.out.printf("email: ");
            String user_email = scanner.nextLine();
            User user = new User(user_name, user_address, user_email);
            user.toFile();

            System.out.printf("continue? y/n ");
            String cnt = scanner.nextLine();
            if (!cnt.toLowerCase().equals("y")) break;
        }
    }

    void presentUser(){
        List<User> users = User.readFile();
        for (User i : users)
            System.out.println(i.toString());
    }

    public static void main(String[] args) {
        UserRegistry registry = new UserRegistry();
        registry.logUser();
    }
}
