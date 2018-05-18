package core.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import core.Core;
import core.GUI.styles.dim_style;
import core.GUI.styles.line_style;
import core.GUI.styles.text_style;
import modules.Standart;
import modules.Standart_functions;
import open_dxf_lib.Color_dxf;
import open_dxf_lib.Color_rgb;
import modules.MakeButton;

public class GUI {
	private Core core = Core.c;
	// Important elements of GUI
	public GLCanvas glcanvas;
	private final JToolBar top_panel;
	private final JPanel trace_panel;
	public  JFrame jframe;
	public  JLabel info_down;
	public  JLabel info;
	public  JLabel info2;
	public  JTextField cmd;
	public  JDialog about_prog;
	public  JDialog dim_style;
	public  JDialog line_style;
	public  JDialog text_style;
	private JDialog color_chooser_dialog;
	
	private JComboBox combo_width;
	private JComboBox combo_line_type;
	private JButton button_color;
	private Color_chooser color_chooser;
	
	public JTextField field_text_size;
	public JTextField field_dim_text_size;
	
	public JButton button_trace;
	
	public GUI(){		
// GL CANVAS
		
		
		GLProfile glprofile = GLProfile.getDefault();
		GLCapabilities glcapabilities = new GLCapabilities(glprofile);
		glcanvas = new GLCanvas(glcapabilities);
		glcanvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		
		
		// GL event listener for canvas - initialization, display, resize...
		glcanvas.addGLEventListener(core.glcanvas_events);
		// Mouse event listener for cursor in glcanvas
		glcanvas.addMouseListener(core.mouse);
		glcanvas.addMouseMotionListener(core.mouse);
		glcanvas.addMouseWheelListener(core.mouse);
		glcanvas.addKeyListener(core.key);
		
		//Key listener for glcanvas
		//Global_var.glcanvas.addKeyListener(new gl_key_listener());

// Info labels
		info = new JLabel();
		//cad_demo.info.setText("Comand:");
		info2 = new JLabel();
		//cad_demo.info2.setText(" ");
		info_down = new JLabel();
		info_down.setText("Coordinates: X "+ 0 + "; Y " + 0 + ";");
		
		
//Command line
		cmd = new JTextField(6);
		//Global_var.cmd.addKeyListener(new gl_key_listener());
		
//Frame for all elements
		jframe = new JFrame("Cad_demo, display " + core.global.N
				+ " lines");

		jframe.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowevent) {
				close();
			}
		});
		
		jframe.setLayout(new BorderLayout());
		jframe.setSize(800, 640);
		
		//Icon 
		URL imageURL = GUI.class.getResource("/res/icon.gif");
		if (imageURL != null) {  
			ImageIcon img = new ImageIcon(imageURL);
			jframe.setIconImage(img.getImage());
		}
		
//Action listeners
		ActionListener save_action_listener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				new Save_action();
			}
		};

		//Action listeners trace buttons
		ActionListener trace_action_listener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				trace_action();
			}
		};
		
		// Action listeners combo width
		ActionListener combo_width_action_listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				width_action();
			}
		};
		// Action listeners combo width
		ActionListener combo_line_type_action_listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				line_type_action();
			}
		};
		// Action listeners combo width
		ActionListener button_color_action_listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				set_color_action();
			}
		};
//Addition standard button bars
		Standart.addButtonsBar(jframe);
		Standart_functions.addButtonsBar(jframe);
		
