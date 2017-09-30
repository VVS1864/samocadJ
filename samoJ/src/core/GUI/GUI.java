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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.Timer;
import javax.swing.JFileChooser;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import core.GL_base;
import core.Values;
import core.Global_var;
import core.GUI.mouse_event_listeners.normal_state;
import core.GUI.styles.dim_style;
import core.GUI.styles.line_style;
import core.GUI.styles.text_style;
import modules.Standart;
import modules.Standart_functions;
import modules.MakeButton;

public class GUI {
	
//Dialogs
	 	 
	public GUI(){
		
// GL CANVAS
		GLProfile glprofile = GLProfile.getDefault();
		GLCapabilities glcapabilities = new GLCapabilities(glprofile);
		Global_var.glcanvas = new GLCanvas(glcapabilities);
		Global_var.glcanvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		
		// GL event listener for canvas - initialization, display, resize...
		Global_var.glcanvas.addGLEventListener(new core.GUI.glcanvas_event_listener());
		
		//Mouse event listener for cursor in glcanvas
		normal_state mouse_listener = new core.GUI.mouse_event_listeners.normal_state();
		
		Global_var.glcanvas.addMouseListener(mouse_listener);
		Global_var.glcanvas.addMouseMotionListener(mouse_listener);
		Global_var.glcanvas.addMouseWheelListener(mouse_listener);
		
		//Key listener for glcanvas
		Global_var.glcanvas.addKeyListener(new gl_key_listener());

// Info labels
		Global_var.info = new JLabel();
		//cad_demo.info.setText("Comand:");
		Global_var.info2 = new JLabel();
		//cad_demo.info2.setText(" ");
		Global_var.info_down = new JLabel();
		Global_var.info_down.setText("Coordinates: X "+ 0 + "; Y " + 0 + ";");
		
		
//Command line
		Global_var.cmd = new JTextField(6);
		
//Frame for all elements
		Global_var.jframe = new JFrame("Cad_demo, display " + GL_base.N
				+ " lines");

		Global_var.jframe.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowevent) {
				Global_var.jframe.dispose();
				System.exit(0);
			}
		});
		
		Global_var.jframe.setLayout(new BorderLayout());
		Global_var.jframe.setSize(800, 640);
		
		//Icon 
		URL imageURL = GUI.class.getResource("/res/icon.gif");
		if (imageURL != null) {  
			ImageIcon img = new ImageIcon(imageURL);
			Global_var.jframe.setIconImage(img.getImage());
		}
		
//Action listeners
		ActionListener save_action_listener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				new Save_action();
			}
		};
		
//Addition standard button bars
		Standart.addButtonsBar(Global_var.jframe);
		Standart_functions.addButtonsBar(Global_var.jframe);
		
//Add all
		//command line container
		final JPanel cmd_panel = new JPanel();
		cmd_panel.setLayout(new BoxLayout(cmd_panel, BoxLayout.X_AXIS));
		cmd_panel.add(Global_var.info);
		cmd_panel.add(Global_var.cmd);
		cmd_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//trace buttons container 
		final JPanel trace_panel = new JPanel();
		trace_panel.setLayout(new BoxLayout(trace_panel, BoxLayout.X_AXIS));
		//buttons
		Color white = new Color(255, 255, 255);
		JButton button_ortho = new JButton("Ortho");
		button_ortho.setBackground(white);
		JButton button_trace = new JButton("Trace");
		button_trace.setBackground(white);
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
		bot_panel.add(Global_var.info2);
		bot_panel.add(Global_var.info_down);
		bot_panel.add(trace_panel);
		
		//North container 
		//final JPanel top_panel = new JPanel();
		JToolBar top_panel = new JToolBar("Still draggable", JToolBar.HORIZONTAL);
		//top_panel.setLayout(new BoxLayout(top_panel, BoxLayout.X_AXIS));
		//Elements for North container
		JButton button_save = MakeButton.makeButton("saveas.gif", "Save", "Save");
		button_save.addActionListener(save_action_listener);
		JButton button_open = MakeButton.makeButton("open.gif", "Open", "Open");
		JButton button_color = MakeButton.makeButton("none", "Current color", " ");
		button_color.setBackground(new Color(255, 255, 255));
		button_color.setMaximumSize(new Dimension(60, 40));
		JLabel label_width = MakeButton.makeLabel("width.gif", "Width");
		
		JComboBox combo_width = new JComboBox(Values.line_widths); 
		combo_width.setMaximumSize(new Dimension(60, 30));
		combo_width.setSelectedIndex(0);
		
		
		JComboBox combo_line_type = new JComboBox(Values.line_types); 
		combo_line_type.setSelectedIndex(0);
		combo_line_type.setMaximumSize(new Dimension(120, 30));
		
		JLabel label_text_size = MakeButton.makeLabel("text_size.gif", "Text size");
		
		JTextField text_size = new JTextField();
		text_size.setText(Integer.toString(Values.text_size));
		text_size.setMaximumSize(new Dimension(60, 30));
				
		JLabel label_dim_text_size = MakeButton.makeLabel("dim_size.gif", "Dimention size");
		
		JTextField dim_text_size = new JTextField();
		dim_text_size.setText(Integer.toString(Values.dim_text_size));
		dim_text_size.setMaximumSize(new Dimension(60, 30));
		
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
		top_panel.add(text_size);
		
		top_panel.add(Box.createHorizontalStrut(g));
		
		top_panel.add(label_dim_text_size);
		top_panel.add(dim_text_size);
		
		top_panel.add(Box.createHorizontalStrut(g));
		
		top_panel.add(button_undo);
		
		//Addition all containers to frame
		Global_var.jframe.add(top_panel, BorderLayout.NORTH);
		Global_var.jframe.add(bot_panel, BorderLayout.SOUTH);
		Global_var.jframe.add(Global_var.glcanvas, BorderLayout.CENTER);
		
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
				Global_var.jframe.dispose();
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
				if (Global_var.dim_style == null){
					Global_var.dim_style = new dim_style();
				}
			}
		});
		
		item_line_style.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (Global_var.line_style == null){
					Global_var.line_style = new line_style();
				}
			}
		});
		
		item_text_style.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (Global_var.text_style == null){
					Global_var.text_style = new text_style();
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
				if (Global_var.about_prog == null){
					Global_var.about_prog = new about();
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
		
		Global_var.jframe.setJMenuBar(menuBar);
		
		
		Global_var.jframe.setVisible(true);
	}
}