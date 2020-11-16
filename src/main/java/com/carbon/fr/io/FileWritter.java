package com.carbon.fr.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.carbon.fr.treatment.adventurer.model.Adventurer;
import com.carbon.fr.treatment.constant.LinePrefix;
import com.carbon.fr.treatment.map.model.Box;

public class FileWritter {
	private final static String separator = " - ";

	public static void saveAdventurer(List<Adventurer> adventurers, String fileName) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
		adventurers.forEach(adventurer -> {
			try {
				writer.append(LinePrefix.adventurer + separator);
				writer.append(adventurer.getName() + separator);
				writer.append(adventurer.getCoordinates().getHorizontalAxis() + separator);
				writer.append(adventurer.getCoordinates().getVerticalAxis() + separator);
				writer.append(adventurer.getOrientation().getOrientation() + separator);
				writer.append(adventurer.getNbTreasure() + "");
				writer.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		writer.close();
	}

	public static void saveMountain(Box map[][], String fileName) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j].isMountain()) {
					writer.append(LinePrefix.mountain + separator);
					writer.append(i + separator);
					writer.append(j + "");
					writer.newLine();
				}
			}
		}
		writer.close();
	}

	public static void saveTresor(Box map[][], String fileName) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j].getNbTreasure() > 0) {
					writer.append(LinePrefix.treasure + separator);
					writer.append(i + separator);
					writer.append(j + separator);
					writer.append(map[i][j].getNbTreasure() + "");
					writer.newLine();
				}
			}
		}
		writer.close();
	}

	public static void saveCardSize(Box map[][], String fileName) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false));
		writer.append(LinePrefix.cardSize + separator);
		writer.append(map.length + separator);
		writer.append(map[0].length + "");
		writer.newLine();
		writer.close();
	}

	public static void saveAll(Box map[][], List<Adventurer> adventurers, String fileName) {
		try {
			saveCardSize(map, fileName);
			saveMountain(map, fileName);
			saveTresor(map, fileName);
			saveAdventurer(adventurers, fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
