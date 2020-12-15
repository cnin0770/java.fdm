package com.fdm.w6.serialization;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonAppTest {
    List<Movie> movies = new ArrayList<Movie>();

    @Test
    public void a_test(){
        movies.add(new Movie("batman", 50, new Director("Christopher", "Nolan")));
        movies.add(new Movie("aliens", 40, new Director("James", "Cameron")));
        JsonApp.serialize(movies);
    }

    @Test
    public void b_test(){
        movies = JsonApp.deserialize();
        System.out.println(movies.get(0).getDirector());
    }
}
