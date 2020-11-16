package com.carbon.fr.treatment.adventurer.movement;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdventurerMovement {
	MOVING_FORWARD("A"), TURN_LEFT("G"), TURN_RIGHT("D");

	private String movemement;

}
