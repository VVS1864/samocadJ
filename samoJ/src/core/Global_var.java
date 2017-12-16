package core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import modules.base_draw_entities.Interface_base_draw;
import open_dxf_lib.Color_rgb;
import samoJ.Dash_base;
import samoJ.Shape;
import samoJ.SnapType;

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

	/**
	 * True if program in select with rectangle state
	 */
	public  boolean select_mode = false;

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
	 * Point 1 of selective rectangle
	 */
	public  float[] select_rect_1 = new float[2];

	/**
	 * Point 2 of selective rectangle
	 */
	public  float[] select_rect_2 = new float[2];

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
	 * Shape under cursor for select with mouse left button click
	 */
	public  Shape current_Shape = null;

	/**
	 * General HashMap of Shapes - Lines, Circles, Dimensions... Any new Shape
	 * must be put here. Key Integer is Shape ID All of Shapes will be passed to
	 * OpenGL VBO
	 */
	public  Map<Integer, Shape> theShapes = new HashMap<Integer, Shape>();
	// public  Map<Integer, Shape> theShapes = new HashMap<Integer,
	// Shape>();
	// public  List<Shape> theShapes = new LinkedList<Shape>(); 
	public  Dash_base dashes = new Dash_base();
	/**
	 * Selective rectangle vertices for draw on canvas and select Shapes
	 */
	public  float[] select_rect_vertices = new float[12];

	/**
	 * Array for draw indication current Shape under cursor
	 */
	public  float[] current_Shape_vertices = null;

	/**
	 * For draw snap sign, it depend from snap type
	 */
	public  float[] snap_sign_vertices = null;
	
	public  float[] preview_object_vertices = null;

	// COLORES
	public  Color_rgb select_rect_color = new Color_rgb(1.0f, 1.0f, 1.0f);


	/**
	 * ID of Shape next for creation. It use as key in theShapes HashMap. After
	 * put Shape to theShapes - ID++
	 */
	public  Integer current_ID = 0;

	public  int N_DrawableLines = 0;
	public  int N_dynamic_lines = 0;
	
	public int N = 50020;// number of test lines
	
	public Global_var() {
		
	}
}