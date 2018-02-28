package core.GUI.styles;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class base_style_dialog extends JDialog{
	protected JPanel properties_panel;
	protected GridLayout grid;
	protected JButton button_apply;
	protected JPanel apply_panel;

	
	public base_style_dialog(JFrame jframe, String title, int w, int h){
		super(jframe, title);
		setSize(w, h);
		setResizable(false);
		
		//Button Apply
		button_apply = new JButton("Apply");
		button_apply.setAlignmentX(RIGHT_ALIGNMENT);
		
		// Panel for properties 	
		properties_panel = new JPanel();
		grid = new GridLayout(0,2);
		properties_panel.setLayout(grid);
		properties_panel.setAlignmentX(RIGHT_ALIGNMENT);
		
		//panel for properties_panel and button apply
		apply_panel = new JPanel();
		apply_panel.setLayout(new BoxLayout(apply_panel, BoxLayout.PAGE_AXIS));
		apply_panel.add(properties_panel);
		apply_panel.add(button_apply);
	}
	
	public void add_applay(){
		//Button Apply
		button_apply = new JButton("Apply");
		properties_panel.add(button_apply);
	}
}