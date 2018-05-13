package core.dynamic_entities;

import open_dxf_lib.Color_rgb;

public class Selective_rect extends Dynamic_entity{
	public  float[] select_rect_1;
	public  float[] select_rect_2;
	public boolean enable = false;
		
	public float x1;
	public float y1;
	public float x2;
	public float y2;
	
	public Color_rgb color_1 = new Color_rgb(255, 0, 0);
	public Color_rgb color_2 = new Color_rgb(0, 0, 255);
	
		
	public void init(float[] point_1, float[] point_2) {
		set_color_width();
		select_rect_1 = point_1.clone();
		select_rect_2 = point_2.clone();
		calc_rect();
		enable = true;
	}
	
	public void new_data(float[] point_2) {
		select_rect_2 = point_2.clone();
		calc_rect();
	}
	
	private void calc_rect() {
		x1 = select_rect_1[0];
		y1 = select_rect_1[1];
		x2 = select_rect_2[0];
		y2 = select_rect_2[1];
		vertices = new float[]{
				x1, y1, 0,
				x2, y1, 0, 
				x1, y2, 0,
				x2, y2, 0,
				x2, y1, 0,
				x2, y2, 0,
				x1, y1, 0,
				x1, y2, 0};
		if (x1 > x2){
			color = color_1;
		}
		else
		{
			color = color_2;
		}		
	}

	public void clear() {
		super.clear();
		enable = false;
	}
	
	public float get_x_min() {
		return x1<x2 ? x1 : x2;
	}
	public float get_y_min() {
		return y1<y2 ? y1 : y2;
	}
	public float get_x_max() {
		return x1>x2 ? x1 : x2;
	}
	public float get_y_max() {
		return y1>y2 ? y1 : y2;
	}
	
	protected void set_color_width() {
		color_1 = core.values.selective_rect_color_1;
		color_2 = core.values.selective_rect_color_2;
		width = core.values.select_rect_width;
	}

}
