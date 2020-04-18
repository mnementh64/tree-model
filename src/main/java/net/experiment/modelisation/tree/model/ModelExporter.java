package net.experiment.modelisation.tree.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

        Segment sub1 = new Segment(2, 7);
        Branch branch1 = new Branch(32, 45, sub1);
        Segment initialSegment = new Segment(5, 50);
        initialSegment.addBranch(branch1);
        Tree tree = new Tree(initialSegment);

        exporter.exportToStdOutput(tree);
        exporter.exportToFile(tree, "/Users/sylvaincaillet/tree1.json");
    }
}
