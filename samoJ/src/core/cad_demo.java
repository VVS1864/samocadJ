package core;


import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import core.GUI.GUI;

public class cad_demo {

	public static JFrame jframe; 
	public static boolean mouse_plan_motion = false;
	public static double[] mouse = new double[2];
	public static GLCanvas glcanvas;
	public static JLabel info_down;
	public static JLabel info;
	public static JLabel info2;
	public static JDialog about_prog;
	public static JDialog dim_style;
	public static JDialog line_style;
	public static JDialog text_style;
	
	public static void main(String[] args) {
		
		//GUI initialization
		final GUI graphical_interface = new GUI();
		
	}
}