package samoJ;
import core.Core;
import open_dxf_lib.Color_rgb;
import open_dxf_lib.DXF_file;
import open_dxf_lib.dash_type;
import samoJ.PrimitiveLine.DrawableLine;
import samoJ.PrimitiveLine.Line;

import java.lang.Math;

public class ShapeLine extends Shape {
	private Core core = Core.c;
	// Vars of Line
	protected float x1;
	protected float y1;
	protected float z1;
	protected float x2;
	protected float y2;
	protected float z2;
	//protected int width; //!!!;

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
	public ShapeLine(ObjectMode mode, float  x1, float y1, float z1, float x2, float y2, float z2, float factor,
			dash_type dash, Color_rgb color, int width) {
		super(mode);
		//protection from Zero Line
		//if (x1 == x2 && y1 == y2 && z1 == z2) {
		//	return;
		//}
		this.x1 = x1;
		this.y1 = y1;
		this.z1 = z1;
		this.x2 = x2;
		this.y2 = y2;
		this.z2 = z2;
		this.factor = factor;
		this.dash = new Dash(dash);
		this.color = color;
		this.width = width;
		super.createShape(); //Standard procedure of create
	}
	
	@Override
	public void addLines() {
		createLine();
		add_snap_line(new Line(x1, y1, z1, x2, y2, z2));
	}

	void createLine() {
		if (dash.mask == null || dash.mask.length == 0) {
			
			add(new DrawableLine(mode, x1, y1, z1, x2, y2, z2, color, width));
			/*
			float h = 4 * Values.current_scale;
			float x = x2 - x1;
			float y = y2 - y1;
			double a = Math.atan2(x, y);
			
			float dx = h*(float)Math.cos(a);
			float dy = h*(float)Math.sin(a);
			float p11x = x1-dx;
			float p11y = y1+dy;
			float p12x = x1+dx;
			float p12y = y1-dy;
			
			float p21x = x2-dx;
			float p21y = y2+dy;
			float p22x = x2+dx;
			float p22y = y2-dy;
			
			add(new PrimitiveLine(p11x, p11y, z1, p21x, p21y, z2, color, width));
			add(new PrimitiveLine(p12x, p12y, z1, p22x, p22y, z2, color, width));
			*/
			return;
			
		}
		float factor_mask[] = new float[dash.mask.length];
		for (int i = 0; i < dash.mask.length; i++) {
			factor_mask[i] = dash.mask[i] * factor*6.6f;

		}
		float dx = x2 - x1;
		float dy = y2 - y1;
		float sq = (float)Math.sqrt(dx * dx + dy * dy); // ��������� �� ����
		float den_x = (dx / sq);// * factor;
		float den_y = (dy / sq);// * factor;
		int i = 0;
		float sum_stipple = 0;
		float x_begin = x1;
		float y_begin = y1;
		float x_min = Math.min(x1, x2);
		float x_max = Math.max(x1, x2);
		float y_min = Math.min(y1, y2);
		float y_max = Math.max(y1, y2);
		boolean continue_flag = true;

		do {

			float d = factor_mask[i];
			sum_stipple += d;
			float x_end =  x1 + den_x * sum_stipple;
			float y_end =  y1 + den_y * sum_stipple;
			// System.out.println(x_begin + " " + y_begin + " "+ x_end + " "+
			// y_end);
			if ((x_end < x_min) || (x_end > x_max) || (y_end < y_min)
					|| (y_end > y_max)) {

				x_end = x2;
				y_end = y2;
				continue_flag = false;
			}
			if ((i % 2) == 0) {
				//System.out.println("draw");
				add(new DrawableLine(mode, x_begin, y_begin, z1, x_end,
						y_end, z2, color, width));
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

	@Override
	public boolean zeroCoordsProtection() {
		return !(x1 == x2 && y1 == y2 && z1 == z2);
		
	}
	
}
