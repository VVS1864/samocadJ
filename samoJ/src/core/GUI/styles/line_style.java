package core.GUI.styles;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;

import javax.swing.JTextField;

import core.Values;
import core.cad_demo;

public class line_style extends base_style_dialog{

	public line_style(){
		super("Line style", 180, 80);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowevent) {
				dispose();
				cad_demo.line_style = null;
			}
		});
		
		// panel1 - line size
		JLabel label1 = new JLabel("Line size");
		JTextField text1 = new JTextField();
		text1.setText(Double.toString(Values.stipple_factor));
		properties_panel.add(label1);
		properties_panel.add(text1);	
		
		add(apply_panel);
		
		setVisible(true);
		
	}
}