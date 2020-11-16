package com.carbon.fr.treatment.adventurer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.carbon.fr.treatment.adventurer.model.Adventurer;
import com.carbon.fr.treatment.coordinates.Coordinates;
import com.carbon.fr.treatment.exceptions.MountainExistException;
import com.carbon.fr.treatment.map.model.Box;
import com.carbon.fr.treatment.map.service.BoxService;
import com.carbon.fr.treatment.map.util.MapUtil;
import com.carbon.fr.parser.Line;

public class AdventurerService {
	/**
	 * Cette methode permet d'ajouter les aventurier sur la carte
	 * 
	 * @param map
	 * @param lines
	 * @return une liste d'aventuriers
	 */
	public static List<Adventurer> addAdventurer(Box map[][], List<String> lines) {
		List<Adventurer> adventurers = new ArrayList<Adventurer>();
		List<String> adventurersLines = Line.GeLinesWithAdventurerPrefix(lines);
		adventurersLines.forEach(adventurerLine -> {
			Coordinates coordinates = Line.getAdventurer(adventurerLine).getCoordinates();
			MapUtil.checkCoordinatesExist(map, coordinates);
			if (!BoxService.isBoxAccessible(map[coordinates.getHorizontalAxis()][coordinates.getVerticalAxis()])) {
				throw new MountainExistException("Il est impossible d'ajouter un aventurier sur les cordonn�es ("
						+ coordinates.getHorizontalAxis() + "," + coordinates.getVerticalAxis()
						+ "), il y'a d�ja une montagne ou un aventurier");
			} else {
				Adventurer adventurer = Line.getAdventurer(adventurerLine);

				map[coordinates.getHorizontalAxis()][coordinates.getVerticalAxis()].setAdventurer(adventurer);
				BoxService.pickUpTreasure(map[coordinates.getHorizontalAxis()][coordinates.getVerticalAxis()],
						adventurer);
				adventurers.add(adventurer);
			}

		});
		return adventurers;
	}

	/**
	 * cette m�thode permet de lancer la recherche des tr�sors
	 * 
	 * @param map
	 * @param adventurers
	 */

	public static void StartSearchTreasure(Box map[][], List<Adventurer> adventurers) {
		if (adventurers.stream().filter(adventurer -> adventurer.getMovements().size() > 0).collect(Collectors.toList())
				.size() > 0) {
			adventurers.stream().filter(adventurer -> adventurer.getMovements().size() > 0).forEach(adventurer -> {
				AdventurerDeplacementService.executeMouvements(adventurer, map, adventurer.getMovements().get(0));
				adventurer.getMovements().remove(0);
				removeAdventurer(adventurers, map);
			});
			StartSearchTreasure(map, adventurers);
		}

	}

	/**
	 * Cette methode permet de supprimer les aventurier qui n'ont plus de sequences.
	 * 
	 * @param adventurers
	 * @param map
	 */
	public static void removeAdventurer(List<Adventurer> adventurers, Box[][] map) {
		adventurers.stream().filter(adventurer -> adventurer.getMovements().size() == 0).forEach(adventurer -> {
			if (map[adventurer.getCoordinates().getHorizontalAxis()][adventurer.getCoordinates().getVerticalAxis()]
					.getAdventurer() != null) {
				map[adventurer.getCoordinates().getHorizontalAxis()][adventurer.getCoordinates().getVerticalAxis()]
						.setAdventurer(null);
			}
		});
	}
}
