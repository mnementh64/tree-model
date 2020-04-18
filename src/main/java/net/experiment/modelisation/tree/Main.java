package net.experiment.modelisation.tree;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import net.experiment.modelisation.tree.model.ModelLoader;
import net.experiment.modelisation.tree.model.Tree;

import java.io.FileNotFoundException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, JsonProcessingException {
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(1000, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        ModelLoader loader = new ModelLoader();
        Tree tree = loader.loadFromFile("/Users/sylvaincaillet/tree1.json");

        TreeRenderer treeRenderer = new TreeRenderer(tree, 1000, 600);
        treeRenderer.render(gc);

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}