package core;

import open_dxf_lib.Color_rgb;
import open_dxf_lib.dash_type;

public class Values {

	public static double scale_size = 1.4;
	public static double current_scale = 1;


	public static double max_scale = 200;
	public static double min_scale = 0.0011;

	public static int circle_segments = 64;
	
	public static dash_type current_dash = dash_type.Continuous;
	public static int[] stipple = {4,1,1,1};
	public static double stipple_factor = 20;

	public static String[] line_types = new String[]{
			"_____________",
			"_ _ _ _ _ _ _",
			"____ _ ____ _",
			"____ _ _ ____",
	};

	public static String[] line_widths = new String[]{
			"1",
			"2",
			"3",
			"4",
	};

	public static int text_size = 350;
	public static double text_width_factor = 1.4;
	
	public static int dim_text_size = 350;

	public static int dim_text_offset = 50;
	public static int dim_arrowhead_size = 100;
	public static int dim_extend_lines = 200;
	public static int dim_extend_ticks = 100;
	public static String dim_arrowheads = "arch";
	public static String[] arrowhead_types = new String[]{
			"arch",
			"arrow",
	};
	
	public static int snap_distance = 10;
	
	//Colors 
	/**
	 * Color for new object (Class Color_rgb!)
	 */
	public static Color_rgb color = new Color_rgb(255, 0, 0);//{255, 0, 0};
	public static Color_rgb snap_color = new Color_rgb(0, 255, 255);//{0, 255, 255};
	/**
	 * Color for object under cursor
	 */
	public static Color_rgb current_shape_color = new Color_rgb(0, 255, 0);//{0, 255, 0};
	
// Paths
	public static String current_file_name = "New_drawing.dxf";
	/**
	 * path for save/open dialog and auto save
	 */
	public static String current_working_dir = System.getProperty("user.dir");
	
	
}