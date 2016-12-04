package mudules;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

import core.navigation.Zoom;

//import javax.swing.ImageIcon;
//import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Standart {

	public static void addButtonsBar(JFrame jframe){
		JToolBar toolBar = new JToolBar("Still draggable", JToolBar.VERTICAL);
		JButton button = null;
		button = MakeButton.makeButton("zoom+.gif", "Zoom +", "Zoom+");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Zoom.button_zoom('+');
			}
			});
		
		toolBar.add(button);
		
		button =MakeButton.makeButton("zoom-.gif", "Zoom -", "Zoom-");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Zoom.button_zoom('-');
			}
			});
		
		toolBar.add(button);
		
		/*
		button = MakeButton.makeButton("line.gif", "Draw new line", "Line");
		toolBar.add(button);
		
		button = MakeButton.makeButton("circle.gif", "Draw new circle", "Circle");
		toolBar.add(button);
		*/
    	jframe.add(toolBar, BorderLayout.WEST);


	}

	}
