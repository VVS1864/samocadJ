package core.GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.program_state;
import core.navigation.Zoom;

public class gl_key_listener implements KeyListener{
	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		if (c == '+' | c == '-'){
			Zoom.button_zoom(c);
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
			System.out.println("Kill");
			program_state.set_default();
		}
		
	}
}