package com.carbon.fr.treatment.map.service;

import java.util.List;

import com.carbon.fr.treatment.coordinates.Coordinates;
import com.carbon.fr.treatment.exceptions.NoMapSizeException;
import com.carbon.fr.treatment.map.model.Box;
import com.carbon.fr.treatment.mountain.MountainService;
import com.carbon.fr.parser.Line;
import com.carbon.fr.treatment.treasure.TreasureService;

public class MapService {

	/**
	 * cette methode permet d'ajouter les montagnes et les trésors sur la carte
	 * @param lines
	 * @return la carte  remplie
	 * @throws NoMapSizeException 
	 */
	public static Box[][] fillIn(List<String> lines) {
		Box[][] map = createMap(lines);
		MountainService.addMountains(map, lines);
		TreasureService.addTreasure(map, lines);
		return map;
	}

	/**
	 * Cette methode recupére les dimenssions de la carte et la crée 
	 * @param lines
	 * @return une carte
	 * @throws NoMapSizeException 
	 */
	public static Box[][] createMap(List<String> lines){
		Coordinates coordinates = Line.GeLinesWithCardSizePrefix(lines);
		Box[][] map = createBox(coordinates.getHorizontalAxis(), coordinates.getVerticalAxis());
		return map;
	}

	/**
	 * Cette methode permet de créer la carte en fonction des dimenssions fournies
	 * @param height
	 * @param width
	 * @return une carte
	 */
	public static Box[][] createBox(int height, int width) {
		Box[][] boxes = new Box[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				boxes[i][j] = new Box();
			}
		}
		return boxes;
	}

}
