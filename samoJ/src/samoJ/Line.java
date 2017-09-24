package samoJ;
import core.Global_var;
import open_dxf_lib.Color_rgb;
import open_dxf_lib.DXF_file;
import open_dxf_lib.dash_type;

public class Line extends Shape {
	// Vars of Line
	protected double x1;
	protected double y1;
	protected double z1;
	protected double x2;
	protected double y2;
	protected double z2;
	
	/*
	// Not dashed line
	public Line(ObjectMode mode, double x1, double y1, double z1, double x2, double y2, double z2) {
		super(mode);
		PrimitiveLine line = new PrimitiveLine(x1, y1, z1, x2, y2, z2);
		add(line);
		add_snap_line(line);
		if(mode == ObjectMode.Preview_object){
			Global_var.preview_object_vertices = super.getPreviewData();
		}
		//System.out.println("factor="+ factor);
		//System.out.println("mask="+mask);
	}*/
	// Dashed or solid line
	public Line(ObjectMode mode, double  x1, double y1, double z1, double x2, double y2, double z2, double factor,
			dash_type dash, Color_rgb color) {
		super(mode);
		this.x1 = x1;
		this.y1 = y1;
		this.z1 = z1;
		this.x2 = x2;
		this.y2 = y2;
		this.z2 = z2;
		this.factor = factor;
		this.dash = new Dash(dash);
		this.color = color;
		
		formPrimitiveLines(x1, y1, z1, x2, y2, z2);
		add_snap_line(new PrimitiveLine(x1, y1, z1, x2, y2, z2));
		if(mode == ObjectMode.Preview_object){
			Global_var.preview_object_vertices = super.getPreviewData();
		}
	}

	void formPrimitiveLines(double x1, double y1, double z1, double x2, double y2, double z2) {
		if (dash.mask == null || dash.mask.length == 0) {
			add(new PrimitiveLine(x1, y1, z1, x2, y2, z2));
			return;
		}
		double factor_mask[] = new double[dash.mask.length];
		for (int i = 0; i < dash.mask.length; i++) {
			factor_mask[i] = dash.mask[i] * factor;

		}
		double dx = x2 - x1;
		double dy = y2 - y1;
		double sq = Math.sqrt(dx * dx + dy * dy); // ��������� �� ����
		double den_x = (dx / sq);// * factor;
		double den_y = (dy / sq);// * factor;
		int i = 0;
		int sum_stipple = 0;
		double x_begin = x1;
		double y_begin = y1;
		double x_min = Math.min(x1, x2);
		double x_max = Math.max(x1, x2);
		double y_min = Math.min(y1, y2);
		double y_max = Math.max(y1, y2);
		boolean continue_flag = true;

		do {

			double d = factor_mask[i];
			sum_stipple += d;
			double x_end = (double) Math.round(x1 + den_x * sum_stipple);
			double y_end = (double) Math.round(y1 + den_y * sum_stipple);
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
			if (i >= dash.mask.length) {
				i = 0;
			}
			// i = i % (mask.length - 1);
			x_begin = x_end;
			y_begin = y_end;

		} while (continue_flag);

	}
	
	@Override
	public void save_to_DXF(DXF_file f) {
		f.put_line(x1, y1, x2, y2, dash.dash, factor, color, width);
		
	}
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Line l1 = new Line(1, 2, 3, 5, 7, 9);
		System.out.println(l1.toList());
		Line l2 = new Line(100, 200, 0, 500, 700, 0, 50, new int[]{1,1});
		System.out.println(l2.toList());
	}*/
}
