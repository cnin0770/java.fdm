package com.fdm.w5.collection;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class GameTest {

	Game yakuza0 = new Game("yakuza0", 100, 80);
	Game pokemon = new Game("Pokemon", 50, 99);
	Game yakuza7 = new Game("yakuza7", 120, 75);
	Game crypt_of_the_necrodancer = new Game("crypt of the necrodancer", 30, 80);
	Game bomber = new Game("bomber", 120.02, 75);

	@Test
	public void return_color() {
		Colora colora = new Colora();
		Color my_colora = colora.get("Blue");
		assertEquals(Color.BLUE, my_colora);
		
		my_colora = colora.get("Yellow");
		assertNull(null, my_colora);
	}

	@Test
	public void game_sets() {
		assertFalse(yakuza0.equals(yakuza7));
		
		Set<Game> games = new HashSet<Game>();
		games.add(yakuza0);
		games.add(yakuza7);
		games.add(pokemon);
		assertEquals(3, games.size());
		
		Set<Game> games2 = new TreeSet<Game>();
		games2.add(yakuza0);
		games2.add(yakuza7);
		games2.add(bomber);
		games2.add(pokemon);
		games2.add(crypt_of_the_necrodancer);
		for (Game gm: games2) System.out.println(gm);
	}
	
	// @Test
	public void game_comparator() {
		GameComparator comparator = new GameComparator();
		Set<Game> games3 = new TreeSet<Game>(comparator);
		games3.add(yakuza0);
		games3.add(yakuza7);
		games3.add(bomber);
		games3.add(pokemon);
		games3.add(crypt_of_the_necrodancer);
		System.out.println(comparator.compare(bomber, yakuza0));
		for (Game gm: games3) System.out.println(gm);
	}
}
