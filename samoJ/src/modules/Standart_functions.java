package modules;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;


public class Standart_functions {

	public static void addButtonsBar(JFrame jframe){
		//toolBar
		JToolBar toolBar = new JToolBar("Still draggable", JToolBar.VERTICAL);
		
		//Buttons
		JButton button_copy = MakeButton.makeButton("copy.gif", "Copy objects", "Copy");
		JButton button_move = MakeButton.makeButton("move.gif", "Move objects", "Move");
		JButton button_rotate = MakeButton.makeButton("rotate.gif", "Rotate objects", "Rotate");
		JButton button_mirror = MakeButton.makeButton("mirror.gif", "Mirror objects", "Mirror");
		JButton button_offset = MakeButton.makeButton("offset.gif", "Offset objects", "Offset");
		JButton button_copy_properties = MakeButton.makeButton("copy_properties.gif", "Copy object properties", "Copy properties");
		JButton button_fillet = MakeButton.makeButton("fillet.gif", "Fillet objects", "Fillet");
		JButton button_trim = MakeButton.makeButton("trim.gif", "Trim objects", "Trim");
		JButton button_extend = MakeButton.makeButton("extend.gif", "Extend objects", "Extend");
		JButton button_scale = MakeButton.makeButton("scale.gif", "Scale objects", "Scale");
		JButton button_chain_dim_line = MakeButton.makeButton("chain_dim.gif", "Chain dimension line", "Chain dimension line");
		
		
		//Actions for buttons
		
		
		//Add buttons
		toolBar.add(button_copy);
		toolBar.add(button_move);
		toolBar.add(button_rotate);
		toolBar.add(button_mirror);
		toolBar.add(button_offset);
		toolBar.add(button_copy_properties);
		toolBar.add(button_fillet);
		toolBar.add(button_trim);
		toolBar.add(button_extend);
		toolBar.add(button_scale);
		toolBar.add(button_chain_dim_line);
		
		
    	jframe.add(toolBar, BorderLayout.EAST);


	}

	}
