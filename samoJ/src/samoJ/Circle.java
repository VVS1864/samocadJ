package samoJ;

import open_dxf_lib.Color_rgb;
import samoJ.PrimitiveLine.DrawableLine;

public class Circle extends Shape {
	Coord theCenter;
	float Radius;
	protected int width; //!!!;
	/**
	 * 
	 * @param Radius
	 *            -
	 * @param x1
	 * @param y1
	 * @param z1
	 */

	public Circle(ObjectMode mode, float r, float x1, float y1, float z1, Color_rgb color, int width) {
		super(mode);
		theCenter = new Coord(x1, y1, z1);
		Radius = r;
		this.color = color;
		this.width = width;
		create_circle();
	}

	public Circle(ObjectMode mode, float rx, float ry, float rz, float x2, float y2, float z2, Color_rgb color, int width) {
		this(mode, (float)Math.round(Math.sqrt((rx - x2) * (rx - x2) +(ry - y2)*(ry - y2))), x2, y2, z2, color, width);
		/*int xd = rx - x2;
		int yd = ry - y2;
		int r = (int) Math.sqrt(xd * xd + yd * yd);
		create_circle();
		*/

	}

	public void create_circle() {
		// int[] lines = circle_lines(Radius, theCenter.x, theCenter.y); �������!!!

		float[] points = circle_points();

		for (int i = 0; i < points.length - 2; i += 2) {

			add(new DrawableLine(points[i], points[i + 1], 0,
					points[i + 2], points[i + 3], 0, color, width));
		}
		// last segment from last to first point
		add(new DrawableLine(points[points.length - 2],
				points[points.length - 1], 0, points[0], points[1], 0, color, width));

		// Crossers in center of circle
		float s = Radius / 20;
		add(new DrawableLine(theCenter.getX() - s, theCenter.getY(), 0, theCenter.getX() + s, theCenter.getY(), 0, color, width));
		add(new DrawableLine(theCenter.getX(), theCenter.getY() - s, 0, theCenter.getX(), theCenter.getY() + s, 0, color, width));
		
	}

	/**
	 * Calculate points of line segments of Circle
	 * 
	 * @param Radius
	 * @param x
	 * @param y
	 * @return
	 */
	public float[] circle_points() {
		int segments = core.Values.circle_segments;
		float[] lines = new float[segments * 2];
		// int w, h = 2.0f*Radius;
		float angle_increment = (float) Math.PI * 2.0f / segments;
		float theta = 0;

		for (int i = 0; i < segments; i++) {

			lines[i * 2] = (float) (Radius * Math.cos(theta) + theCenter.getX());
			lines[i * 2 + 1] = (float) (Radius * Math.sin(theta) + theCenter.getY());

			theta += angle_increment;
		}
		return lines;
	}
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Circle c = new Circle(5, 15, 0, 25, 30, 0);
		System.out.println(c.toList());
	}*/

}
