package com.carbon.fr.treatment.adventurer.service;

import static com.carbon.fr.treatment.orientation.Orientation.EAST;
import static com.carbon.fr.treatment.orientation.Orientation.NORTH;
import static com.carbon.fr.treatment.orientation.Orientation.SOUTH;
import static com.carbon.fr.treatment.orientation.Orientation.WEST;

import com.carbon.fr.treatment.adventurer.model.Adventurer;
import com.carbon.fr.treatment.adventurer.movement.AdventurerMovement;
import com.carbon.fr.treatment.constant.ExceptionMessage;
import com.carbon.fr.treatment.coordinates.Coordinates;
import com.carbon.fr.treatment.exceptions.OrientationNotFoundException;
import com.carbon.fr.treatment.map.model.Box;
import com.carbon.fr.treatment.map.service.BoxService;
import com.carbon.fr.treatment.map.util.MapUtil;
import com.carbon.fr.treatment.orientation.Orientation;

public class AdventurerDeplacementService {

	/**
	 * Cette methode retourne les prochains coordonnees sur lesquels l'aventurier
	 * doit avancer
	 * 
	 * @param adventurer
	 * @return les coordonnees sinon une exception
	 */
	public static Coordinates getNextCoordinates(Adventurer adventurer) {
		int horizontalAxis;
		int verticalAxis;

		switch (adventurer.getOrientation()) {
		case SOUTH:
			horizontalAxis = adventurer.getCoordinates().getHorizontalAxis();
			verticalAxis = adventurer.getCoordinates().getVerticalAxis() + 1;
			break;
		case NORTH:
			horizontalAxis = adventurer.getCoordinates().getHorizontalAxis();
			verticalAxis = adventurer.getCoordinates().getVerticalAxis() - 1;
			break;
		case WEST:
			horizontalAxis = adventurer.getCoordinates().getHorizontalAxis() - 1;
			verticalAxis = adventurer.getCoordinates().getVerticalAxis();
			break;
		case EAST:
			horizontalAxis = adventurer.getCoordinates().getHorizontalAxis() + 1;
			verticalAxis = adventurer.getCoordinates().getVerticalAxis();
			break;
		default:
			throw new OrientationNotFoundException(
					ExceptionMessage.orientationNotFound + " '" + adventurer.getOrientation().getOrientation());
		}

		Coordinates coordinates = new Coordinates(verticalAxis, horizontalAxis);
		return coordinates;
	}

	/**
	 * Cette methode permet � l'aventurier de pivoter de 90� vers la droite
	 * 
	 * @param orientation
	 * @return la nouvelle orientation
	 */

	public static Orientation RotateRight(Orientation orientation) {
		Orientation nextOrientation = null;
		switch (orientation) {
		case NORTH:
			nextOrientation = EAST;
			break;
		case EAST:
			nextOrientation = SOUTH;
			break;
		case SOUTH:
			nextOrientation = WEST;
			break;
		case WEST:
			nextOrientation = NORTH;
			break;
		default:
			throw new OrientationNotFoundException(
					ExceptionMessage.orientationNotFound + " '" + orientation.getOrientation());
		}
		return nextOrientation;
	}

	/**
	 * Cette methode permet � l'aventurier de pivoter de 90� vers la gauche
	 * 
	 * @param orientation
	 * @return la nouvelle orientation
	 */

	public static Orientation RotateLeft(Orientation orientation) {
		Orientation nextOrientation = null;
		switch (orientation) {
		case NORTH:
			nextOrientation = WEST;
			break;
		case EAST:
			nextOrientation = NORTH;
			break;
		case SOUTH:
			nextOrientation = EAST;
			break;
		case WEST:
			nextOrientation = SOUTH;
			break;
		default:
			throw new OrientationNotFoundException(
					ExceptionMessage.orientationNotFound + " '" + orientation.getOrientation());
		}
		return nextOrientation;
	}

	/**
	 * cette methode permet a l'aventurier d'avancer d'une case sur la carte. Si la
	 * case est une montagne ou il y'a deja un aventurier ou les coordonnees ne sont
	 * pas definies sur la carte le mouvement est ignoree
	 * 
	 * @param adventurer
	 * @param map
	 */
	public static void movingForward(Adventurer adventurer, Box map[][]) {
		Coordinates nextCoordinates = getNextCoordinates(adventurer);
		if (!MapUtil.getDimensions(map).isCoordinatesExist(nextCoordinates)) {
			System.out.println("===>" + adventurer.getName() + " a ignore  les coordonnees "
					+ nextCoordinates.toString() + " car ils ne sont pas definies sur la carte ");
		} else if (!BoxService
				.isBoxAccessible(map[nextCoordinates.getHorizontalAxis()][nextCoordinates.getVerticalAxis()])) {
			System.out.println("===>" + adventurer.getName() + " a ignore les coordonnees " + nextCoordinates.toString()
					+ " car ils sont inaccessibles");
		} else {
			map[adventurer.getCoordinates().getHorizontalAxis()][adventurer.getCoordinates().getVerticalAxis()]
					.setAdventurer(null);
			adventurer.setCoordinates(nextCoordinates);
			map[nextCoordinates.getHorizontalAxis()][nextCoordinates.getVerticalAxis()].setAdventurer(adventurer);
			BoxService.pickUpTreasure(
					map[adventurer.getCoordinates().getHorizontalAxis()][adventurer.getCoordinates().getVerticalAxis()],
					adventurer);
			System.out.println("===>" + adventurer.getName() + " vient d'avancer sur la case"
					+ adventurer.getCoordinates().toString() + " avec l'orientation " + adventurer.getOrientation());

		}
	}

	/**
	 * cette methode permet a l'aventurier d'excuter le mouvement en cours
	 * @param adventurer
	 * @param map
	 * @param adventurerMovement
	 */
	public static void executeMouvements(Adventurer adventurer, Box map[][], AdventurerMovement adventurerMovement) {
		switch (adventurerMovement) {
		case MOVING_FORWARD:
			movingForward(adventurer, map);
			break;
		case TURN_LEFT:
			adventurer.setOrientation(RotateLeft(adventurer.getOrientation()));
			System.out.println("===>" + adventurer.getName() + " a tourné a gauche vers l'orientation "
					+ adventurer.getOrientation());
			break;
		case TURN_RIGHT:
			adventurer.setOrientation(RotateRight(adventurer.getOrientation()));
			System.out.println("===>" + adventurer.getName() + " a tourné à droite vers l'orientation"
					+ adventurer.getOrientation());
			break;
		default:
			System.out.println("Le mouvement definit ne peut pas être effectué");
			break;
		}
	}

}
