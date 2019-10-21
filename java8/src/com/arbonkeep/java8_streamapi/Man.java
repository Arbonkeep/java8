package com.arbonkeep.java8_streamapi;

public class Man {
    private Godness godness;

    public Man() {

    }

    public Man(Godness godness) {
        super();
        this.godness = godness;
    }

    public Godness getGodness() {
        return godness;
    }

    public void setGodness(Godness godness) {
        this.godness = godness;
    }

    @Override
    public String toString() {
        return "Man{" +
                "godness=" + godness +
                '}';
    }
}
