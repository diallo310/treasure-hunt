package com.carbon.fr.treatment.mountain;

import java.util.List;

import com.carbon.fr.treatment.coordinates.Coordinates;
import com.carbon.fr.treatment.map.model.Box;
import com.carbon.fr.treatment.map.util.MapUtil;
import com.carbon.fr.parser.Line;

public class MountainService {

	/**
	 * Cette méthode permet de charger les montagnes sur la carte
	 * 
	 * @param map
	 * @param lines
	 */
	public static void addMountains(Box map[][], List<String> lines) {
		List<String> mountains = Line.GeLinesWithMountainPrefix(lines);
		mountains.forEach(mountain -> {
			Coordinates coordinates = Line.getCoordinates(mountain);
			MapUtil.checkCoordinatesExist(map, coordinates);
			map[coordinates.getHorizontalAxis()][coordinates.getVerticalAxis()].setMountain(true);
		});
	}

}
