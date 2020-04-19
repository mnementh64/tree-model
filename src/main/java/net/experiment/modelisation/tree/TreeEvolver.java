package net.experiment.modelisation.tree;

import net.experiment.modelisation.tree.model.Branch;
import net.experiment.modelisation.tree.model.Tree;

public class TreeEvolver {
    private final Tree tree;

    public TreeEvolver(Tree tree) {
        this.tree = tree;
    }

    public void evolve() {
        evolve(tree.getTrunk());
    }

    public void evolve(Branch branch) {
        // basic rule : each branch grows by 10 units
        int branchRank = branch.getRank();
        double growthFactor = branchRank == 0 ? 1.3 : branchRank == 1 ? 1.2 : 1.3;
        branch.scale(growthFactor);

        double sectionAmount = branchRank == 0 ? 1 : branchRank == 1 ? 1.5 : 1.2;
        branch.growSection(sectionAmount);

        branch.streamOfBranches().forEach(this::evolve);
    }
}
