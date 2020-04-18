package net.experiment.modelisation.tree.geometry;

import net.experiment.modelisation.tree.model.Segment;
import org.mini2Dx.gdx.math.Polygon;

public class GeometryUtils {
    public static float[] rotateSegment(Segment segment, Point origin, double orientation) {
        float[] vertices = createVertices(segment);
        return rotateVertices(vertices, origin, orientation, segment.getLength());
    }

    public static float[] rotateVertices(float[] vertices, Point origin, double orientation, int length) {
        Polygon polygon = new Polygon();
        polygon.setVertices(vertices);
        polygon.translate(origin.x, (float) (origin.y + length / 2.0));
        polygon.rotate((float) Math.toDegrees(orientation));

        return polygon.getTransformedVertices();
    }

    public static float[] createVertices(Segment segment) {
        return new float[]{
                (float) (-segment.getSection() / 2.0), (float) (-segment.getLength() / 2.0),
                (float) (segment.getSection() / 2.0), (float) (-segment.getLength() / 2.0),
                (float) (segment.getSection() / 2.0), (float) (segment.getLength() / 2.0),
                (float) (-segment.getSection() / 2.0), (float) (segment.getLength() / 2.0)
        };
    }
}
