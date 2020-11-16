package com.carbon.fr.treatment.adventurer.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.carbon.fr.treatment.adventurer.service.AdventurerService;

public class AdventurerServiceTest {
	@Test
	public void shouldAddAdventurer() {
		com.carbon.fr.treatment.map.model.Box[][] map = com.carbon.fr.treatment.map.service.MapService.createBox(4, 3);
		List<String> lines = new ArrayList<>();
		lines.add("A - Lara - 1 - 1 - S - AADADAGGA");
		lines.add("A - Hawa - 1 - 2 - N - AADADAGGA");
		AdventurerService.addAdventurer(map, lines);
		Assertions.assertEquals(map[1][1].getAdventurer().getName(), "Lara");
		Assertions.assertEquals(map[1][2].getAdventurer().getName(), "Hawa");
	}
}