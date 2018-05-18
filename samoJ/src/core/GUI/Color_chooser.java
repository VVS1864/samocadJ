package core.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;

import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Color_chooser extends JColorChooser{

	public Color_chooser() {
		
		
		AbstractColorChooserPanel[] oldPanels = getChooserPanels();
		for(AbstractColorChooserPanel p:oldPanels){
            String displayName=p.getDisplayName();
            switch (displayName) {
                case "HSV":
                    removeChooserPanel(p);
                    break;
                case "HSL":
                    removeChooserPanel(p);
                    break;
                case "CMYK":
                    removeChooserPanel(p);
                    break;
            }
		}
		
		
	}

}
