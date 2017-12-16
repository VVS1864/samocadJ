package core;

import core.GUI.GUI;
import core.GUI.mouse_event_listeners.Mouse_events;
import core.navigation.Zoom;
import core.GUI.GL_canvas_event_listener;
import core.GUI.GL_key_listener;

public class Core {
	public static Core c;
	// Global variables
	public Global_var global;
			
	//User-editable variables
	public Values values;
	
	public GUI gui;
	public GL_base glRender;
	public Program_state program_state;
	
	public Mouse_events mouse;
	public GL_canvas_event_listener glcanvas_events;
	public GL_key_listener key;
	
	public Zoom zoom;
	
	public Core() {
		//field for access from another modules
		c = this;
		global = new Global_var();
		values = new Values();
		// GL_base initialization
		glRender = new GL_base();
		
		//Navigation
		zoom = new Zoom();
		//Set event listeners for GL
		mouse = new Mouse_events();
		glcanvas_events = new GL_canvas_event_listener();
		key = new GL_key_listener();
		// Set default state
		program_state = new Program_state();
		
		// GUI initialization
		gui = new GUI();
		//glRender.init_GL();		
		//gui.set_GL_listeners(mouse, glcanvas_events, key);
		//gui.jframe.setVisible(true);
		
		program_state.set_default();
		

		
		
		
		
	}

}
