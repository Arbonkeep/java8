package com.arbonkeep.java8_streamapi;

import java.util.Optional;

public class NewMan {
    private Optional<Godness> godness = Optional.ofNullable(null);

    public NewMan() {}

    public NewMan(Optional<Godness> godness) {
        this.godness = godness;
    }

    public Optional<Godness> getGodness() {
        return godness;
    }

    public void setOp(Optional<Godness> godness) {
        this.godness = godness;
    }

    @Override
    public String toString() {
        return "NewMan{" +
                "op=" + godness +
                '}';
    }
}
