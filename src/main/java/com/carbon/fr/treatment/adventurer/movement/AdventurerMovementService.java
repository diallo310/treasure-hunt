package com.carbon.fr.treatment.adventurer.movement;

import com.carbon.fr.treatment.constant.ExceptionMessage;
import com.carbon.fr.treatment.exceptions.UnexpectedMovementException;

public class AdventurerMovementService {
	/**
	 * En fonction du caractere de la sequence de mouvement de l'aventurier on
	 * retourne le type du mouvement
	 * 
	 * @param c
	 * @return Une enume	ration du type de movement
	 * @throws UnexpectedMovementException
	 */
	public static AdventurerMovement getAdventurerFromChar(char c) throws UnexpectedMovementException {
		switch (c) {
		case 'A':
			return AdventurerMovement.MOVING_FORWARD;
		case 'G':
			return AdventurerMovement.TURN_LEFT;
		case 'D':
			return AdventurerMovement.TURN_RIGHT;
		}
		throw new UnexpectedMovementException(ExceptionMessage.unexpectedMovement + " '" + c + "'");
	}

}
