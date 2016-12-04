package samoJ;

import core.Values;

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
	
	public Circle(int Radius, int x1, int y1, int z1) {
		create_circle(Radius, x1, y1);
	}

	public Circle(int rx, int ry, int rz, int x2, int y2, int z2) {
		int xd = rx - x2;
		int yd = ry - y2;
		int r = (int) Math.sqrt(xd * xd + yd * yd);
		create_circle(r, x2, y2);
	}

	public void create_circle(int Radius, int x1, int y1) {
		// int[] lines = circle_lines(Radius, x1, y1); красота!!!
		
		int[] lines = circle_points(Radius, x1, y1);

		for (int i = 0; i < lines.length - 2; i += 2) {
			//super.add(new DashedLine(lines[i], lines[i + 1], 0,
			//		lines[i + 2], lines[i + 3], 0));
			
			super.add(new DashedLine(lines[i], lines[i + 1], 0,
					lines[i + 2], lines[i + 3], 0, Values.stipple_factor, Values.stipple));
		}
		//super.add(new DashedLine(lines[lines.length - 2],
		//		lines[lines.length - 1], 0, lines[0], lines[1], 0));
		
		super.add(new DashedLine(lines[lines.length - 2],
				lines[lines.length - 1], 0, lines[0], lines[1], 0, Values.stipple_factor, Values.stipple));

		/*
		 * thePointData = circle_lines(Radius, x1, y1); //core.GL_base.list1 =
		 * ArrayUtils.addAll(core.GL_base.list1, thePointData);
		 * 
		 * int[] array1and2 = new int[core.GL_base.list1.length +
		 * thePointData.length];
		 * 
		 * System.arraycopy(core.GL_base.list1, 0, array1and2, 0,
		 * core.GL_base.list1.length); System.arraycopy(thePointData, 0,
		 * array1and2, core.GL_base.list1.length, thePointData.length);
		 * core.GL_base.list1 = array1and2;
		 */
		
		// crosshairs in center of circle
		int s = Radius / 20;
		super.add(new DashedLine(x1-s, y1, 0, x1+s, y1, 0));
		super.add(new DashedLine(x1, y1-s, 0, x1, y1+s, 0));
		
		

	}

	/**
	 * Calculate points of line segments of Circle
	 * 
	 * @param Radius
	 * @param x
	 * @param y
	 * @return
	 */
	public static int[] circle_points(int Radius, int xr, int yr) {
		int segments = core.Values.circle_segments;
		int[] lines = new int[segments * 2];
		// int w, h = 2.0f*Radius;
		double angle_increment = (double) Math.PI * 2.0f / segments;
		double theta = 0;

		for (int i = 0; i < segments; i++) {

			lines[i * 2] = (int) (Radius * Math.cos(theta) + xr);
			lines[i * 2 + 1] = (int) (Radius * Math.sin(theta) + yr);

			theta += angle_increment;
		}
		return lines;
	}

	/*
	 * public static LinkedList<Double> circle_lines_(double Radius, double x,
	 * double y) { int segments = core.Values.circle_segments; int d = segments
	 * * 6; LinkedList<Double> lines = new LinkedList<Double>(); // double w, h
	 * = 2.0f*Radius; double angle_increment = (double) Math.PI * 2.0f /
	 * segments; double theta = 0; double x1 = x+Radius; double y1 = y;
	 * lines.add(x1); lines.add(y1); lines.add(0.0); for (int i = 3; i < d-6;
	 * i+=6) { theta += angle_increment;
	 * 
	 * double x2 = Radius * (double) Math.cos(theta) + x; double y2 = Radius *
	 * (double) Math.sin(theta) + y; double z2 = 0.0f; lines.add(x2);
	 * lines.add(y2); lines.add(z2); lines.add(x2); lines.add(y2);
	 * lines.add(z2); } lines.add(x1); lines.add(y1); lines.add(0.0); return
	 * lines; }
	 * 
	 * public static double[] circle_lines(double Radius, double x, double y) {
	 * int segments = core.Values.circle_segments; int d = segments * 6;
	 * double[] lines = new double[d]; // double w, h = 2.0f*Radius; double
	 * angle_increment = (double) Math.PI * 2.0f / segments; double theta = 0;
	 * double x1 = x+Radius; double y1 = y; lines[0] = x1; lines[1] = y1;
	 * lines[2] = 0.0f; for (int i = 3; i < d-6; i+=6) { theta +=
	 * angle_increment;
	 * 
	 * double x2 = Radius * (double) Math.cos(theta) + x; double y2 = Radius *
	 * (double) Math.sin(theta) + y; double z2 = 0.0f; lines[i] = x2; lines[i+1]
	 * = y2; lines[i+2] = z2; lines[i+3] = x2; lines[i+4] = y2; lines[i+5] = z2;
	 * } lines[d-3] = x1; lines[d-2] = y1; lines[d-1] = 0.0f; return lines; }
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Circle c = new Circle(5.0f, 15.0f, 0.0f, 25.0f, 30.0f, 0.0f);
		// System.out.println(c.toList());
	}

}
