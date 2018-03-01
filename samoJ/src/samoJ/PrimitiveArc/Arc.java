package samoJ.PrimitiveArc;

import samoJ.Coord;
import samoJ.PrimitiveCircle.Circle;

/**
 * Entity for snap to arc, one of basic snap entities (line, circle, arc)
 * @author vlad
 *
 */
public class Arc extends Circle {	
	private float start;
	private float extent;
	/**
	 * 
	 * @param Center
	 * @param Radius
	 * @param start angle counterclockwise (0 = 360 = 3 o'clock)
	 * @param extent angle counterclockwise (0 = 360 = 3 o'clock)
	 */
	public Arc(Coord Center, float Radius, float start, float extent) {
		super(Center, Radius);
		this.start = start;
		this.extent = extent;
	}

	public Arc(float x1, float y1, float z1, float Radius, float start, float extent) {
		this(new Coord(x1, y1, z1), Radius, start, extent);
	}

	public float getStart() {
		return start;
	}

	public float getExtent() {
		return extent;
	}
}
