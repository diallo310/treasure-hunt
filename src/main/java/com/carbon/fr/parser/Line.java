package com.carbon.fr.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.carbon.fr.treatment.adventurer.model.Adventurer;
import com.carbon.fr.treatment.adventurer.movement.AdventurerMovement;
import com.carbon.fr.treatment.adventurer.movement.AdventurerMovementService;
import com.carbon.fr.treatment.constant.ExceptionMessage;
import com.carbon.fr.treatment.coordinates.Coordinates;
import com.carbon.fr.treatment.exceptions.InvalidLineFormatException;
import com.carbon.fr.treatment.exceptions.NoMapSizeException;
import com.carbon.fr.treatment.orientation.OrientationService;

/**
 * 
 * @author Dell class contenant les méthodes permettant d'extraire les
 *         informations des lignes du fichier d'entrée
 *
 */

public class Line {

	private static final String separator = "-";

	/**
	 * Cette méthode permet d'extraire les coordonnées dans une ligne Les
	 * coordonnées sont fournies sous forme de 2 chiffres séparés par des tiret du
	 * 6*
	 * 
	 * @param line
	 * @return les coordonnées sinon une exception pour signaler que le format de la
	 *         ligne est incorrecte
	 */
	public static Coordinates getCoordinates(String line) {
		String contents[] = getLineData(line);
		if (contents.length == 0) {
			throw new InvalidLineFormatException(ExceptionMessage.invalidFormat);
		}

		return new Coordinates(Integer.parseInt(contents[2].trim()), Integer.parseInt(contents[1].trim()));
	}

	/**
	 * Cette methode retourne le nombre de trésor sur une ligne
	 * 
	 * @param line
	 * @return Le nombre de trésor sinon une exception pour signaler que le format
	 *         de la ligne est incorrecte
	 */
	public static int getNumberTreasure(String line) {
		String contents[] = getLineData(line);
		if (contents.length == 0) {
			throw new InvalidLineFormatException(ExceptionMessage.invalidFormat);
		}
		return Integer.parseInt(contents[3].trim());
	}

	/**
	 * cette méthode retourne les informations de l'aventurier contenues sur une
	 * ligne . Si le format n'est pas respecté une exeception est lévé
	 * 
	 * @param line
	 * @return un aventurier sinon une exception pour signaler que le format de la
	 *         ligne est incorrecte
	 */
	public static Adventurer getAdventurer(String line) {
		String contents[] = getLineData(line);
		if (contents.length == 0 || contents.length < 4) {
			throw new InvalidLineFormatException(ExceptionMessage.invalidFormat);
		}
		List<AdventurerMovement> movements = new ArrayList<>();

		for (char movement : contents[5].trim().toCharArray()) {
			movements.add(AdventurerMovementService.getAdventurerFromChar(movement));
		}

		Adventurer adventurer = new Adventurer();
		adventurer.setName(contents[1].trim());
		adventurer.setCoordinates(
				new Coordinates(Integer.parseInt(contents[3].trim()), Integer.parseInt(contents[2].trim())));
		adventurer.setMovements(movements);
		adventurer.setOrientation(OrientationService.getOrientation(contents[4].trim()));
		return adventurer;
	}

	/**
	 * Cette methode permet de séparer les informations d'une ligne
	 * 
	 * @param line
	 * @return un tableau
	 */

	private static String[] getLineData(String line) {
		line = line.trim();
		return line.split(separator);
	}

	/**
	 * Cette méthode permet de récupérer les lignes avec le prefixe M, c'est à dire
	 * les montagnes
	 * 
	 * @param lines
	 * @return Une liste contentant les lignes
	 */
	public static List<String> GeLinesWithMountainPrefix(List<String> lines) {
		return filterLinesByType(lines, LineType.MOUNTAIN.getType());
	}

	/**
	 * Cette méthode permet de récupérer les lignes avec le prefixe T, c'est à dire
	 * les trésors
	 * 
	 * @param lines
	 * @return Une liste contentant les lignes
	 */
	public static List<String> GeLinesWithTreasurePrefix(List<String> lines) {
		return filterLinesByType(lines, LineType.TREASURE.getType());
	}

	public static Coordinates GeLinesWithCardSizePrefix(List<String> lines) {
		List<String> results = filterLinesByType(lines, LineType.CARTESIZE.getType());
		Coordinates coordinates = getCoordinates(results.get(0));

		if (results.size() > 1 || results.size() == 0 || coordinates.getHorizontalAxis()<2 && coordinates.getHorizontalAxis()<2) {
			throw new NoMapSizeException(ExceptionMessage.noMapSize);
		}
		
		
		return coordinates;
	}

	/**
	 * Cette méthode permet de récupérer les lignes avec le prefixe A, c'est à dire
	 * les aventuriers
	 * 
	 * @param lines
	 * @return Une liste contentant les lignes
	 */

	public static List<String> GeLinesWithAdventurerPrefix(List<String> lines) {
		return filterLinesByType(lines, LineType.ADVENTURER.getType());
	}

	/**
	 * Cette methode permet de filtrer une liste en fonction du type de la ligne,
	 * c'est à dire (Montagne, Trésor, Aventurier)
	 * 
	 * @param lines
	 * @param type
	 * @return une liste filtrée
	 */
	public static List<String> filterLinesByType(List<String> lines, String type) {
		List<String> results = lines.stream().filter(line -> line.startsWith(type)).collect(Collectors.toList());
		return results;
	}

}
