package com.carbon.fr.treatment.adventurer.model;

import java.util.List;

import com.carbon.fr.treatment.adventurer.movement.AdventurerMovement;
import com.carbon.fr.treatment.coordinates.Coordinates;
import com.carbon.fr.treatment.orientation.Orientation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Adventurer {
	private String name;
	private Coordinates coordinates;
	private Orientation orientation;
	private List<AdventurerMovement> movements;
	private int nbTreasure;
}
