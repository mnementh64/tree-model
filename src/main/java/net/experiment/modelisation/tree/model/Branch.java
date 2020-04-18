package net.experiment.modelisation.tree.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.experiment.modelisation.tree.geometry.Vector;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    @JsonProperty
    private int attachedPosition;
    @JsonProperty
    private Vector vector;
    @JsonProperty
    private int section;
    @JsonProperty
    private List<Branch> branches = new ArrayList<>();
    @JsonProperty
    private Branch extension;

    public Branch() {
    }

    public Branch(int attachedPosition, Vector vector, int section) {
        this.attachedPosition = attachedPosition;
        this.vector = vector;
        this.section = section;
    }

    public void addBranch(Branch branch) {
        this.branches.add(branch);
    }
}
