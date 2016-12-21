package modules;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

import core.navigation.Zoom;
import modules.base_draw_entities.base_draw;
import modules.standard_objects.line.draw_line;

//import javax.swing.ImageIcon;
//import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Standart {

	public static void addButtonsBar(JFrame jframe){
		//toolBar
		JToolBar toolBar = new JToolBar("Still draggable", JToolBar.VERTICAL);
		
		//Buttons
		JButton button_plus = MakeButton.makeButton("zoom+.gif", "Zoom +", "Zoom+");
		JButton button_minus = MakeButton.makeButton("zoom-.gif", "Zoom -", "Zoom-");
		JButton button_line = MakeButton.makeButton("line.gif", "Draw new line", "Line");
		JButton button_circle = MakeButton.makeButton("circle.gif", "Draw new circle", "Circle");
		JButton button_arc = MakeButton.makeButton("arc.gif", "Draw new arc", "Arc");
		JButton button_dim = MakeButton.makeButton("dim.gif", "Draw new dimension", "Dim");
		JButton button_text = MakeButton.makeButton("text.gif", "Draw new text", "Text");
		
		//Actions for buttons
		button_plus.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Zoom.button_zoom('+');
			}
			});
		
		button_minus.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Zoom.button_zoom('-');
			}
			});
		
		button_line.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				draw_line draw_action = new draw_line();
			}
			});
		
		//Add buttons
		toolBar.add(button_plus);
		toolBar.add(button_minus);
		toolBar.add(button_line);
		toolBar.add(button_circle);
		toolBar.add(button_arc);
		toolBar.add(button_dim);
		toolBar.add(button_text);
		
    	jframe.add(toolBar, BorderLayout.WEST);


	}

	}
