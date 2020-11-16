package com.carbon.fr.treatment.map.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.carbon.fr.treatment.map.model.Box;
import com.carbon.fr.treatment.map.service.MapService;

public class MapServiceTest {
	@Test
	public void shouldCreateMap() {
		Box[][] map = MapService.createBox(4, 3);
		Assertions.assertEquals(map.length, 4);
		Assertions.assertEquals(map[0].length, 3);
	}

}
