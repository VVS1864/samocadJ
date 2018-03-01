package samoJ.PrimitiveCircle;

import samoJ.Coord;

/**
 * Entity for snap to circle, one of basic snap entities (line, circle, arc)
 * @author vlad
 *
 */
public class Circle {
	private Coord Center;
	private float Radius;
	
	public Circle(Coord Center, float Radius) {
		this.Center = Center;
		this.Radius = Radius;	
	}

	public Circle(float x1, float y1, float z1, float Radius) {
		this(new Coord(x1, y1, z1), Radius);
	}

	public Coord getCenter() {
		return Center;
	}

	public float getRadius() {
		return Radius;
	}
}
