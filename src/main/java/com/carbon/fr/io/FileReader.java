package com.carbon.fr.io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.carbon.fr.treatment.constant.ExceptionMessage;
import com.carbon.fr.treatment.exceptions.FileNotFoundException;

/**
 * Cette permet d'extraire les ligne du fichier d'entr�e
 * 
 * @author Dell
 *
 */
public class FileReader {
	static Charset charset = Charset.defaultCharset();

	public static List<String> getLines(String file) {

		List<String> lines = new ArrayList<String>();
		try {
			Path path = Paths.get(file);
			lines = Files.readAllLines(path, charset);
		} catch (IOException | InvalidPathException ex) {
			throw new FileNotFoundException(ExceptionMessage.fileNotFound);
		}
		return lines;

	}

}
