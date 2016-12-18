package core.GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.navigation.Zoom;

public class gl_key_listener implements KeyListener{
	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		Zoom.button_zoom(c);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}