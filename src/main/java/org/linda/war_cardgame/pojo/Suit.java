package org.linda.war_cardgame.pojo;

public class Suit {
	private int id;
	private String name;
	

	public Suit() {

	}

	public Suit(int id) {
		super();
		this.id = id;
	}

	public Suit(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
