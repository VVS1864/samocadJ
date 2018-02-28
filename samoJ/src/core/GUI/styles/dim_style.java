package core.GUI.styles;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;

import javax.swing.JComboBox;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.Core;
import modules.MakeButton;

public class dim_style extends base_style_dialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Core core = Core.c;
	public dim_style(){
		super(Core.c.gui.jframe, "Dimension style", 500, 200);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowevent) {
				dispose();
				core.gui.dim_style = null;
			}	
		});
		
		// Common panel
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));		
		
		// panel1 - offset from dim line
		JLabel label1 = new JLabel("Offset from dim line");
		JTextField text1 = new JTextField();
		text1.setText(Integer.toString(core.values.dim_text_offset));
		properties_panel.add(label1);
		properties_panel.add(text1);
		

		// panel2 - arrowhead size
		JLabel label2 = new JLabel("Arrowhead size");
		JTextField text2 = new JTextField();
		text2.setText(Integer.toString(core.values.dim_arrowhead_size));
		properties_panel.add(label2);
		properties_panel.add(text2);
		
		// panel3 - extend dim lines
		JLabel label3 = new JLabel("Extend dim lines");
		JTextField text3 = new JTextField();
		text3.setText(Integer.toString(core.values.dim_extend_lines));
		properties_panel.add(label3);
		properties_panel.add(text3);
		
		// panel4 - extend dim ticks
		JLabel label4 = new JLabel("Extend dim ticks");
		JTextField text4 = new JTextField();
		text4.setText(Integer.toString(core.values.dim_extend_ticks));
		properties_panel.add(label4);
		properties_panel.add(text4);
		
		// panel5 - dim arrowheads
		JLabel label5 = new JLabel("Dim arrowheads");
		
		JComboBox combo_arrowhead = new JComboBox(core.values.arrowhead_types); 
		combo_arrowhead.setSelectedItem(core.values.dim_arrowheads);

		properties_panel.add(label5);
		properties_panel.add(combo_arrowhead);

		// Image dimension properties
		JLabel label_img = MakeButton.makeLabel("dim_properties.gif", " ");

		// Complete common panel
		panel.add(label_img);
		panel.add(apply_panel);

		add(panel);
		setVisible(true);
	}

}