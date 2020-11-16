package com.carbon.fr.parser;

public enum LineType {
	CARTESIZE("C"), MOUNTAIN("M"), TREASURE("T"), ADVENTURER("A");

	private String type;

	private LineType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
