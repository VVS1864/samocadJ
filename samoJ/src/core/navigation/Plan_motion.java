package core.navigation;



import com.jogamp.opengl.awt.GLCanvas;

import core.GL_base;
import core.cad_demo;


public class Plan_motion {
	public static void plan_motion(GLCanvas glcanvas, double x, double y) {

		//double[] real_coords = GL_base.get_real_coords(glcanvas, x, y);
		double dx = /*real_coords[0]*/x - cad_demo.mouse[0];
		double dy = /*real_coords[1]*/y - cad_demo.mouse[1];

		double[] m = { 
				1.0, 0.0, 0.0, 
				0.0, 0.0, 1.0, 
				0.0, 0.0, 0.0, 
				0.0, 1.0, 0.0, 
				dx, dy, 0.0, 1.0 
		};

		GL_base.general_matrix = m.clone();
		glcanvas.display();
	}
}