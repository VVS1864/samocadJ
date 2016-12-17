package core;


public class Values {

	public static double scale_size = 1.4;
	public static double current_scale = 1;


	public static double max_scale = 200;
	public static double min_scale = 0.0011;

	public static int circle_segments = 64;

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
}