package samoJ;

public class Circle extends Shape {
	Coord theCenter;
	int Radius;

	/**
	 * 
	 * @param Radius
	 *            -
	 * @param x1
	 * @param y1
	 * @param z1
	 */

	public Circle(int r, int x1, int y1, int z1) {
		theCenter = new Coord(x1, y1, z1);
		Radius = r;
		create_circle();
	}

	public Circle(int rx, int ry, int rz, int x2, int y2, int z2) {
		this((int)Math.round(Math.sqrt((rx - x2) * (rx - x2) +(ry - y2)*(ry - y2))), x2, y2, z2);
		/*int xd = rx - x2;
		int yd = ry - y2;
		int r = (int) Math.sqrt(xd * xd + yd * yd);
		create_circle();
		*/

	}

	public void create_circle() {
		// int[] lines = circle_lines(Radius, theCenter.x, theCenter.y); красота!!!

		int[] points = circle_points();

		for (int i = 0; i < points.length - 2; i += 2) {

			add(new PrimitiveLine(points[i], points[i + 1], 0,
					points[i + 2], points[i + 3], 0));
		}
		// last segment from last to first point
		add(new PrimitiveLine(points[points.length - 2],
				points[points.length - 1], 0, points[0], points[1], 0));

		// Crossers in center of circle
		int s = Radius / 20;
		add(new PrimitiveLine(theCenter.x - s, theCenter.y, 0, theCenter.x + s, theCenter.y, 0));
		add(new PrimitiveLine(theCenter.x, theCenter.y - s, 0, theCenter.x, theCenter.y + s, 0));

	}

	/**
	 * Calculate points of line segments of Circle
	 * 
	 * @param Radius
	 * @param x
	 * @param y
	 * @return
	 */
	public int[] circle_points() {
		int segments = core.Values.circle_segments;
		int[] lines = new int[segments * 2];
		// int w, h = 2.0f*Radius;
		double angle_increment = (double) Math.PI * 2.0f / segments;
		double theta = 0;

		for (int i = 0; i < segments; i++) {

			lines[i * 2] = (int) (Radius * Math.cos(theta) + theCenter.x);
			lines[i * 2 + 1] = (int) (Radius * Math.sin(theta) + theCenter.y);

			theta += angle_increment;
		}
		return lines;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Circle c = new Circle(5, 15, 0, 25, 30, 0);
		System.out.println(c.toList());
	}

}
