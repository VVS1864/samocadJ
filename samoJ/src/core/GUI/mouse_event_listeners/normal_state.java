package core.GUI.mouse_event_listeners;

import it.unimi.dsi.fastutil.floats.FloatArrayList;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

import org.apache.commons.lang3.ArrayUtils;

import core.Clip_algorithm;
import core.Draw_snap_sign;
import core.GL_base;
import core.Get_snap;
import core.Global_var;
import core.Select_objects;
import core.Values;
import core.navigation.Plan_motion;
import core.navigation.Zoom;
import samoJ.Shape;
import samoJ.SnapCoord;
import samoJ.SnapType;

/**
 * Mouse listener for all mouse actions
 * 
 * @author vlad
 * 
 */
public class normal_state extends mouse_state {

	@Override
	public void mousePressed(MouseEvent e) {
		// Middle button
		if (e.getButton() == MouseEvent.BUTTON2) {
			Global_var.mouse_plan_motion = true;
			Global_var.mouse = Global_var.cursor_coords.clone();
		}
		// Left button
		if (e.getButton() == MouseEvent.BUTTON1) {
			// If program is in state of Shape-creating process or making
			// function
			if (Global_var.draw_new_object == true) {
				Global_var.current_function.run();
			} else {
				// Enable select with rectangle program state
				Select_objects.mouse_select();
			}
		}
		//Global_var.glcanvas.display();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON2) {
			float x = Global_var.cursor_coords[0];
			float y = Global_var.cursor_coords[1];
			Plan_motion.plan_motion(Global_var.glcanvas, x, y);
			Global_var.mouse_plan_motion = false;
			Global_var.glcanvas.display();
		}

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int notches = -e.getWheelRotation();
		float x = Global_var.cursor_coords[0];
		float y = Global_var.cursor_coords[1];
		Zoom.zoom(x, y, notches);
		Global_var.glcanvas.display();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
		if (Global_var.mouse_plan_motion == true) {
			float x = Global_var.cursor_coords[0];
			float y = Global_var.cursor_coords[1];
			Plan_motion.plan_motion(Global_var.glcanvas, x, y);
			Global_var.glcanvas.display();
		}

	}

	/**
	 * Updating coordinates of cursor, this is unique place for get real coords
	 * of cursor
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		Global_var.cursor_coords = GL_base.get_real_coords(Global_var.glcanvas,
				e.getX(), e.getY());
		float x = Global_var.cursor_coords[0];
		float y = Global_var.cursor_coords[1];
		// TO-DO snap to Shapes
		
		Global_var.cursor_snap_coords = Global_var.cursor_coords.clone();
		if (Global_var.mouse_plan_motion == false) {
			Global_var.info_down.setText("Coordinates: X "
					+ String.format("%.2f", x) + "; Y "
					+ String.format("%.2f", y) + ";");

			if (Global_var.select_mode == true) {
				Select_objects.draw_selective_rect();
			}

			// If only cursor motion - find Shapes under cursor and snap
			// Get snap_distance as real in gl world:
			float real_snap_distance = Values.snap_distance
					* Values.current_scale;
			// Get Shapes under cursor
			LinkedList<Shape> current_Shapes = Clip_algorithm.simple_clip(
					 (x - real_snap_distance),
					 (y - real_snap_distance),
					 (x + real_snap_distance),
					 (y + real_snap_distance), Global_var.theShapes);
			// Current Shape indication - if not (select_mode, draw_new_object)
			// and there are any Shapes under cursor
			if (!Global_var.select_mode && !Global_var.draw_new_object
					&& !current_Shapes.isEmpty()) {
				Global_var.current_Shape = current_Shapes.get(0);
				// Make current shapeInteger for click-selecting
				FloatArrayList list1 = Global_var.current_Shape.toListFloat();
				/*Global_var.current_Shape_vertices = ArrayUtils
						.toPrimitive(list1.toArray(new Double[list1.size()]));*/
				Global_var.current_Shape_vertices = list1.elements();
				
			} else {
				Global_var.current_Shape_vertices = null;	
			}
			
			//Delete old snap sign 
			Global_var.snap_sign_vertices = null;
			// Find snap
			SnapCoord snap = null;
			if (!current_Shapes.isEmpty()){
				snap = Get_snap.get_snap(Global_var.cursor_coords,
						real_snap_distance, current_Shapes, Global_var.snap_keys);


				if (snap != null) { 
					Global_var.snap_sign_vertices = Draw_snap_sign.draw(snap,
							real_snap_distance);
					Global_var.cursor_snap_coords = snap.getXYZ();
				}
			}
			
			//if program in state Draw new object - 
			//display new position of this object on current coords of cursor
			if (Global_var.draw_new_object == true){//==ObjectMode.New_object??
				Global_var.current_function.mouse_move_event();
			}
			Global_var.glcanvas.display();
		}
	}
}