package net.experiment.modelisation.tree.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.experiment.modelisation.tree.geometry.Point;
import net.experiment.modelisation.tree.geometry.Segment;
import net.experiment.modelisation.tree.geometry.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Branch {
    @JsonProperty
    private int attachedAtPercent;
    @JsonProperty
    private Vector vector;
    @JsonProperty
    private int section;
    @JsonProperty
    private List<Branch> branches = new ArrayList<>();
    @JsonProperty
    private Branch extension;
    @JsonProperty
    private int rank = 0;

    /**
     * The real coordinates of the start / end points of this branch
     */
    @JsonIgnore
    private Segment realSegment;

    public Branch() {
    }

    public Branch(int attachedAtPercent, Vector vector, int section) {
        this.attachedAtPercent = attachedAtPercent;
        this.vector = vector;
        this.section = section;
    }

    public void addBranch(Branch branch) {
        branch.setRank(this.getRank() + 1);
        this.branches.add(branch);
    }

    public Stream<Branch> streamOfBranches() {
        return branches.stream();
    }

    public void resolvePositionFrom(Point origin) {
        realSegment = new Segment(new Point(origin), new Point(origin.translate(vector)));

        // resolve positions for all branches
        for (Branch branch : branches) {
            Point attachedPoint = realSegment.a.translate(vector.scale((double) branch.getAttachedAtPercent() / 100.0));
            branch.resolvePositionFrom(attachedPoint);
        }
    }

    public void displayPositions(int rank) {
        String prefix = IntStream.range(1, rank).mapToObj(i -> "_").collect(Collectors.joining());
        System.out.println(prefix + realSegment.toString());
        if (!branches.isEmpty()) {
            for (Branch branch : branches) {
                branch.displayPositions(rank + 1);
            }
        }
    }

    public int getAttachedAtPercent() {
        return attachedAtPercent;
    }

    public Segment getRealSegment() {
        return realSegment;
    }

    public int getSection() {
        return section;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }
}
