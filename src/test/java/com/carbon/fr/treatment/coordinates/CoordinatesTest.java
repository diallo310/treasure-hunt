package com.carbon.fr.treatment.coordinates;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.carbon.fr.treatment.coordinates.Coordinates;

public class CoordinatesTest {

	@Test
	public void verifyCoordinates() {
		Coordinates map = new Coordinates(3, 4);
		Coordinates c0 = new Coordinates(2, 2);
		Coordinates c1 = new Coordinates(1, 4);

		Assertions.assertTrue(map.isCoordinatesExist(c0));
		Assertions.assertFalse(map.isCoordinatesExist(c1));

	}
}
