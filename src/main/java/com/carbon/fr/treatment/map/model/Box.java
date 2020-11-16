package com.carbon.fr.treatment.map.model;

import com.carbon.fr.treatment.adventurer.model.Adventurer;
import com.carbon.fr.treatment.coordinates.Coordinates;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Box {
	private boolean isMountain;
	private int nbTreasure;
	private Adventurer adventurer;
	private Coordinates coordinates;
}

