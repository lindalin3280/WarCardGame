package org.linda.war_cardgame.pojo;

public class Rank implements Comparable<Rank> {
    private int value;
    private String rank;

    public Rank() {

    }

    public Rank(int value) {
        super();
        this.value = value;
    }

    public Rank(int value, String rank) {
        super();
        this.value = value;
        this.rank = rank;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return rank + "";
    }

    @Override
    public int compareTo(Rank o) {
        return this.value - o.value;
    }

}
