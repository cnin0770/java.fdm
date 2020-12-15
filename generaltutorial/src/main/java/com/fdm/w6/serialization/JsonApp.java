package com.fdm.w6.serialization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonApp {
    private static final String FILE_NAME = "./src/main/resources/movies.json";
    public static void serialize(List<Movie> movie_list) {
        ObjectMapper mapper = new ObjectMapper();
        File destination = new File(FILE_NAME);

        try {
            mapper.writeValue(destination, movie_list);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<Movie> deserialize() {
        ObjectMapper mapper = new ObjectMapper();
        File source = new File(FILE_NAME);
        List<Movie> movies = new ArrayList<Movie>();
        try {
            movies = mapper.readValue(source, new TypeReference<List<Movie>>(){});
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return movies;
    }
}
