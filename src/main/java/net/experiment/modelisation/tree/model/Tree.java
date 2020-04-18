package net.experiment.modelisation.tree.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tree {

    @JsonProperty
    private Branch trunk;

    public Tree() {
    }

    public Tree(Branch trunk) {
        this.trunk = trunk;
    }
}
