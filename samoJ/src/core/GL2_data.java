package core;

import it.unimi.dsi.fastutil.floats.FloatArrayList;

public class GL2_data {
	public int width;
	public int[] vbo_buffer;
	public FloatArrayList listFloat;
	public FloatArrayList listFloatColor;
	public float[] colors;
	public float[] vertices;
	public GL2_data(int width) {
		this.width = width;
		vbo_buffer = new int[2];
		listFloat = new FloatArrayList();
		listFloatColor = new FloatArrayList();
	}

}
