package com.carbon.fr.treatment.map.service;

import com.carbon.fr.treatment.adventurer.model.Adventurer;
import com.carbon.fr.treatment.map.model.Box;

public class BoxService {

	/**
	 * cette méthode permet de verifier si la box est accessible ou pas
	 * 
	 * @param box
	 * @return true si elle est accessible sinon false
	 */
	public static boolean isBoxAccessible(Box box) {
		return !box.isMountain() && box.getAdventurer() == null;
	}

	/**
	 * cette méthode de rammaser les trésors
	 * 
	 * @param box
	 * @param adventurer
	 */
	public static void pickUpTreasure(Box box, Adventurer adventurer) {
		if (box.getNbTreasure() > 0) {
			box.setNbTreasure(box.getNbTreasure() - 1);
			adventurer.setNbTreasure(adventurer.getNbTreasure() + 1);
		}

	}

}
