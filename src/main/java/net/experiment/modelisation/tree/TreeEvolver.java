package net.experiment.modelisation.tree;

import net.experiment.modelisation.tree.model.Tree;

public class TreeEvolver {
    private final Tree tree;

    public TreeEvolver(Tree tree) {
        this.tree = tree;
    }

    public void evolve() {
        // basic rule : each branch grows by 10 units
        tree.getTrunk().evolve();
    }
}
