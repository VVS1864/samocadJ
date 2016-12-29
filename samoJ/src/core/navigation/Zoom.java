package core.navigation;

import core.GL_base;
import core.Values;
import core.Global_var;

public class Zoom {
	/*
	 * Optimized by AEROport 25/12/2016, resolved manually the file version conflict by VVS1864 26/12/2016
	 */
	
	/*
	 * optional function static void printMatrix(Matrix theM) { double[][]
	 * temp_array = theM.getArray(); System.out.println("--------------"); for
	 * (double[] str : temp_array) { for (double elem : str)
	 * System.out.print(" " + elem); System.out.println(); }
	 * System.out.println("--------------"); }
	 */
	public static double[] get_real_coords_of_screen(){
		double x = GL_base.viewport[2]/2;
		double y = GL_base.viewport[3]/2;
		double[] real_coords = GL_base.get_real_coords(Global_var.glcanvas, x, y);

		return real_coords;
	}

	public static void button_zoom(char c) {
		if (c == '+') {
			button_zoom_doing(1);
		} else if (c == '-') {
			button_zoom_doing(-1);
		}
		System.out.println(c);
	}

	// For "public static void button_zoom(char c)"
	private static void button_zoom_doing(int nt) {
		double [] real_centr_coords = get_real_coords_of_screen();
		double x = real_centr_coords[0];
		double y = real_centr_coords[1];
		zoom(x, y, nt);
	}

	public static void zoom(double x, double y, int notches) {
		// s = scale ZoomIn or ZoomOut
		double s = (notches > 0) ? Values.scale_size : 1.0 / Values.scale_size;

		double new_scale = Values.current_scale / s;		

		double gl_x = x;
		double gl_y = y;

		if (Values.min_scale > new_scale) {
			System.out.println("Minimal scale!");
		}

		else if(new_scale > Values.max_scale){
			System.out.println("Maximal scale!");
		}
		
		else {
			// Optimized version AEROport 25/12/2016 No Matrix
			double[] zoom_matrix = { s, 0.0, 0.0, 0.0, 0.0, s, 0.0, 0.0, 0.0,
					0.0, 1.0, 0.0, gl_x - gl_x * s, gl_y - gl_y * s, 0.0, 1.0 };

			GL_base.general_matrix = zoom_matrix;
			Global_var.glcanvas.display();
			System.out.println(new_scale);
			Values.current_scale = new_scale;
		}
	}
}
