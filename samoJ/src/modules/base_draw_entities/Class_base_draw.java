package modules.base_draw_entities;

import core.Core;

/**
 * Base "entity"-class for process of creating any Shape (line, circle, etc.) 
 * or functional actions with them (move, rotate, mirror, etc.).
 * Contain typically methods for create Shapes
 * @author vlad
 *
 */
public class Class_base_draw implements Interface_base_draw{
	protected Core core = Core.c;
	//Points for create object
	public float x1;
	public float y1;
	
	public float x2;
	public float y2;
	
	public float x3;//for Dimensions and mirror function
	public float y3;	
	
	/**
	 * Initial method of Shape-creating process or any function. 
	 * 
	 * @param message1 text value for first label (before command line)
	 * @param message2 text value for second label (after command line)
	 * @param next_class next class for next draw action
	 * @param init_class class for 'old_function'
	 */
	public void init_draw(String message1, String message2, Interface_base_draw next_class, 
			Interface_base_draw init_class){
		set_next(message1, message2, next_class);
		core.global.draw_new_object = true;
		core.global.old_function = init_class;
	}
	
	/**
	 * First method Shape-creating process - remember coordinates of point 1
	 * @param message1
	 * @param message2
	 * @param next_class
	 */
	public void draw_1(String message1, String message2, Interface_base_draw next_class){
		set_next(message1, message2, next_class);
		core.global.point_1_coords = core.global.cursor_snap_coords.clone();
	}
	
	/**
	 * Second method - for remember coordinates of point 2
	 * @param message1
	 * @param message2
	 * @param next_class
	 */
	public void draw_2(String message1, String message2, Interface_base_draw next_class){
		set_next(message1, message2, next_class);
		
		core.global.point_2_coords = core.global.cursor_snap_coords.clone();
		
		x1 = core.global.point_1_coords[0];
		y1 = core.global.point_1_coords[1];
		
		x2 = core.global.point_2_coords[0];
		y2 = core.global.point_2_coords[1];
		core.global.point_1_coords = core.global.cursor_snap_coords.clone();
	}
	public void dynamic_draw(){
		core.global.point_2_coords = core.global.cursor_snap_coords.clone();
		x1 = core.global.point_1_coords[0];
		y1 = core.global.point_1_coords[1];
		
		x2 = core.global.point_2_coords[0];
		y2 = core.global.point_2_coords[1];
		
	}
	
	/**
	 * Subsidiary method 
	 * @param message1
	 * @param message2
	 * @param next_class
	 */
	public void set_next(String message1, String message2, Interface_base_draw next_class){
		core.gui.info.setText(message1);
		core.gui.info2.setText(message2);
		core.global.current_function = next_class;
	}
	
	@Override
	public void run(){
	}
	
	@Override
	public void mouse_move_event() {		
	}
	
}