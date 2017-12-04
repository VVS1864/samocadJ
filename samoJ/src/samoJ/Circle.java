package samoJ;

import open_dxf_lib.Color_rgb;

public class Circle extends Shape {
	Coord theCenter;
	double Radius;
	
	/**
	 * 
	 * @param Radius
	 *            -
	 * @param x1
	 * @param y1
	 * @param z1
	 */

	public Circle(ObjectMode mode, double r, double x1, double y1, double z1, Color_rgb color) {
		super(mode);
		theCenter = new Coord(x1, y1, z1);
		Radius = r;
		this.color = color;
		create_circle();
	}

	public Circle(ObjectMode mode, double rx, double ry, double rz, double x2, double y2, double z2, Color_rgb color) {
		this(mode, (double)Math.round(Math.sqrt((rx - x2) * (rx - x2) +(ry - y2)*(ry - y2))), x2, y2, z2, color);
		/*int xd = rx - x2;
		int yd = ry - y2;
		int r = (int) Math.sqrt(xd * xd + yd * yd);
		create_circle();
		*/

	}

	public void create_circle() {
		// int[] lines = circle_lines(Radius, theCenter.x, theCenter.y); �������!!!

		double[] points = circle_points();

		for (int i = 0; i < points.length - 2; i += 2) {

			add(new PrimitiveLine(points[i], points[i + 1], 0,
					points[i + 2], points[i + 3], 0, color));
		}
		// last segment from last to first point
		add(new PrimitiveLine(points[points.length - 2],
				points[points.length - 1], 0, points[0], points[1], 0, color));

		// Crossers in center of circle
		double s = Radius / 20;
		add(new PrimitiveLine(theCenter.getX() - s, theCenter.getY(), 0, theCenter.getX() + s, theCenter.getY(), 0, color));
		add(new PrimitiveLine(theCenter.getX(), theCenter.getY() - s, 0, theCenter.getX(), theCenter.getY() + s, 0, color));

	}

	/**
	 * Calculate points of line segments of Circle
	 * 
	 * @param Radius
	 * @param x
	 * @param y
	 * @return
	 */
	public double[] circle_points() {
		int segments = core.Values.circle_segments;
		double[] lines = new double[segments * 2];
		// int w, h = 2.0f*Radius;
		double angle_increment = (double) Math.PI * 2.0f / segments;
		double theta = 0;

		for (int i = 0; i < segments; i++) {

			lines[i * 2] = (double) (Radius * Math.cos(theta) + theCenter.getX());
			lines[i * 2 + 1] = (double) (Radius * Math.sin(theta) + theCenter.getY());

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
