package com.carbon.fr.treatment.adventurer.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.carbon.fr.treatment.adventurer.model.Adventurer;
import com.carbon.fr.treatment.adventurer.service.AdventurerDeplacementService;
import com.carbon.fr.treatment.coordinates.Coordinates;
import com.carbon.fr.treatment.orientation.Orientation;

public class AdventurerDeplacementServiceTest {
	@Test
	public void rotateRight_shouldReturnNextOrientation() {
		Orientation orientationEast = Orientation.EAST;
		Orientation orientationWest = Orientation.WEST;
		Orientation orientationNorth = Orientation.NORTH;
		Orientation orientationSouth = Orientation.SOUTH;

		Assertions.assertEquals(AdventurerDeplacementService.RotateRight(orientationNorth), orientationEast);
		Assertions.assertEquals(AdventurerDeplacementService.RotateRight(orientationEast), orientationSouth);
		Assertions.assertEquals(AdventurerDeplacementService.RotateRight(orientationSouth), orientationWest);
		Assertions.assertEquals(AdventurerDeplacementService.RotateRight(orientationWest), orientationNorth);
	}

	@Test
	public void rotateLeft_shouldReturnNextOrientation() {
		Orientation orientationEast = Orientation.EAST;
		Orientation orientationWest = Orientation.WEST;
		Orientation orientationNorth = Orientation.NORTH;
		Orientation orientationSouth = Orientation.SOUTH;

		Assertions.assertEquals(AdventurerDeplacementService.RotateLeft(orientationNorth), orientationWest);
		Assertions.assertEquals(AdventurerDeplacementService.RotateLeft(orientationEast), orientationNorth);
		Assertions.assertEquals(AdventurerDeplacementService.RotateLeft(orientationSouth), orientationEast);
		Assertions.assertEquals(AdventurerDeplacementService.RotateLeft(orientationWest), orientationSouth);
	}

	@Test
	public void shouldReturnNextNextCoordinates() {
		Adventurer adventurer = new Adventurer();
		Coordinates coordinates = new Coordinates(2, 2);
		adventurer.setCoordinates(coordinates);
		adventurer.setOrientation(Orientation.SOUTH);
		Assertions.assertEquals(AdventurerDeplacementService.getNextCoordinates(adventurer).getVerticalAxis(), 3);
	}

}
