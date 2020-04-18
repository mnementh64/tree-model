package net.experiment.modelisation.tree.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.experiment.modelisation.tree.geometry.Vector;

import java.io.FileWriter;
import java.io.IOException;

public class ModelExporter {

    private final ObjectMapper mapper = new ObjectMapper();

    public void exportToStdOutput(Tree tree) throws JsonProcessingException {
        String value = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tree);
        System.out.println("value = " + value);
    }

    public void exportToFile(Tree tree, String fileName) throws IOException {
        String value = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tree);
        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write(value);
        }
    }

    public static void main(String[] args) throws IOException {
        ModelExporter exporter = new ModelExporter();

        Vector vecTrunk = new Vector(0, 50);
        Branch trunk = new Branch(0, vecTrunk, 8);

        Vector vecBranch1 = new Vector(30, 30);
        Branch branch1 = new Branch(70, vecBranch1, 4);
        trunk.addBranch(branch1);

        Vector vecSubBranch1 = new Vector(5, 10);
        Branch subBranch1 = new Branch(60, vecSubBranch1, 2);
        branch1.addBranch(subBranch1);

        Vector vecBranch2 = new Vector(-30, 30);
        Branch branch2 = new Branch(80, vecBranch2, 3);
        trunk.addBranch(branch2);

        Tree tree = new Tree(trunk);

        exporter.exportToStdOutput(tree);
        exporter.exportToFile(tree, "/Users/sylvaincaillet/tree1.json");
    }
}
