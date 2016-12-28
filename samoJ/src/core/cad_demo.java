package core;

import com.jogamp.opengl.awt.GLCanvas;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import core.GUI.GUI;
import modules.base_draw_entities.base_draw;
import samoJ.Shape;

public class cad_demo {
	// Important elements of GUI
	public static JFrame jframe;
	public static GLCanvas glcanvas;
	public static JLabel info_down;
	public static JLabel info;
	public static JLabel info2;
	public static JTextField cmd;
	public static JDialog about_prog;
	public static JDialog dim_style;
	public static JDialog line_style;
	public static JDialog text_style;

	// Program state variables
	public static boolean mouse_plan_motion = false;
	public static boolean draw_new_object = false;

	// Program global variables
	public static double[] mouse = new double[2]; // coords of mouse when
													// mouse_plan_motion
	public static double[] cursor_coords = new double[2]; // latest coords of
															// cursor
	public static double[] point_1_coords = new double[2]; // coords of point 1
															// for draw objects
															// or make actions
															// with them
	public static double[] point_2_coords = new double[2]; // coords of point 1
	public static base_draw current_function = null;
	public static List<Shape> theShapes = new LinkedList<Shape>();

	public static void main(String[] args) {

		// GUI initialization
		final GUI graphical_interface = new GUI();
		program_state.set_default();
	}
}