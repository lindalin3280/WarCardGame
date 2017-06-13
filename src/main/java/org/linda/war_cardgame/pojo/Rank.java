package org.linda.war_cardgame.pojo;

public class Rank {
	private int value;

	public Rank() {
		
	}
	
	public Rank(int value) {
		super();
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value + "";
	}
	
}
