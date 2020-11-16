package com.carbon.fr.treatment.map.util;

import com.carbon.fr.treatment.coordinates.Coordinates;
import com.carbon.fr.treatment.exceptions.CoordinateNotFoundException;
import com.carbon.fr.treatment.map.model.Box;

public abstract class MapUtil {
	/**
	 * Cette metthode permet de verifier si les coordornées fournies sont définies
	 * sur la carte
	 * 
	 * @param map
	 * @param coordinates
	 */
	public static void checkCoordinatesExist(Box map[][], Coordinates coordinates) {
		if (!getDimensions(map).isCoordinatesExist(coordinates)) {
			throw new CoordinateNotFoundException(" les coordonées (" + coordinates.getHorizontalAxis() + ","
					+ coordinates.getVerticalAxis() + ") ne sont pas définies sur la carte");
		}
	}

	/**
	 * cette méthode permet de retourner les dimensions de la carte
	 * @param map
	 * @return les dimensions 
	 */
	public static Coordinates getDimensions(Box map[][]) {
		return new Coordinates(map[0].length, map.length);
	}
	
	
	

}
