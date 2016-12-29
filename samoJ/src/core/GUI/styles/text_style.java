package core.GUI.styles;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import core.Values;
import core.Global_var;

public class text_style extends base_style_dialog{
	public text_style(){
		super("Text style", 250, 80);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowevent) {
				dispose();
				Global_var.text_style = null;
			}
		});
		// panel1 - letters width factor
		JLabel label1 = new JLabel("Letters width factor");
		JTextField text1 = new JTextField(4);
		text1.setText(Double.toString(Values.text_width_factor));
		properties_panel.setLayout(new FlowLayout());
		properties_panel.add(label1);
		properties_panel.add(text1);	

		add(apply_panel);

		setVisible(true);
				
		
	}
}