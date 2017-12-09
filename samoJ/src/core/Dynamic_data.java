package core;

import java.util.ArrayList;



public class Dynamic_data {
	ArrayList<Float> vertices = new ArrayList<>();
	ArrayList<Float> colors = new ArrayList<>();
	ArrayList<Float> widths = new ArrayList<>();
	public Dynamic_data() {
		if (Global_var.select_mode){
			put_data(Global_var.select_rect_vertices, Global_var.select_rect_color.get_float_rgb(), Values.select_rect_width);
		}
		
		if (Global_var.current_Shape_vertices != null){
			put_data(Global_var.current_Shape_vertices, Values.current_shape_color.get_float_rgb(), Values.dynamic_width);	
		}
		if (Global_var.snap_sign_vertices != null){
			put_data(Global_var.snap_sign_vertices, Values.snap_color.get_float_rgb(), Values.dynamic_width);
		}
		if (Global_var.preview_object_vertices != null){
			put_data(Global_var.preview_object_vertices, Values.color.get_float_rgb(), Values.dynamic_width);
		}	
	}
	private void put_data(float[] vs, float[] color, float w) {
		for(int i = 0; i<vs.length; i+=3) {
			vertices.add(vs[i]);
			vertices.add(vs[i+1]);
			vertices.add(vs[i+2]);
			colors.add(color[0]);
			colors.add(color[1]);
			colors.add(color[2]);
			widths.add((float)w);
		}
	}
	public float[] get_vertices() {
		return get_floats(vertices);
	}
	public float[] get_colors() {
		return get_floats(colors);
	}
	public float[] get_widths() {
		return get_floats(widths);
	}
	
	public float[] get_floats(ArrayList<Float> vertices) {
		float[] floatVertices = new float[vertices.size()];
		int i = 0;
		for (Float f : vertices) {
		    floatVertices[i++] = f;// (f != null ? f : Float.NaN); // Or whatever default you want.
		}
		return floatVertices;
		
	}

}
