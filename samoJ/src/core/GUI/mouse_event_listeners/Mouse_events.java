package core.GUI.mouse_event_listeners;

import it.unimi.dsi.fastutil.floats.FloatArrayList;
import open_dxf_lib.Color_rgb;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.LinkedList;

import core.Clip_algorithm;
import core.Core;
import core.Draw_snap_sign;
import core.Get_snap;
import core.navigation.Plan_motion;
import core.navigation.Zoom;
import samoJ.Shape;
import samoJ.SnapCoord;

/**
 * Mouse listener for all mouse actions
 * 
 * @author vlad
 * 
 */

public class Mouse_events extends mouse_state {
	private Core core = Core.c;

	@Override
	public void mousePressed(MouseEvent e) {
		// Middle button
		if (e.getButton() == MouseEvent.BUTTON2) {
			core.global.mouse_plan_motion = true;
			core.global.mouse = core.global.cursor_coords.clone();
		}
		// Left button
		if (e.getButton() == MouseEvent.BUTTON1) {
			// If program is in state of Shape-creating process or making
			// function
			if (core.global.draw_new_object == true) {
				core.global.current_function.run();
			} else {
				// Enable select with rectangle program state
				mouse_select();
			}
		}
		//Global_var.glcanvas.display();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON2) {
			float x = core.global.cursor_coords[0];
			float y = core.global.cursor_coords[1];
			Plan_motion.plan_motion(x, y);
			core.global.mouse_plan_motion = false;
			core.gui.glcanvas.display();
		}

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int notches = -e.getWheelRotation();
		float x = core.global.cursor_coords[0];
		float y = core.global.cursor_coords[1];
		core.zoom.zoom(x, y, notches);
		core.gui.glcanvas.display();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
		if (core.global.mouse_plan_motion == true) {
			float x = core.global.cursor_coords[0];
			float y = core.global.cursor_coords[1];
			Plan_motion.plan_motion(x, y);
			core.gui.glcanvas.display();
		}

	}

	/**
	 * Updating coordinates of cursor, this is unique place for get real coords
	 * of cursor
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		core.global.cursor_coords = core.glRender.get_real_coords(e.getX(), e.getY());
		float x = core.global.cursor_coords[0];
		float y = core.global.cursor_coords[1];
		// TO-DO snap to Shapes
		
		core.global.cursor_snap_coords = core.global.cursor_coords.clone();
		if (core.global.mouse_plan_motion == false) {
			core.gui.info_down.setText("Coordinates: X "
					+ String.format("%.2f", x) + "; Y "
					+ String.format("%.2f", y) + ";");

			if (core.global.select_mode == true) {
				selective_rect();
			}

			// If only cursor motion - find Shapes under cursor and snap
			// Get snap_distance as real in gl world:
			float real_snap_distance = core.values.snap_distance
					* core.values.current_scale;
			// Get Shapes under cursor
			LinkedList<Shape> current_Shapes = Clip_algorithm.simple_clip(
					 (x - real_snap_distance),
					 (y - real_snap_distance),
					 (x + real_snap_distance),
					 (y + real_snap_distance), core.global.theShapes);
			// Current Shape indication - if not (select_mode, draw_new_object)
			// and there are any Shapes under cursor
			if (!core.global.select_mode && !core.global.draw_new_object
					&& !current_Shapes.isEmpty()) {
				core.global.current_Shape = current_Shapes.get(0);
				// Make current shapeInteger for click-selecting
				FloatArrayList list1 = core.global.current_Shape.toListFloat();
				/*core.global.current_Shape_vertices = ArrayUtils
						.toPrimitive(list1.toArray(new Double[list1.size()]));*/
				core.global.current_Shape_vertices = list1.elements();
				
			} else {
				core.global.current_Shape_vertices = null;	
			}
			
			//Delete old snap sign 
			core.global.snap_sign_vertices = null;
			// Find snap
			SnapCoord snap = null;
			if (!current_Shapes.isEmpty()){
				snap = Get_snap.get_snap(core.global.cursor_coords,
						real_snap_distance, current_Shapes, core.values.snap_keys);


				if (snap != null) { 
					core.global.snap_sign_vertices = Draw_snap_sign.draw(snap,
							real_snap_distance);
					core.global.cursor_snap_coords = snap.getXYZ();
				}
			}
			
			//if program in state Draw new object - 
			//display new position of this object on current coords of cursor
			if (core.global.draw_new_object == true){//==ObjectMode.New_object??
				core.global.current_function.mouse_move_event();
			}
			core.gui.glcanvas.display();
		}
	}
	
	private void selective_rect() {
		core.global.select_rect_2 = core.global.cursor_snap_coords.clone();
		float x1 = core.global.select_rect_1[0];
		float y1 = core.global.select_rect_1[1];
		float x2 = core.global.select_rect_2[0];
		float y2 = core.global.select_rect_2[1];
		core.global.select_rect_vertices = new float[]{
				x1, y1, 0,
				x2, y1, 0, 
				x1, y2, 0,
				x2, y2, 0,
				x2, y1, 0,
				x2, y2, 0,
				x1, y1, 0,
				x1, y2, 0};
		if (x1 > x2){
			core.global.select_rect_color = new Color_rgb(255, 0, 0);
		}
		else
		{
			core.global.select_rect_color = new Color_rgb(0, 0, 255);
		}
		
	}
	
	void mouse_select(){
		if (core.global.select_mode == false){
			//TO-DO block if object on cursor!!!
			core.global.select_rect_1 = core.global.cursor_snap_coords.clone();
			core.global.select_mode = true;
		}
		else {
			core.global.select_rect_2 = core.global.cursor_snap_coords.clone();
			core.global.select_mode = false;
		}
		
	}
}