package com.carbon.fr;

import java.io.IOException;
import java.util.List;

import com.carbon.fr.treatment.adventurer.model.Adventurer;
import com.carbon.fr.treatment.adventurer.service.AdventurerService;
import com.carbon.fr.io.FileReader;
import com.carbon.fr.io.FileWritter;
import com.carbon.fr.treatment.map.model.Box;
import com.carbon.fr.treatment.map.service.MapService;

public class Main {

	public static void main(String[] args) throws IOException {
		Params params = Params.getInstance();
		params.getInputFile(args);
		List<String> linesInput = FileReader.getLines(params.inputFile);
		Box[][] map = MapService.fillIn(linesInput);
		List<Adventurer> adventurers = AdventurerService.addAdventurer(map, linesInput);
		AdventurerService.StartSearchTreasure(map, adventurers);
		FileWritter.saveAll(map, adventurers, params.outputFile);
	}
}
