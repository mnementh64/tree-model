package net.experiment.modelisation.tree.geometry;

import net.experiment.modelisation.tree.model.Segment;
import org.junit.Assert;
import org.junit.Test;

public class GeometryUtilsTest {

    @Test
    public void rotateSegment_rotation0_noChange() {
        // GIVEN
        Segment segment = new Segment(5, 50);
        Point origin = new Point(10, 2);
        float[] vertices = GeometryUtils.createVertices(segment);
        displayVertices(vertices);

        // WHEN
        float[] transformedVertices = GeometryUtils.rotateVertices(vertices, origin, 0, segment.getLength());
        displayVertices(transformedVertices);

        // THEN
        for (int i = 0; i < vertices.length; i += 2) {
            Assert.assertEquals(vertices[i] + 10, transformedVertices[i], 0.01);
            Assert.assertEquals(vertices[i + 1] + 25 + 2, transformedVertices[i + 1], 0.01);
        }
    }

    @Test
//    public void rotateSegment_rotationSmall_almostNoChange() {
//        // GIVEN
//        Segment segment = new Segment(5, 50);
//        Point origin = new Point(10, 2);
//        float[] vertices = GeometryUtils.createVertices(segment);
//        displayVertices(vertices);
//
//        // WHEN
//        float[] transformedVertices = GeometryUtils.rotateVertices(vertices, origin, Math.PI / 20, segment.getLength());
//        displayVertices(transformedVertices);
//
//        // THEN
//        for (int i = 0; i < vertices.length; i += 2) {
//            Assert.assertEquals(vertices[i] + 10, transformedVertices[i], 0.01);
//            Assert.assertEquals(vertices[i + 1] + 25 + 2, transformedVertices[i + 1], 0.01);
//        }
//    }

    private void displayVertices(float[] vertices) {
        System.out.println("----------------------------------");
        for (int i = 0; i < vertices.length; i += 2) {
            System.out.println(vertices[i] + "," + vertices[i + 1]);
        }
    }


}