package core;

import java.util.ArrayList;

import it.unimi.dsi.fastutil.floats.FloatArrayList;



public class Dynamic_data {
	int GL_version;
	
	//GL3
	ArrayList<Float> vertices = new ArrayList<Float>();
	ArrayList<Float> colors = new ArrayList<Float>();
	ArrayList<Float> widths = new ArrayList<Float>();
	
	//GL2
	GL1_2_data[] GL1_2_dynamic_dataArray = new GL1_2_data[4];

	public Dynamic_data(int GL_version) {
		this.GL_version = GL_version;
		for (int i = 0; i < 4; i++) {
			GL1_2_dynamic_dataArray[i] = new GL1_2_data(i + 1);
		}
	}
	public void put_data(float[] vs, float[] color, int w) {
		for(int i = 0; i<vs.length; i+=3) {
			if(GL_version == 3) {
				vertices.add(vs[i]);
				vertices.add(vs[i + 1]);
				vertices.add(vs[i + 2]);
				colors.add(color[0]);
				colors.add(color[1]);
				colors.add(color[2]);
				widths.add((float) w);
			}
			else if(GL_version < 3) {
				GL1_2_dynamic_dataArray[w].listFloat.add(vs[i]);
				GL1_2_dynamic_dataArray[w].listFloat.add(vs[i + 1]);
				GL1_2_dynamic_dataArray[w].listFloat.add(vs[i + 2]);
				GL1_2_dynamic_dataArray[w].listFloatColor.add(color[0]);
				GL1_2_dynamic_dataArray[w].listFloatColor.add(color[1]);
				GL1_2_dynamic_dataArray[w].listFloatColor.add(color[2]);
			}
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
