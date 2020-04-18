package net.experiment.modelisation.tree.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.experiment.modelisation.tree.geometry.Point;

public class Tree {

    @JsonProperty
    private Branch trunk;

    public Tree() {
    }

    public Tree(Branch trunk) {
        this.trunk = trunk;
    }

    public void resolvePositions(Point origin) {
        trunk.resolvePositionFrom(origin);
    }

    public void displayPositions() {
        trunk.displayPositions(1);
    }

    public Branch getTrunk() {
        return trunk;
    }
}
