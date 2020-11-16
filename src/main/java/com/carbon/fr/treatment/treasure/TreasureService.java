package com.carbon.fr.treatment.treasure;

import java.util.List;

import com.carbon.fr.treatment.coordinates.Coordinates;
import com.carbon.fr.treatment.exceptions.MountainExistException;
import com.carbon.fr.treatment.map.model.Box;
import com.carbon.fr.treatment.map.util.MapUtil;
import com.carbon.fr.parser.Line;

public class TreasureService {
	/**
	 * Cette méthode permet de charger les trésors sur la carte
	 * 
	 * @param map
	 * @param lines
	 */
	public static void addTreasure(Box map[][], List<String> lines) {
		List<String> treasures = Line.GeLinesWithTreasurePrefix(lines);
		treasures.forEach(treasure -> {
			Coordinates coordinates = Line.getCoordinates(treasure);
			MapUtil.checkCoordinatesExist(map, coordinates);
			if (!map[coordinates.getHorizontalAxis()][coordinates.getVerticalAxis()].isMountain()) {
				map[coordinates.getHorizontalAxis()][coordinates.getVerticalAxis()]
						.setNbTreasure(Line.getNumberTreasure(treasure));
			} else {
				throw new MountainExistException(
						"Il est impossible d'ajouter un trésor sur les cordonnées (" + coordinates.getHorizontalAxis()
								+ "," + coordinates.getVerticalAxis() + "), il y'a déjà une montagne");
			}
		});

	}
}
