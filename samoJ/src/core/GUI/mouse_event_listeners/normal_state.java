package core.GUI.mouse_event_listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;


import core.GL_base;
import core.cad_demo;
import core.navigation.Plan_motion;
import core.navigation.Zoom;

/**
 * Mouse listener for all mouse actions
 * @author vlad
 *
 */
public class normal_state extends mouse_state{

	@Override
	public void mousePressed(MouseEvent e) {
		//Middle button
		if (e.getButton() == MouseEvent.BUTTON2) {
			System.out.println("Button 2 pressed...");
			cad_demo.mouse_plan_motion = true;
			cad_demo.mouse = cad_demo.cursor_coords.clone();
		}
		//Left button
		if (e.getButton() == MouseEvent.BUTTON1) {
			//If program is in state of Shape-creating process or making function
			if (cad_demo.draw_new_object == true){
				cad_demo.current_function.run();
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON2) {
			System.out.println("Button 2 released...");
			double x = cad_demo.cursor_coords[0];
			double y = cad_demo.cursor_coords[1];
			Plan_motion.plan_motion(cad_demo.glcanvas, x, y);
			cad_demo.mouse_plan_motion = false;

		}
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int notches = -e.getWheelRotation();
		double x = cad_demo.cursor_coords[0];
		double y = cad_demo.cursor_coords[1];
		Zoom.zoom(x, y, notches);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
		if (cad_demo.mouse_plan_motion == true) {
			double x = cad_demo.cursor_coords[0];
			double y = cad_demo.cursor_coords[1];
			Plan_motion.plan_motion(cad_demo.glcanvas, x, y);

		}
		
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		//Updating coordinates of cursor
		//this is unique place for get real coords of cursor
		
		double[] xy = GL_base.get_real_coords(cad_demo.glcanvas, e.getX(),
				e.getY());
		double x = (double)xy[0];
		double y = (double)xy[1];
		cad_demo.cursor_coords[0] = x;
		cad_demo.cursor_coords[1] = y;
		cad_demo.info_down.setText("Coordinates: X "+ String.format("%.2f", x) + "; Y "
				+ String.format("%.2f", y) + ";");
		
	}
	

}
