package com.carbon.fr.treatment.adventurer.movement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.carbon.fr.treatment.adventurer.movement.AdventurerMovement;
import com.carbon.fr.treatment.adventurer.movement.AdventurerMovementService;
import com.carbon.fr.treatment.exceptions.UnexpectedMovementException;

public class AdventurerMovementServiceTest {
	@Test
	public void getAdventurerFromChar_shouldReturnTheMovementType() {
		char movingFoward = 'A';
		char turnRight = 'D';
		char turnLeft = 'G';
		Assertions.assertEquals(AdventurerMovementService.getAdventurerFromChar(movingFoward),
				AdventurerMovement.MOVING_FORWARD);
		Assertions.assertEquals(AdventurerMovementService.getAdventurerFromChar(turnRight),
				AdventurerMovement.TURN_RIGHT);
		Assertions.assertEquals(AdventurerMovementService.getAdventurerFromChar(turnLeft),
				AdventurerMovement.TURN_LEFT);
	}

	@Test
	public void getAdventurerFromChar_shouldReturnTrowException() {
		char movement = 'H';
		Assertions.assertThrows(UnexpectedMovementException.class,
				() -> AdventurerMovementService.getAdventurerFromChar(movement));
	}

}
