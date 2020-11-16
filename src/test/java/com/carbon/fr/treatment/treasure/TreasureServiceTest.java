package com.carbon.fr.treatment.treasure;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.carbon.fr.treatment.map.model.Box;
import com.carbon.fr.treatment.map.service.MapService;
import com.carbon.fr.treatment.treasure.TreasureService;

public class TreasureServiceTest {
	@Test
	public void shouldAddTreasure() {
		Box[][] map = MapService.createBox(3, 4);
		List<String> lines = new ArrayList<>();
		lines.add("T - 0 - 3 - 2");
		lines.add("T - 1 - 3 - 3");
		TreasureService.addTreasure(map, lines);
		Assertions.assertEquals(map[0][3].getNbTreasure(), 2);
		Assertions.assertEquals(map[1][3].getNbTreasure(), 3);

	}
}
