package net.experiment.modelisation.tree;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import net.experiment.modelisation.tree.geometry.Point;
import net.experiment.modelisation.tree.model.Branch;
import net.experiment.modelisation.tree.model.Tree;

public class TreeRenderer {

    private final GraphicsContext gc;
    private final Tree tree;
    private final int width;
    private final int height;

    public TreeRenderer(GraphicsContext gc, Tree tree, int width, int height) {
        this.gc = gc;
        this.tree = tree;
        this.width = width;
        this.height = height;
    }

    public void render() {
        gc.clearRect(0, 0, width, height);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);
        gc.strokeLine(width / 2, 0, width / 2, height);
        gc.strokeLine(0, height / 2, width, height / 2);

        gc.setStroke(Color.BROWN);

        // resolve the real positions of all branches of the tree - the trunk starts from the origin.
        Point origin = new Point(width / 2, height);
        tree.resolvePositions(origin);
        tree.displayPositions();

        drawBranch(gc, tree.getTrunk());

//        gc.setFill(Color.GREEN);
//        gc.setStroke(Color.BLUE);
//        gc.setLineWidth(5);
//        gc.strokeLine(40, 10, 10, 40);
//        gc.fillOval(10, 60, 30, 30);
//        gc.strokeOval(60, 60, 30, 30);
//        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
//        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
//        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
//        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
//        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
//        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
//        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
//        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
//        gc.fillPolygon(new double[]{10, 40, 10, 40},
//                new double[]{210, 210, 240, 240}, 4);
//        gc.strokePolygon(new double[]{60, 90, 60, 90},
//                new double[]{210, 210, 240, 240}, 4);
//        gc.strokePolyline(new double[]{110, 140, 110, 140},
//                new double[]{210, 210, 240, 240}, 4);
    }

    private void drawBranch(GraphicsContext gc, Branch branch) {
        gc.setLineWidth(branch.getSection());
        Point a = branch.getRealSegment().a;
        Point b = branch.getRealSegment().b;
        gc.strokeLine(a.x, height - (a.y - height), b.x, height - (b.y - height));

        branch.streamOfBranches().forEach(br -> drawBranch(gc, br));
    }

    /**
     * D-P-C
     * |   |
     * |   |
     * .....
     * |   |
     * |   |
     * A-O-B
     */
//    private void drawFrom(GraphicsContext gc, Segment segment, Point origin, double orientation) {
//        float[] transformedVertices = GeometryUtils.rotateSegment(segment, origin, orientation);
//
//        double[] transformedX = new double[transformedVertices.length / 2];
//        double[] transformedY = new double[transformedVertices.length / 2];
//        for (int i = 0; i < transformedVertices.length; i += 2) {
//            transformedX[i / 2] = transformedVertices[i];
//            transformedY[i / 2] = transformedVertices[i + 1] - segment.getLength();
//        }
//        gc.fillPolygon(transformedX, transformedY, 4);
//
////        segment.streamOfBranches().forEach(branch -> {
////            Point positionOnSegment = GeometryUtils.computePositionObranch.getPositionOnSegment()
////        });
//    }
}
