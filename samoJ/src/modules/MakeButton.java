package modules;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class MakeButton {
	
	public static JButton makeButton(String imageName,
			// actionCommand,
			String toolTipText,
			String altText){
		
	
		//Look for the image.
		String imgLocation = "/res/"
				+ imageName;
		URL imageURL = Object.class.getResource(imgLocation);
		
		JButton button = new JButton();
		
		button.setToolTipText(toolTipText);
		

		if (imageURL != null) {                      //image found
			button.setIcon(new ImageIcon(imageURL, altText));
		} else {                                     //no image found
			button.setText(altText);
			System.err.println("Resource not found: "
					+ imgLocation);
		}

		return button;
	}
	
	public static JLabel makeLabel(String imageName, String altText){
		String imgLocation = "/res/"
				+ imageName;
		URL imageURL = Standart.class.getResource(imgLocation);
		
		JLabel label = new JLabel();
		
		if (imageURL != null) {                      //image found
			label.setIcon(new ImageIcon(imageURL, altText));
		} else {                                     //no image found
			label.setText(altText);
			System.err.println("Resource not found: "
					+ imgLocation);
		}
		
		return label;
	}
	
}