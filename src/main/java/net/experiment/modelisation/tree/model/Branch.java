package net.experiment.modelisation.tree.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.experiment.modelisation.tree.geometry.Point;
import net.experiment.modelisation.tree.geometry.Segment;
import net.experiment.modelisation.tree.geometry.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Branch {
    @JsonProperty
    private int attachedAtPercent;
    @JsonProperty
    private Vector vector;
    @JsonProperty
    private double section;
    @JsonProperty
    private List<Branch> branches = new ArrayList<>();
    @JsonProperty
    private Branch extension;
    @JsonProperty
    private int rank = 0;
    @JsonProperty
    private int age = 0;

    /**
     * The real coordinates of the start / end points of this branch
     */
    @JsonIgnore
    private Segment realSegment;

    public Branch() {
    }

    public Branch(Vector vector, double section) {
        this.vector = vector;
        this.section = section;
    }

    public Branch(int attachedAtPercent, Vector vector, double section) {
        this.attachedAtPercent = attachedAtPercent;
        this.vector = vector;
        this.section = section;
    }

    public void addBranch(Branch branch) {
        branch.setRank(this.getRank() + 1);
        this.branches.add(branch);
    }

    public void setOlder() {
        age++;
    }

    public Stream<Branch> streamOfBranches() {
        return branches.stream();
    }

    public void resolvePositionFrom(Point origin) {
        Point start = new Point(origin);
        Point end = new Point(origin.translate(vector));
        realSegment = new Segment(start, end);

        // resolve positions for all branches
        for (Branch branch : branches) {
            Point attachedPoint = realSegment.a.translate(vector.scale((double) branch.getAttachedAtPercent() / 100.0));
            branch.resolvePositionFrom(attachedPoint);
        }

        if (extension != null) {
            extension.resolvePositionFrom(end);
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
        if (hasExtension()) {
            extension.displayPositions(rank);
        }
    }

    public void scale(double factor) {
        vector = vector.scale(factor);
    }

    public void addModule(double amount) {
        vector = vector.addModule(amount);
    }

    public void growSection(double amount) {
        section += amount;
    }

    public int getAttachedAtPercent() {
        return attachedAtPercent;
    }

    public Segment getRealSegment() {
        return realSegment;
    }

    public double getSection() {
        return section;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public void addExtension(Branch extension) {
        // extensions must have the same rank as their reference branch
        extension.setRank(this.getRank() + 1);
        this.extension = extension;
    }

    public Branch getExtension() {
        return extension;
    }

    public boolean hasExtension() {
        return extension != null;
    }

    public int getAge() {
        return age;
    }

    @JsonIgnore
    public Vector getNormalizedVector() {
        return vector.normalized();
    }

    public boolean hasSubBranch() {
        return !branches.isEmpty();
    }

    public Vector getRotatedVector(double angleInDegres) {
        return vector.rotate(angleInDegres);
    }

    public double computeAngleInDegres() {
        return vector.computeAngleInDegres();
    }
}
