package net.experiment.modelisation.tree.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * All length are set in millimeters
 */
public class Segment {

    @JsonProperty
    private int section;
    @JsonProperty
    private int length;
    @JsonProperty
    private List<Branch> branches = new ArrayList<>();

    public Segment() {
    }

    public Segment(int section, int length) {
        this.section = section;
        this.length = length;
    }

    public void addBranch(Branch branch) {
        branches.add(branch);
    }

    public Stream<Branch> streamOfBranches() {
        return branches.stream();
    }

    public int getLength() {
        return length;
    }

    public int getSection() {
        return section;
    }
}
