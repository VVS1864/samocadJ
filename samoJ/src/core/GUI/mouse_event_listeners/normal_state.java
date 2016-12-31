package core.GUI.mouse_event_listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;


import core.GL_base;
import core.Global_var;
import core.Select_objects;
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
			Global_var.mouse_plan_motion = true;
			Global_var.mouse = Global_var.cursor_coords.clone();
		}
		//Left button
		if (e.getButton() == MouseEvent.BUTTON1) {
			//If program is in state of Shape-creating process or making function
			if (Global_var.draw_new_object == true){
				Global_var.current_function.run();
			}
			else {
				//Enable select with rectangle program state
				Select_objects.mouse_select();
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON2) {
			System.out.println("Button 2 released...");
			double x = Global_var.cursor_coords[0];
			double y = Global_var.cursor_coords[1];
			Plan_motion.plan_motion(Global_var.glcanvas, x, y);
			Global_var.mouse_plan_motion = false;

		}
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int notches = -e.getWheelRotation();
		double x = Global_var.cursor_coords[0];
		double y = Global_var.cursor_coords[1];
		Zoom.zoom(x, y, notches);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
		if (Global_var.mouse_plan_motion == true) {
			double x = Global_var.cursor_coords[0];
			double y = Global_var.cursor_coords[1];
			Plan_motion.plan_motion(Global_var.glcanvas, x, y);

		}
		
	}
	/**
	 * Updating coordinates of cursor, this is unique place for get real coords of cursor
	 */
	@Override
	public void mouseMoved(MouseEvent e) {		
		Global_var.cursor_coords = GL_base.get_real_coords(Global_var.glcanvas, e.getX(),
				e.getY());
		double[] xy = Global_var.cursor_coords;
		//TO-DO snap to Shapes
		Global_var.cursor_snap_coords = Global_var.cursor_coords.clone();
		if (Global_var.mouse_plan_motion == false){
			Global_var.info_down.setText("Coordinates: X "+ String.format("%.2f", xy[0]) + "; Y "
				+ String.format("%.2f", xy[1]) + ";");
			
			if (Global_var.select_mode == true){
				Select_objects.draw_selective_rect();
			}
		}
		
	}
	

}
