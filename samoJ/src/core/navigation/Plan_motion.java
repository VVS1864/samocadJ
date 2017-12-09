package core.navigation;



import com.jogamp.opengl.awt.GLCanvas;

import core.GL_base;
import core.Global_var;


public class Plan_motion {
	public static void plan_motion(GLCanvas glcanvas, float x, float y) {

		//double[] real_coords = GL_base.get_real_coords(glcanvas, x, y);
		float dx = /*real_coords[0]*/x - Global_var.mouse[0];
		float dy = /*real_coords[1]*/y - Global_var.mouse[1];

		float[] m = { 
				1, 0, 0, 0,
				0, 1, 0, 0, 
				0, 0, 1, 0, 
				dx, dy, 0, 1 
		};
		GL_base.general_matrix = m.clone();
		//GL_base.general_matrix.glMultMatrixf(m, 0);
		glcanvas.display();
	}
}