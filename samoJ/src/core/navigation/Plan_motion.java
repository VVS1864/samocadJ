package core.navigation;

import core.Core;

public class Plan_motion {
	public static void plan_motion(float x, float y) {

		//double[] real_coords = GL_base.get_real_coords(glcanvas, x, y);
		float dx = /*real_coords[0]*/x - Core.c.global.mouse[0];
		float dy = /*real_coords[1]*/y - Core.c.global.mouse[1];

		float[] m = { 
				1, 0, 0, 0,
				0, 1, 0, 0, 
				0, 0, 1, 0, 
				dx, dy, 0, 1 
		};
		Core.c.glRender.general_matrix = m.clone();
		Core.c.gui.glcanvas.display();
	}
}