//Add all
		//command line container
		final JPanel cmd_panel = new JPanel();
		cmd_panel.setLayout(new BoxLayout(cmd_panel, BoxLayout.X_AXIS));
		cmd_panel.add(info);
		cmd_panel.add(cmd);
		cmd_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//trace buttons container 
		trace_panel = new JPanel();
		trace_panel.setLayout(new BoxLayout(trace_panel, BoxLayout.X_AXIS));
		//buttons
		Color white = new Color(255, 255, 255);
		JButton button_ortho = new JButton("Ortho");
		button_ortho.setBackground(white);
		button_trace = new JButton("Trace");
		button_trace.setBackground(white);
		button_trace.addActionListener(trace_action_listener);
		JButton button_object_trace = new JButton("Object trace");
		button_object_trace.setBackground(white);
		JButton button_snap_near = new JButton("Snap near");
		button_snap_near.setBackground(white);
		trace_panel.add(button_ortho);
		trace_panel.add(button_trace);
		trace_panel.add(button_object_trace);
		trace_panel.add(button_snap_near);
		trace_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//South container 
		final JPanel bot_panel = new JPanel();
		bot_panel.setLayout(new BoxLayout(bot_panel, BoxLayout.Y_AXIS));
		bot_panel.add(cmd_panel);
		bot_panel.add(info2);
		bot_panel.add(info_down);
		bot_panel.add(trace_panel);
		
		//North container 
		//final JPanel top_panel = new JPanel();
		top_panel = new JToolBar("Still draggable", JToolBar.HORIZONTAL);
		//top_panel.setLayout(new BoxLayout(top_panel, BoxLayout.X_AXIS));
		//Elements for North container
		JButton button_save = MakeButton.makeButton("saveas.gif", "Save", "Save");
		button_save.addActionListener(save_action_listener);
		JButton button_open = MakeButton.makeButton("open.gif", "Open", "Open");
		button_color = MakeButton.makeButton("none", "Current color", " ");
		button_color.setBackground(new Color(255, 255, 255));
		button_color.setMaximumSize(new Dimension(60, 40));
		button_color.addActionListener(button_color_action_listener);
		JLabel label_width = MakeButton.makeLabel("width.gif", "Width");
		
		combo_width = new JComboBox(core.values.line_widths); 
		combo_width.setMaximumSize(new Dimension(60, 30));
		combo_width.setSelectedIndex(0);
		combo_width.addActionListener(combo_width_action_listener);
		
		combo_line_type = new JComboBox(core.values.dash_types.keySet().toArray()); 
		combo_line_type.setSelectedIndex(0);
		combo_line_type.setMaximumSize(new Dimension(120, 30));
		combo_line_type.addActionListener(combo_line_type_action_listener);
		
		JLabel label_text_size = MakeButton.makeLabel("text_size.gif", "Text size");
		
		field_text_size = new JTextField();
		field_text_size.setText(Double.toString(core.values.text_size));
		field_text_size.setMaximumSize(new Dimension(60, 30));
				
		JLabel label_dim_text_size = MakeButton.makeLabel("dim_size.gif", "Dimention size");
		
		field_dim_text_size = new JTextField();
		field_dim_text_size.setText(Double.toString(core.values.dim_text_size));
		field_dim_text_size.setMaximumSize(new Dimension(60, 30));
		
		JButton button_undo = MakeButton.makeButton("undo.gif", "Undo", "Undo");
		
		//Add elements to North container
		int g = 10;
		top_panel.add(button_save);
		top_panel.add(button_open);
		
		top_panel.add(Box.createHorizontalStrut(g));
		
		top_panel.add(button_color);
		
		top_panel.add(Box.createHorizontalStrut(g));
		
		top_panel.add(label_width);
		top_panel.add(combo_width);
		
		top_panel.add(Box.createHorizontalStrut(g));
		
		top_panel.add(combo_line_type);
		
		top_panel.add(Box.createHorizontalStrut(g));
		
		top_panel.add(label_text_size);
		top_panel.add(field_text_size);
		
		top_panel.add(Box.createHorizontalStrut(g));
		
		top_panel.add(label_dim_text_size);
		top_panel.add(field_dim_text_size);
		
		top_panel.add(Box.createHorizontalStrut(g));
		
		top_panel.add(button_undo);
		
		//Addition all containers to frame
		jframe.add(top_panel, BorderLayout.NORTH);
		jframe.add(bot_panel, BorderLayout.SOUTH);
		jframe.add(glcanvas, BorderLayout.CENTER);
		
