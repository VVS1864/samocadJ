package core.navigation;

import core.Core;
import core.GL_base;
import core.Values;
import core.Global_var;

public class Zoom {
	private Core core = Core.c;
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
	public float[] get_real_coords_of_screen(){
		float x = core.glRender.viewport[2]/2;
		float y = core.glRender.viewport[3]/2;
		float[] real_coords = core.glRender.get_real_coords(x, y);

		return real_coords;
	}

	public void button_zoom(char c) {
		if (c == '+') {
			button_zoom_doing(1);
		} else if (c == '-') {
			button_zoom_doing(-1);
		}
		core.gui.glcanvas.display();
		System.out.println(c);
	}

	// For "public static void button_zoom(char c)"
	private void button_zoom_doing(int nt) {
		float [] real_centr_coords = get_real_coords_of_screen();
		float x = real_centr_coords[0];
		float y = real_centr_coords[1];
		zoom(x, y, nt);
	}

	public void zoom(float x, float y, int notches) {
		// s = scale ZoomIn or ZoomOut
		float s = (notches > 0) ? core.values.scale_size : 1 / core.values.scale_size;

		float new_scale = core.values.current_scale / s;		

		float gl_x = x;
		float gl_y = y;

		if (core.values.min_scale > new_scale) {
			System.out.println("Minimal scale!");
		}

		//else if(new_scale > Values.max_scale){
		//	System.out.println("Maximal scale!");
		//}
		
		else {
			// Optimized version AEROport 25/12/2016 No Matrix
			float[] zoom_matrix = { s, 0, 0, 0, 0, s, 0, 0, 0,
					0, 1, 0, gl_x - gl_x * s, gl_y - gl_y * s, 0, 1 };

			//GL_base.general_matrix.glMultMatrixf(zoom_matrix, 0);
			core.glRender.general_matrix = zoom_matrix.clone();
			core.values.current_scale = new_scale;
		}
	}
}
