package net.experiment.modelisation.tree;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import net.experiment.modelisation.tree.model.ModelLoader;
import net.experiment.modelisation.tree.model.Tree;

import java.io.FileNotFoundException;
import java.time.Duration;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, JsonProcessingException {
        primaryStage.setTitle("Tree growth simulation");
        Group root = new Group();
        Canvas canvas = new Canvas(600, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        ModelLoader loader = new ModelLoader();
        Tree tree = loader.loadFromFile("/Users/sylvaincaillet/tree1.json");

        SimulationTask task = new SimulationTask(tree, gc);
        final Thread taskThread = new Thread(task, "simulation");
        taskThread.setDaemon(true);

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        taskThread.start();
    }

    private static final class SimulationTask extends Task<Void> {

        private final Tree tree;
        private final GraphicsContext gc;

        public SimulationTask(Tree tree, GraphicsContext gc) {
            this.tree = tree;
            this.gc = gc;
        }

        @Override
        protected Void call() throws Exception {
            TreeRenderer treeRenderer = new TreeRenderer(gc, tree, 600, 600);
            TreeEvolver treeEvolver = new TreeEvolver(tree);
            TreeSimulation simulation = new TreeSimulation(tree, treeRenderer, treeEvolver);
            simulation.launch(100, Duration.ofMillis(100));
            return null;
        }
    }
}