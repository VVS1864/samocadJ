package core.GUI;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.cad_demo;



public class about extends JDialog{

	public about(){
		super(cad_demo.jframe, "About Samocad");
		setSize(350, 300);
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowevent) {
				dispose();
				cad_demo.about_prog = null;
			}	
		});
		JPanel panel = new JPanel();

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		URL imageURL = about.class.getResource("/res/icon.gif");

		JLabel label_1 = new JLabel("<html><center><img src='" + imageURL +"'>"
				+ "<h1>Samocad</h1> "
				+ "Programm Samocad is open software and designed for "
				+ "create simple drawings, as partial alternative to AutoCAD<br><br>"
				+ "License GPLv3<center/><html/>");

		panel.add(label_1);



		add(panel);
		setVisible(true);
	}

}