package com.carbon.fr.treatment.orientation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Orientation {
	WEST("W"), EAST("E"), NORTH("N"), SOUTH("S");

	private String orientation;
}
