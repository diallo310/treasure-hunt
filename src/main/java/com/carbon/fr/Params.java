package com.carbon.fr;

import com.carbon.fr.treatment.constant.ExceptionMessage;
import com.carbon.fr.treatment.exceptions.InvalidArgumentException;

public class Params {

	public String inputFile = "";
	public String outputFile = "";
	private static Params PARAMS = new Params();

	public static Params getInstance() {
		return PARAMS;
	}

	public void getInputFile(String[] args) {
		switch (args.length) {
		case 1:
			throw new InvalidArgumentException(ExceptionMessage.invalidArgumentException);
		case 2:
			inputFile = args[0];
			outputFile = args[1];
			break;
		default:
		}
	}
}
