package core;
import com.jogamp.opengl.awt.GLCanvas;

import Jama.Matrix;

public class Zoom {
	
	public static void button_zoom(char c){
		if (c == '+'){
			int notches = 1;
			float x = GL_base.viewport[2]/2;
			float y = GL_base.viewport[3]/2;
			zoom(x, y, notches);
			System.out.println("+");
			
		}else
		if (c == '-'){
			int notches = -1;
			float x = GL_base.viewport[2]/2;
			float y = GL_base.viewport[3]/2;
			zoom(x, y, notches);
			System.out.println("-");
		}
	}
	
	
	static void zoom(float x, float y, int notches) {
		GLCanvas glcanvas = cad_demo.glcanvas;
		double s;

		if (notches > 0) {
			//System.out.println("Mouse wheel moved UP " + -notches + " notch(es)");
			s = Values.scale_size;

		} else {
			//System.out.println("Mouse wheel moved DOWN " + notches + " notch(es)");
			s = 1.0 / Values.scale_size;
		}

		double[] real_coords = GL_base.get_real_coords(glcanvas, x, y);

		double gl_x = real_coords[0];
		double gl_y = real_coords[1];

		double new_scale = Values.current_scale / s;
		System.out.println(new_scale);
		if ((Values.min_scale < new_scale) && (new_scale < Values.max_scale)){
							
		double[][] translate_matrix = { 
				{ 1.0, 0.0, 0.0, 0.0 },							
				{ 0.0, 1.0, 0.0, 0.0 }, 
				{ 0.0, 0.0, 1.0, 0.0 },							
				{ -gl_x, -gl_y, 0.0, 1.0 } };

		double[][] scale_matrix = {
				{ s, 0.0, 0.0, 0.0 },							
				{ 0.0, s, 0.0, 0.0 },
				{ 0.0, 0.0, 1.0, 0.0 },							
				{ 0.0, 0.0, 0.0, 1.0 } };

		double[][] translate2_matrix = { 
				{ 1.0, 0.0, 0.0, 0.0 },							
				{ 0.0, 1.0, 0.0, 0.0 }, 
				{ 0.0, 0.0, 1.0, 0.0 },							
				{ gl_x, gl_y, 0.0, 1.0 } };
		Matrix translate_m = new Matrix(translate_matrix);					
		Matrix translate2_m = new Matrix(translate2_matrix);					
		Matrix scale_m = new Matrix(scale_matrix);
		Matrix zoom_m = translate_m.times(scale_m).times(translate2_m);					
		double[] zoom_matrix = zoom_m.getRowPackedCopy();
		GL_base.general_matrix = zoom_matrix.clone();					
		glcanvas.display();	
		
		Values.current_scale = new_scale;
		
		}
	}

}