package core;

import com.jogamp.opengl.awt.GLCanvas;

//import Jama.Matrix;

public class Zoom {
	/*
	 * optional function static void printMatrix(Matrix theM) { double[][]
	 * temp_array = theM.getArray(); System.out.println("--------------"); for
	 * (double[] str : temp_array) { for (double elem : str)
	 * System.out.print(" " + elem); System.out.println(); }
	 * System.out.println("--------------"); }
	 */

	public static void button_zoom(char c) {
		if (c == '+') {
			button_zoom_doing(1);
		} else if (c == '-') {
			button_zoom_doing(-1);
		}
		System.out.println(c);
	}

	// For "public static void button_zoom(char c)"
	public static void button_zoom_doing(int nt) {
		double x = GL_base.viewport[2] / 2;
		double y = GL_base.viewport[3] / 2;
		zoom(x, y, nt);
	}

	static void zoom(double x, double y, int notches) {
		// s = scale ZoomIn or ZoomOut
		double s = (notches > 0) ? Values.scale_size : 1.0 / Values.scale_size;

		double new_scale = Values.current_scale / s;

		if ((Values.min_scale < new_scale) && (new_scale < Values.max_scale)) {

			GLCanvas glcanvas = cad_demo.glcanvas;
			double[] real_coords = GL_base.get_real_coords(glcanvas, x, y);

			double gl_x = real_coords[0];
			double gl_y = real_coords[1];

			/*
			 * double[][] translate_matrix = { { 1.0, 0.0, 0.0, 0.0 }, { 0.0,
			 * 1.0, 0.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0 }, { -gl_x, -gl_y, 0.0, 1.0
			 * } };
			 * 
			 * double[][] scale_matrix = { { s, 0.0, 0.0, 0.0 }, { 0.0, s, 0.0,
			 * 0.0 }, { 0.0, 0.0, 1.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 } };
			 * 
			 * double[][] translate2_matrix = { { 1.0, 0.0, 0.0, 0.0 }, { 0.0,
			 * 1.0, 0.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0 }, { gl_x, gl_y, 0.0, 1.0 }
			 * };
			 * 
			 * 
			 * 
			 * Matrix translate_m = new Matrix(translate_matrix);
			 * 
			 * Matrix translate2_m = new Matrix(translate2_matrix);
			 * 
			 * Matrix scale_m = new Matrix(scale_matrix);
			 * 
			 * Matrix zoom_m = translate_m.times(scale_m).times(translate2_m);
			 * //printMatrix(zoom_m);
			 * 
			 * 
			 * 
			 * 
			 * Matrix zoom_m = new Matrix(rez_matrix);
			 * 
			 * double[] zoom_matrix = zoom_m.getRowPackedCopy();
			 */

			// Optimized version AEROport 25/12/2016 No Matrix
			double[] zoom_matrix = { s, 0.0, 0.0, 0.0, 0.0, s, 0.0, 0.0, 0.0,
					0.0, 1.0, 0.0, gl_x - gl_x * s, gl_y - gl_y * s, 0.0, 1.0 };

			// GL_base.general_matrix = zoom_matrix.clone();
			GL_base.general_matrix = zoom_matrix;
			glcanvas.display();

			Values.current_scale = new_scale;

		}
	}

}
