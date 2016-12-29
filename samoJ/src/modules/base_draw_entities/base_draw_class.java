package modules.base_draw_entities;

import core.Global_var;

/**
 * Base "entity"-class for process of creating any Shape (line, circle, etc.) 
 * or functional actions with them (move, rotate, mirror, etc.).
 * Contain typically methods for create Shapes
 * @author vlad
 *
 */
public class base_draw_class


{
	
	//Points for create object
	public int x1;
	public int y1;
	
	public int x2;
	public int y2;
	
	public int x3;//for Dimensions and mirror function
	public int y3;	
	
	/**
	 * Initial method of Shape-creating process or any function. 
	 * 
	 * @param message1 text value for first label (before command line)
	 * @param message2 text value for second label (after command line)
	 * @param next_class next class for next draw action
	 */
	public void init_draw(String message1, String message2, base_draw next_class){
		set_next(message1, message2, next_class);
		Global_var.draw_new_object = true;

	}
	
	/**
	 * First method Shape-creating process - remember cad_demo.point_1_coords - 
	 * coordinates of point 1
	 * @param message1
	 * @param message2
	 * @param next_class
	 */
	public void draw_1(String message1, String message2, base_draw next_class){
		set_next(message1, message2, next_class);
		Global_var.point_1_coords = Global_var.cursor_coords.clone();
	}
	
	/**
	 * Second method - for remember coordinates of point 2
	 * @param message1
	 * @param message2
	 * @param next_class
	 */
	public void draw_2(String message1, String message2, base_draw next_class){
		set_next(message1, message2, next_class);
		
		Global_var.point_2_coords = Global_var.cursor_coords.clone();
		
		x1 = (int)Global_var.point_1_coords[0];
		y1 = (int)Global_var.point_1_coords[1];
		
		x2 = (int)Global_var.point_2_coords[0];
		y2 = (int)Global_var.point_2_coords[1];
		Global_var.point_1_coords = Global_var.cursor_coords.clone();
	}
	
	/**
	 * Subsidiary method 
	 * @param message1
	 * @param message2
	 * @param next_class
	 */
	public void set_next(String message1, String message2, base_draw next_class){
		Global_var.info.setText(message1);
		Global_var.info2.setText(message2);
		Global_var.current_function = next_class;
	}
	
}