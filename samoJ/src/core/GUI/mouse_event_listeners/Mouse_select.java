package core.GUI.mouse_event_listeners;

import java.util.LinkedList;

import core.Clip_algorithm;
import core.Core;
import core.dynamic_entities.Selective_rect;
import samoJ.Shape;

public class Mouse_select {
	private Core core = Core.c;
	public Mouse_select(boolean shift_down) {

		Selective_rect r = core.global.selective_rect;
		if (r.enable == false) {
			// If current shape under cursor
			if (!core.global.current_Shape.get_collection().isEmpty()) {
				LinkedList<Shape> current_Shapes = core.global.current_Shape
						.get_collection();
				make_new_collection(current_Shapes, shift_down);
				
			}
			else {
				r.init(core.global.cursor_snap_coords,
						core.global.cursor_snap_coords);
				core.gui.info2.setText("Select shapes...");
			}

		}
		else {
			r.clear();
			LinkedList<Shape> current_Shapes = new LinkedList<Shape>();
			if (r.get_overlapping()) {
				current_Shapes = Clip_algorithm.simple_clip(
						r.get_x_min(), r.get_y_min(), r.get_x_max(),
						r.get_y_max(), core.global.theShapes);
			}
			else {
				current_Shapes = Clip_algorithm.enclose_clip(
						r.get_x_min(), r.get_y_min(), r.get_x_max(),
						r.get_y_max(), core.global.theShapes);
			}
			make_new_collection(current_Shapes, shift_down);

			
		}
	}

	private void make_new_collection(LinkedList<Shape> current_Shapes,
			boolean shift_down) {
		LinkedList<Shape> new_collection = new LinkedList<Shape>();
		LinkedList<Shape> old_collection = core.global.collection
				.get_collection();
		new_collection.addAll(old_collection);
		if (!shift_down) {
			for (Shape new_sh : current_Shapes) {
				if (!old_collection.contains(new_sh)) {
					new_collection.add(new_sh);
				}
			}
		}
		else {
			for (Shape new_sh : current_Shapes) {
				if (old_collection.contains(new_sh)) {
					new_collection.remove(new_sh);
				}
			}
		}
		core.global.collection.new_collection(new_collection);
		int size = new_collection.size();
		if (size > 0) {
			core.gui.info2.setText("Selected " + size + " shapes");
		}
		else {
			core.gui.info2.setText(" ");
		}
	
	}

}
