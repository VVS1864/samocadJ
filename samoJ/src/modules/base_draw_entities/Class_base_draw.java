package modules.base_draw_entities;

import core.Global_var;
import modules.standard_objects.line.draw_line;

/**
 * Base "entity"-class for process of creating any Shape (line, circle, etc.) 
 * or functional actions with them (move, rotate, mirror, etc.).
 * Contain typically methods for create Shapes
 * @author vlad
 *
 */
public class Class_base_draw


{
	
	//Points for create object
	public double x1;
	public double y1;
	
	public double x2;
	public double y2;
	
	public double x3;//for Dimensions and mirror function
	public double y3;	
	
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
		Global_var.draw_new_object = true;
		Global_var.old_function = init_class;
	}
	
	/**
	 * First method Shape-creating process - remember coordinates of point 1
	 * @param message1
	 * @param message2
	 * @param next_class
	 */
	public void draw_1(String message1, String message2, Interface_base_draw next_class){
		set_next(message1, message2, next_class);
		Global_var.point_1_coords = Global_var.cursor_snap_coords.clone();
	}
	
	/**
	 * Second method - for remember coordinates of point 2
	 * @param message1
	 * @param message2
	 * @param next_class
	 */
	public void draw_2(String message1, String message2, Interface_base_draw next_class){
		set_next(message1, message2, next_class);
		
		Global_var.point_2_coords = Global_var.cursor_snap_coords.clone();
		
		x1 = (double)Global_var.point_1_coords[0];
		y1 = (double)Global_var.point_1_coords[1];
		
		x2 = (double)Global_var.point_2_coords[0];
		y2 = (double)Global_var.point_2_coords[1];
		Global_var.point_1_coords = Global_var.cursor_snap_coords.clone();
	}
	public void dynamic_draw(){
		Global_var.point_2_coords = Global_var.cursor_snap_coords.clone();
		x1 = (double)Global_var.point_1_coords[0];
		y1 = (double)Global_var.point_1_coords[1];
		
		x2 = (double)Global_var.point_2_coords[0];
		y2 = (double)Global_var.point_2_coords[1];
		
	}
	
	/**
	 * Subsidiary method 
	 * @param message1
	 * @param message2
	 * @param next_class
	 */
	public void set_next(String message1, String message2, Interface_base_draw next_class){
		Global_var.info.setText(message1);
		Global_var.info2.setText(message2);
		Global_var.current_function = next_class;
	}
	
}