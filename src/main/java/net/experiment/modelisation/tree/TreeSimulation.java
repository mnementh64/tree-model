package net.experiment.modelisation.tree;

import net.experiment.modelisation.tree.model.Tree;

public class TreeSimulation {
    private final Tree tree;
    private final TreeRenderer treeRenderer;
    private final TreeEvolver treeEvolver;

    public TreeSimulation(Tree tree, TreeRenderer treeRenderer, TreeEvolver treeEvolver) {
        this.tree = tree;
        this.treeRenderer = treeRenderer;
        this.treeEvolver = treeEvolver;
    }

    public void launch(int nbMonths) {
        for (int i=0;i<nbMonths;i++) {
            treeEvolver.evolve();
            treeRenderer.render();
        }
    }
}
