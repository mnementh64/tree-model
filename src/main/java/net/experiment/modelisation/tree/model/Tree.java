package net.experiment.modelisation.tree.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tree {

    @JsonProperty
    private Segment initialSegment;

    public Tree() {}
    public Tree(Segment initialSegment) {
        this.initialSegment = initialSegment;
    }

    public Segment getInitialSegment() {
        return initialSegment;
    }
}
