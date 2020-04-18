package net.experiment.modelisation.tree.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ModelLoader {

    private final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        ModelLoader exporter = new ModelLoader();

        Tree tree = exporter.loadFromFile("/Users/sylvaincaillet/tree1.json");
        System.out.println("tree = " + tree);
    }

    public Tree loadFromFile(String fileName) throws FileNotFoundException, JsonProcessingException {
        String content = new Scanner(new File(fileName)).useDelimiter("\\Z").next();
        return mapper.readValue(content, Tree.class);
    }
}
