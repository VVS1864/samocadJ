package core;

import java.util.ArrayList;



public class Dynamic_data {
	ArrayList<Float> vertices = new ArrayList<>();
	ArrayList<Float> colors = new ArrayList<>();
	ArrayList<Float> widths = new ArrayList<>();
	public Dynamic_data() {
	}
	public void put_data(float[] vs, float[] color, float w) {
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
