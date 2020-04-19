package net.experiment.modelisation.tree;

import javafx.application.Platform;
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
        for (int i = 0; i < nbMonths; i++) {
            // must be called first to compute real positions
            Platform.runLater(
                    () -> {
                        treeRenderer.render();
                    }
            );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Must be called last because also
            treeEvolver.evolve();
        }
    }
}
