package core;


import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import core.dynamic_entities.Preview_shape;
import core.dynamic_entities.Selected;
import core.dynamic_entities.Selective_rect;
import core.dynamic_entities.Snap_sign;
import modules.base_draw_entities.Interface_base_draw;
import open_dxf_lib.Color_rgb;
import samoJ.Dash_base;
import samoJ.Shape;

public class Global_var {
	// Program state variables
	/**
	 * True if user is press middle mouse button and drag it
	 */
	public  boolean mouse_plan_motion = false;

	/**
	 * True if program in state of draw new Shape or active function state
	 * (copy, mirror, rotate...)
	 */
	public  boolean draw_new_object = false;


	// Program global variables
	/**
	 * Coords of mouse when mouse_plan_motion
	 */
	public  float[] mouse = new float[2];

	/**
	 * latest coords of cursor
	 */
	public  float[] cursor_coords = new float[2];

	/**
	 * latest coords of cursor with snap
	 */
	public  float[] cursor_snap_coords = new float[2];

	/**
	 * coords of point 1 for draw objects or make actions with them
	 */
	public  float[] point_1_coords = new float[2];

	/**
	 * Coords of point 2 for draw objects or make actions with them
	 */
	public  float[] point_2_coords = new float[2];
	
	/**
	 * object for manage selective rectangle
	 */
	public Selective_rect selective_rect = new Selective_rect();
	
	/**
	 * object for manage selected Shapes
	 */
	public Selected collection = new Selected();
	
	/**
	 * indication Shape under cursor
	 */
	public Selected current_Shape = new Selected();
	
	/**
	 * snap sign
	 */
	public Snap_sign snap_sign = new Snap_sign();
	
	/**
	 * snap sign
	 */
	public Preview_shape preview_shape = new Preview_shape();
	/**
	 * Class implements Interface_base_draw for run an action if user clicks
	 * left mouse button and draw_new_object == true
	 */
	public  Interface_base_draw current_function = null;
	
	/**
	 * Class for repeat last user action  
	 */
	public  Interface_base_draw old_function = null;
	
	/**
	 * General HashMap of Shapes - Lines, Circles, Dimensions... Any new Shape
	 * must be put here. Key Integer is Shape ID. All of Shapes will be passed to
	 * OpenGL VBO
	 */
	public  Map<Integer, Shape> theShapes = new HashMap<Integer, Shape>();
	
	public  Dash_base dashes = new Dash_base();
	

	/**
	 * ID of Shape next for creation. It use as key in theShapes HashMap. After
	 * put Shape to theShapes - ID++
	 */
	public  Integer current_ID = 0;

	public  int N_DrawableLines = 0;
	public  int N_dynamic_lines = 0;
	public  int N_fast_dynamic_lines = 0;
	
	public int N = 50;// number of test lines
	
	public Global_var() {
		
	}
}