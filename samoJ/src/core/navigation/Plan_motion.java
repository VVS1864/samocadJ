package core.navigation;



import com.jogamp.opengl.awt.GLCanvas;

import core.GL_base;
import core.Global_var;


public class Plan_motion {
	public static void plan_motion(GLCanvas glcanvas, double x, double y) {

		//double[] real_coords = GL_base.get_real_coords(glcanvas, x, y);
		double dx = /*real_coords[0]*/x - Global_var.mouse[0];
		double dy = /*real_coords[1]*/y - Global_var.mouse[1];

		double[] m = { 
				1.0, 0.0, 0.0, 0.0,
				0.0, 1.0, 0.0, 0.0, 
				0.0, 0.0, 1.0, 0.0, 
				dx, dy, 0.0, 1.0 
		};

		GL_base.general_matrix = m;
		glcanvas.display();
	}
}