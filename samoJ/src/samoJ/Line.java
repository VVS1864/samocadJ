package samoJ;

public class Line extends Shape {
	// Not dashed line
	public Line(int x1, int y1, int z1, int x2, int y2, int z2) {
		add(new PrimitiveLine(x1, y1, z1, x2, y2, z2));
		//System.out.println("factor="+ factor);
		//System.out.println("mask="+mask);
	}
	// 
	public Line(int x1, int y1, int z1, int x2, int y2, int z2, double factor,
			int[] mask) {
		this.factor = factor;
		this.mask = mask;
		formPrimitiveLines(x1, y1, z1, x2, y2, z2);
	}

	void formPrimitiveLines(int x1, int y1, int z1, int x2, int y2, int z2) {
		if (mask == null || mask.length == 0) {
			add(new PrimitiveLine(x1, y1, z1, x2, y2, z2));
			return;
		}
		int factor_mask[] = new int[mask.length];
		for (int i = 0; i < mask.length; i++) {
			factor_mask[i] = (int) ((double) mask[i] * factor);

		}
		int dx = x2 - x1;
		int dy = y2 - y1;
		double sq = Math.sqrt(dx * dx + dy * dy); // ��������� �� ����
		double den_x = (dx / sq);// * factor;
		double den_y = (dy / sq);// * factor;
		int i = 0;
		int sum_stipple = 0;
		int x_begin = x1;
		int y_begin = y1;
		int x_min = Math.min(x1, x2);
		int x_max = Math.max(x1, x2);
		int y_min = Math.min(y1, y2);
		int y_max = Math.max(y1, y2);
		boolean continue_flag = true;

		do {

			int d = factor_mask[i];
			sum_stipple += d;
			int x_end = (int) Math.round(x1 + den_x * sum_stipple);
			int y_end = (int) Math.round(y1 + den_y * sum_stipple);
			// System.out.println(x_begin + " " + y_begin + " "+ x_end + " "+
			// y_end);
			if ((x_end < x_min) || (x_end > x_max) || (y_end < y_min)
					|| (y_end > y_max)) {

				x_end = x2;
				y_end = y2;
				continue_flag = false;
			}
			if ((i % 2) == 0) {
				// System.out.println("draw");
				add(new PrimitiveLine(x_begin, y_begin, z1, x_end,
						y_end, z2));
			}
			i++;
			if (i >= mask.length) {
				i = 0;
			}
			// i = i % (mask.length - 1);
			x_begin = x_end;
			y_begin = y_end;

		} while (continue_flag);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Line l1 = new Line(1, 2, 3, 5, 7, 9);
		System.out.println(l1.toList());
		Line l2 = new Line(100, 200, 0, 500, 700, 0, 50, new int[]{1,1});
		System.out.println(l2.toList());
	}
}
