package mudules;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MakeButton {
	
	protected static JButton makeButton(String imageName,
			// actionCommand,
			String toolTipText,
			String altText){
		
	
		//Look for the image.
		String imgLocation = "/res/"
				+ imageName;
		URL imageURL = Standart.class.getResource(imgLocation);

		//Create and initialize the button.
		JButton button = new JButton();
		//button.setActionCommand(actionCommand);
		button.setToolTipText(toolTipText);
		//button.addActionListener(new ActionListener(){

			//@Override
			//public void actionPerformed(ActionEvent e) {
				//actionCommand()
				
			//}
			
			
		//});

		if (imageURL != null) {                      //image found
			button.setIcon(new ImageIcon(imageURL, altText));
		} else {                                     //no image found
			button.setText(altText);
			System.err.println("Resource not found: "
					+ imgLocation);
		}

		return button;
	}
}