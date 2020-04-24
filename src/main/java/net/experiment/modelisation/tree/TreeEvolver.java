package net.experiment.modelisation.tree;

import net.experiment.modelisation.tree.geometry.Vector;
import net.experiment.modelisation.tree.model.Branch;
import net.experiment.modelisation.tree.model.Tree;

import java.util.Random;

public class TreeEvolver {
    private final Tree tree;
    private final Random random = new Random(System.currentTimeMillis());

    public TreeEvolver(Tree tree) {
        this.tree = tree;
    }

    public void evolve() {
        evolve(tree.getTrunk());
    }

    public void evolve(Branch branch) {
        // this branch is now older
        branch.setOlder();

        // basic rule : each branch grows by 10 units
        int branchRank = branch.getRank();
//        double growthFactor = branchRank == 0 ? 1.3 : branchRank == 1 ? 1.2 : 1.3;
//        branch.scale(growthFactor);
        double growthAmount = 4 * Math.exp(-0.25 * branch.getAge());
        ;
        branch.addModule(growthAmount);

//        double sectionAmount = branchRank == 0 ? 1 : branchRank == 1 ? 1.5 : 1.2;
//        branch.growSection(sectionAmount);

//        double growthAmount = 0.0;
        if (branch.hasExtension()) {
//            // too old ? doesn't grow any more
//            if (branch.getAge() <= 20) {
//                // a branch with an extension grows slower
//                growthAmount = branchRank == 0 ? 2 : 1;
//            }
            evolve(branch.getExtension());
        } else {
            // a branch without an extension grows faster
//            growthAmount = branchRank == 0 ? 5 : 3;
            // random creation of an extension, based on the branch's age
            randomGenerationOfExtension(branch);
        }
//        branch.addModule(growthAmount);

        branch.streamOfBranches().forEach(this::evolve);
    }

    private void randomGenerationOfExtension(Branch branch) {
        // too young
        if (branch.getAge() <= 3) {
            return;
        }

        int chance = random.nextInt(10) + branch.getAge();

        // of to create an extension
        if (chance > 8) {
            double deltaAngleInDegre = (random.nextDouble() - 0.5) * 5;

            Vector vector = branch.getNormalizedVector().rotate(deltaAngleInDegre);
            Branch extension = new Branch(vector, branch.getSection() - 1);
            branch.addExtension(extension);
        }
    }

    public static void main(String[] args) {
        for (double a = 0; a <= 10; a++) {
            double b = 4 * Math.exp(-0.25 * a);
            System.out.println(a + " --> " + b);
        }
    }
}