//MENU BAR
		
		//Menu bar
		JMenuBar menuBar = new JMenuBar();
		
		
		JMenu menu_file = new JMenu("File");
		JMenu menu_draw = new JMenu("Draw");
		JMenu menu_function = new JMenu("Functions");
		JMenu menu_format = new JMenu("Format");
		JMenu menu_help = new JMenu("Help");
		
		//Sub menu File
		JMenuItem item_open = new JMenuItem("Open");
		JMenuItem item_saveas = new JMenuItem("Save as");
		JMenuItem item_print = new JMenuItem("Print");
		JMenuItem item_exit = new JMenuItem("Exit");
		
		//Actions menu File
		item_saveas.addActionListener(save_action_listener);
		
		item_exit.addActionListener(new ActionListener()
		  {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				jframe.dispose();
				System.exit(0);//Exit
			}

		  });
		
		//Add sub menus to File
		menu_file.add(item_open);
		menu_file.add(item_saveas);
		menu_file.add(item_print);
		menu_file.add(item_exit);
		
		//Sub menu Draw
		JMenuItem item_line = new JMenuItem("Line");
		JMenuItem item_lcircle = new JMenuItem("Circle");
		JMenuItem item_arc = new JMenuItem("Arc");
		JMenuItem item_dim = new JMenuItem("Dimention");
		JMenuItem item_text = new JMenuItem("Text");
		
		//Add sub menus to Draw
		menu_draw.add(item_line);
		menu_draw.add(item_lcircle);
		menu_draw.add(item_arc);
		menu_draw.add(item_dim);
		menu_draw.add(item_text);
		
		//Sub menu Function
		JMenuItem item_move = new JMenuItem("Move");
		JMenuItem item_copy = new JMenuItem("Copy");
		JMenuItem item_mirror = new JMenuItem("Mirror");
		JMenuItem item_rotate = new JMenuItem("Rotate");
		JMenuItem item_offset = new JMenuItem("Offset");
		JMenuItem item_copy_properties = new JMenuItem("Copy properties");
		JMenuItem item_fillet = new JMenuItem("Fillet");
		JMenuItem item_trim = new JMenuItem("Trim");
		JMenuItem item_extend = new JMenuItem("Extend");
		JMenuItem item_scale = new JMenuItem("Scale");
		JMenuItem item_chain_dim_line = new JMenuItem("Chain dim line");
		
		//Add sub menus to Functions
		menu_function.add(item_move);
		menu_function.add(item_copy);
		menu_function.add(item_mirror);
		menu_function.add(item_rotate);
		menu_function.add(item_offset);
		menu_function.add(item_copy_properties);
		menu_function.add(item_fillet);
		menu_function.add(item_trim);
		menu_function.add(item_extend);
		menu_function.add(item_scale);
		menu_function.add(item_chain_dim_line);
		
		//Sub menu Format
		JMenuItem item_dim_style = new JMenuItem("Dimension style");
		JMenuItem item_line_style = new JMenuItem("Line style");
		JMenuItem item_text_style = new JMenuItem("Text style");
		JMenuItem item_object_properties = new JMenuItem("object properties");
		
		//Actions menu Format
		item_dim_style.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (dim_style == null){
					dim_style = new dim_style();
				}
			}
		});
		
		item_line_style.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (line_style == null){
					line_style = new line_style();
				}
			}
		});
		
		item_text_style.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (text_style == null){
					text_style = new text_style();
				}
			}
		});
		
		//Add sub menus to Format
		menu_format.add(item_dim_style);
		menu_format.add(item_line_style);
		menu_format.add(item_text_style);
		menu_format.add(item_object_properties);
		
		//Sub menu Help
		JMenuItem item_about = new JMenuItem("About");

		//Actions menu Help
		item_about.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (about_prog == null){
					about_prog = new about();
				}
			}
		});

		//Add sub menus to Format
		menu_help.add(item_about);
		
				
		//Add menus to menuBar
		menuBar.add(menu_file);
		menuBar.add(menu_draw);
		menuBar.add(menu_function);
		menuBar.add(menu_format);
		menuBar.add(menu_help);
		
		jframe.setJMenuBar(menuBar);
		/// ???!!! TODO
		for (Component c : top_panel.getComponents()) {
			c.addKeyListener(core.key);
		}
		for (Component c : trace_panel.getComponents()) {
			c.addKeyListener(core.key);
		}
		for (Component c : bot_panel.getComponents()) {
			c.addKeyListener(core.key);
		}
		for (Component c : cmd_panel.getComponents()) {
			c.addKeyListener(core.key);
		}
		
		jframe.setVisible(true);
		
		
	}
	public void close() {
		
		jframe.dispose();
		System.exit(0);
	}
	
	private void trace_action() {
		core.values.trace_flag = but_swich(button_trace, core.values.trace_flag);
	}
	
	private void width_action() {
		core.values.width = Integer.parseInt(core.values.line_widths[combo_width.getSelectedIndex()]);
	}
	
	private void line_type_action() {
		core.values.current_dash = core.values.dash_types.get(combo_line_type.getSelectedItem());
	}
	
	private void set_color_action() {
		// Action listeners OK in color chooser
		ActionListener OK_color_action_listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color c = color_chooser.getColor();
				if (c != null) {
					Color_dxf c_dxf = new Color_dxf(c.getRed(), c.getGreen(),
							c.getBlue());
					core.values.color = new Color_rgb(c_dxf.get_rgb());
					c = new Color(core.values.color.get_r(),
							core.values.color.get_g(),
							core.values.color.get_b());
					button_color.setBackground(c);
				}
			}
		};
		if (color_chooser_dialog == null) {
			color_chooser = new Color_chooser();
			color_chooser_dialog = JColorChooser.createDialog(jframe, "Color Chooser",
	                true, color_chooser, OK_color_action_listener, null);
		}
		
		if (color_chooser_dialog.isVisible()) {
			color_chooser_dialog.setVisible(false);
		}
		else {
			color_chooser_dialog.setVisible(true);
		}
	}
		
	
	
	private boolean but_swich(JButton button, boolean flag) {
		if (flag) {
			Color white = new Color(255, 255, 255);
			button.setBackground(white);
			return false;
		}
		else {
			Color blue = new Color(0, 0, 255);
			button.setBackground(blue);
			return true;
		}
	}
	
	/*
	public void set_GL_listeners(Mouse_events mouse, GL_canvas_event_listener glcanvas_events,
			GL_key_listener key) {
		// GL event listener for canvas - initialization, display, resize...
		glcanvas.addGLEventListener(glcanvas_events);
		
		// Mouse event listener for cursor in glcanvas
		glcanvas.addMouseListener(mouse);
		glcanvas.addMouseMotionListener(mouse);
		glcanvas.addMouseWheelListener(mouse);
		
		///???!!! TODO
		for(Component c: top_panel.getComponents()) {
			c.addKeyListener(key);
		}
		for(Component c: trace_panel.getComponents()) {
			c.addKeyListener(key);
		}
		glcanvas.addKeyListener(key);
	}*/
}