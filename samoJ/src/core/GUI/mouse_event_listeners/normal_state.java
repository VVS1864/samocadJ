package core.GUI.mouse_event_listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;


import core.GL_base;
import core.cad_demo;
import core.navigation.Plan_motion;
import core.navigation.Zoom;

public class normal_state extends mouse_state{

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON2) {
			System.out.println("Button 2 pressed...");
			cad_demo.mouse_plan_motion = true;
			cad_demo.mouse = GL_base.get_real_coords(cad_demo.glcanvas, e.getX(),
					e.getY());
		}
		;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON2) {
			System.out.println("Button 2 released...");
			Plan_motion.plan_motion(cad_demo.glcanvas, e.getX(), e.getY());
			cad_demo.mouse_plan_motion = false;

		}
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int notches = -e.getWheelRotation();
		double x = e.getX();
		double y = e.getY();
		Zoom.zoom(x, y, notches);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (cad_demo.mouse_plan_motion == true) {

			Plan_motion.plan_motion(cad_demo.glcanvas, e.getX(), e.getY());

		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		double[] xy = GL_base.get_real_coords(cad_demo.glcanvas, e.getX(),
				e.getY());
		double x = (double)xy[0];
		double y = (double)xy[1];
		cad_demo.info_down.setText("Coordinates: X "+ String.format("%.2f", x) + "; Y "
				+ String.format("%.2f", y) + ";");
		
	}
	

}
