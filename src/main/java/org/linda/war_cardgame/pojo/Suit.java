package org.linda.war_cardgame.pojo;

public class Suit {
	private String name;

	public Suit() {

	}

	public Suit(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name + "";
	}

}
