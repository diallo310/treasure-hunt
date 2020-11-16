package com.carbon.fr.treatment.coordinates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates {
	private int verticalAxis;
	private int horizontalAxis;

	public boolean isCoordinatesExist(Coordinates coordinates) {
		return coordinates.getHorizontalAxis() >= 0 && coordinates.getVerticalAxis() >= 0
				&& coordinates.getHorizontalAxis() < this.horizontalAxis
				&& coordinates.getVerticalAxis() < this.verticalAxis;
	}

	@Override
	public String toString() {
		return "[" + horizontalAxis + "," + verticalAxis + "]";
	}

}
