package com.carbon.fr.treatment.orientation;

import com.carbon.fr.treatment.constant.ExceptionMessage;
import com.carbon.fr.treatment.exceptions.InvalidOrientationException;

public class OrientationService {
	public static Orientation getOrientation(String orientation) {
		switch (orientation) {
		case "E":
			return Orientation.EAST;
		case "N":
			return Orientation.NORTH;
		case "S":
			return Orientation.SOUTH;
		case "W":
			return Orientation.WEST;
		default:
			throw new InvalidOrientationException(ExceptionMessage.orientationNotFound);
		}
	}

}
