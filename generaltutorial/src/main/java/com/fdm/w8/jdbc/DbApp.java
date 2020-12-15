package com.fdm.w8.jdbc;

import java.util.ArrayList;
import java.util.List;

class DbApp {
    static ConnectionPool connectionPool = new ConnectionPool();
    static GameDao gameDAO = new GameDao(connectionPool);

    void createTable() {
        gameDAO.createTable();
        Game zelda = new Game(1, "Zelda");
        Game animalCrossing = new Game(2, "Animal Crossing");
        Game doom = new Game(3, "Doom");
        gameDAO.insert(zelda);
        gameDAO.insert(animalCrossing);
        gameDAO.insert(doom);

        Game ssbu = new Game(4, "Super Smash Bros. Ultimate");
        gameDAO.safeInsert(ssbu);

        List<Game> games = new ArrayList<>();
        games.add(new Game(5, "GTA"));
        games.add(new Game(6, "GTA II"));
        games.add(new Game(7, "GTA III"));
        games.add(new Game(8, "GTA IV"));
        gameDAO.batchInsert(games);
    }

    void dropTable() {
        gameDAO.selectName("'; DROP TABLE GAMES --");
    }

    void safeTest() {
        System.out.println(gameDAO.safeSelect("'; DROP TABLE GAMES --").size() == 0);
    }

    void getAll() {
        gameDAO.selectAll().forEach(System.out::println);
    }

    public static void main(String[] args) {
        DbApp app = new DbApp();
        gameDAO.selectAll().forEach(System.out::println);
    }
}
