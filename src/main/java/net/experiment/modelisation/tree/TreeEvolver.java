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
        double growthAmount = 4 * Math.exp(-0.25 * branch.getAge());
        ;
        branch.addModule(growthAmount);

        double sectionAmount = Math.exp(-0.5 * branch.getAge());
        branch.growSection(sectionAmount);

        // extension management
        if (branch.hasExtension()) {
            evolve(branch.getExtension());
        } else {
            // random creation of an extension, based on the branch's age
            randomGenerationOfExtension(branch);
        }

        branch.streamOfBranches().forEach(this::evolve);

        // sub-branch management
        randomGenerationOfSubBranch(branch);
    }

    private void randomGenerationOfSubBranch(Branch branch) {
        if (branch.hasSubBranch()) {
            return;
        }

        int chance = random.nextInt(10) + branch.getAge();

        // ok to create a sub branch
        if (chance > 8) {
            int attachedPosition = random.nextInt(100);

            // branch above or below the reference one ?
            int angleModifier = random.nextInt(10) > 5 ? 1 : -1;

            double branchAngle = branch.computeAngleInDegres();

            // angle of the new branch : the absolute value will be +/- (15° + the random angle)
            double angleInDegres = angleModifier * (15 + random.nextInt(15));
            if ((branchAngle + angleInDegres) < 0) {
                angleInDegres = 0;
            } else if ((branchAngle + angleInDegres) > 180) {
                angleInDegres = 180;
            }

            Vector vector = branch.getRotatedVector(angleInDegres);

            Branch subBranch = new Branch(attachedPosition, vector, 1);
            branch.addBranch(subBranch);
        }
    }

    private void randomGenerationOfExtension(Branch branch) {
        // too young
        if (branch.getAge() <= 3) {
            return;
        }

        int chance = random.nextInt(10) + branch.getAge();

        // ok to create an extension
        if (chance > 8) {
            double deltaAngleInDegre = (random.nextDouble() - 0.5) * 10;

            Vector vector = branch.getNormalizedVector().rotate(deltaAngleInDegre);
            double newSection = branch.getSection() - 1.4;
            Branch extension = new Branch(vector, newSection < 0 ? 0.1 : newSection);
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
