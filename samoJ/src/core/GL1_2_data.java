package core;

import java.nio.FloatBuffer;

import com.jogamp.common.nio.Buffers;

import it.unimi.dsi.fastutil.floats.FloatArrayList;

public class GL1_2_data {
	public boolean init = false;
	public int width;
	public int[] vbo_buffer;
	public FloatArrayList listFloat;
	public FloatArrayList listFloatColor;
	private float[] colors;
	private float[] vertices;
	FloatBuffer fb;
	FloatBuffer fbColors;
	public GL1_2_data(int width) {
		this.width = width;
		vbo_buffer = new int[2];
		listFloat = new FloatArrayList();
		listFloatColor = new FloatArrayList();
		init = true;
	}
	/**
	 * This method create FloatBuffer of float[] arrays for OpenGL, if data is
	 * ready.
	 */
	public void compile() {
		vertices = listFloat.elements();
		colors = listFloatColor.elements();
		fb = Buffers.newDirectFloatBuffer(vertices);
		fbColors = Buffers.newDirectFloatBuffer(colors);

	}

	public int size() {
		return vertices.length;
	}

}
