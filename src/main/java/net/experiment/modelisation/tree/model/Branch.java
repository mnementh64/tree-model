package net.experiment.modelisation.tree.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Branch {
    @JsonProperty
    /**
     * distance of the branch starting point relative to the beginning of the attached branch
     */
    private int positionOnSegment;
    /**
     * angle in radians relative to the attached branch.
     */
    @JsonProperty
    private double orientation;
    @JsonProperty
    private Segment segment;

    public Branch() {
    }

    public Branch(int positionOnSegment, double orientation, Segment segment) {
        this.positionOnSegment = positionOnSegment;
        this.orientation = orientation;
        this.segment = segment;
    }

    public Segment getSegment() {
        return segment;
    }

    public double getOrientation() {
        return orientation;
    }

    public int getPositionOnSegment() {
        return positionOnSegment;
    }
}
