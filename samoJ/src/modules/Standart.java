package modules;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

import core.Core;
import modules.base_draw_entities.Class_base_draw;
import modules.standard_objects.circle.draw_circle_1;
import modules.standard_objects.line.draw_line_1;

//import javax.swing.ImageIcon;
//import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Standart {
	
	//private Core core = Core.c;
	public static void addButtonsBar(JFrame jframe){
		class button_line_action  implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				Class_base_draw draw_action = new draw_line_1();
				draw_action.run();
			}
		}
		class button_circle_action  implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				Class_base_draw draw_action = new draw_circle_1();
				draw_action.run();
			}
		}
		class button_plus_action implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				Core.c.zoom.button_zoom('+');
			}
		}
		class button_minus_action implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				Core.c.zoom.button_zoom('-');
			}
		}
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
		button_plus.addActionListener(new button_plus_action());
			
		
		button_minus.addActionListener(new button_minus_action());
			
		
		button_line.addActionListener(new button_line_action());
		button_circle.addActionListener(new button_circle_action());
			
		
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
