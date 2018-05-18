package core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import open_dxf_lib.Color_rgb;
import open_dxf_lib.dash_type;
import samoJ.Shape;
import samoJ.SnapType;

public class Values {

	public float scale_size = 1.4f;
	public float current_scale = 1;


	public float max_scale = 200;
	public float min_scale = 0.0011f;

	public int circle_segments = 360;
	
	public dash_type current_dash = dash_type.CENTER;
	public int[] stipple = {4,1,1,1};
	public float stipple_factor = 3;
	/**
	 * Current line width 
	 */
	public int width = 1;
	public int dynamic_width = 2;
	public int select_rect_width = 1;
	
	public Map<String, dash_type> dash_types = new HashMap<String, dash_type>();
	
	
	public String[] line_widths = new String[]{
			"1",
			"2",
			"3",
			"4",
	};

	public double text_size = 350;
	public double text_width_factor = 1.4;
	
	public double dim_text_size = 350;

	public int dim_text_offset = 50;
	public int dim_arrowhead_size = 100;
	public int dim_extend_lines = 200;
	public int dim_extend_ticks = 100;
	public String dim_arrowheads = "arch";
	public String[] arrowhead_types = new String[]{
			"arch",
			"arrow",
	};
	
	public int snap_distance = 10;
	
	//Colors 
	/**
	 * Color for new object (Class Color_rgb!)
	 */
	public Color_rgb color = new Color_rgb(1f, 1f, 0f);//{255, 0, 0};
	public Color_rgb selected_shape_color = new Color_rgb(0f, 0f, 1f);
	public Color_rgb snap_color = new Color_rgb(0f, 1f, 1f);//{0, 255, 255};
	
	/**
	 * Color for object under cursor
	 */
	public Color_rgb current_shape_color = new Color_rgb(0f, 1f, 0f);//{0, 255, 0};
	
	/**
	 * Color for selective rect left
	 */
	public Color_rgb selective_rect_color_1 = new Color_rgb(255, 0, 0);
	/**
	 * Color for selective rect right
	 */
	public Color_rgb selective_rect_color_2 = new Color_rgb(0, 0, 255);
	
// Paths
	public String current_file_name = "New_drawing.dxf";
	/**
	 * path for save/open dialog and auto save
	 */
	public String current_working_dir = System.getProperty("user.dir");
	
	/**
	 * Keys for adjust snaps
	 */
	public  LinkedList<SnapType> snap_keys = new LinkedList<SnapType>(
			Arrays.asList(SnapType.EndPoint, SnapType.MidPoint,
					SnapType.Intersection, SnapType.NearPoint));
	
	public boolean trace_flag = false;
	public boolean object_trace_flag = false;
	public boolean ortho_flag = false;
	
	public Values() {
		dash_types.put("_____________", dash_type.Continuous);
		dash_types.put("_ _ _ _ _ _ _", dash_type.DASHED);
		dash_types.put("____ _ ____ _", dash_type.CENTER);
		dash_types.put("____ _ _ ____", dash_type.PHANTOM);
		//TODO load values from config
	}
	
}