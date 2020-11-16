package com.carbon.fr.treatment.mountain;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.carbon.fr.treatment.map.model.Box;
import com.carbon.fr.treatment.map.service.MapService;
import com.carbon.fr.treatment.mountain.MountainService;

public class MountainServiceTest {
	@Test
	public static void ShouldAddMountains() {
		Box[][] map = MapService.createBox(3, 4);
		List<String> lines = new ArrayList<>();
		lines.add("M - 2 - 1");
		lines.add("M - 1 - 0");
		MountainService.addMountains(map, lines);
		Assertions.assertTrue(map[2][1].isMountain());
		Assertions.assertTrue(map[1][0].isMountain());
	}

}
