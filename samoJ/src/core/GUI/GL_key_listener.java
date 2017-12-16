package core.GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.Core;
import core.navigation.Zoom;

public class GL_key_listener implements KeyListener{
	private Core core = Core.c;
	
	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		if (c == '+' | c == '-'){
			core.zoom.button_zoom(c);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ESCAPE || (key == KeyEvent.VK_ENTER && core.global.draw_new_object == true)){
			System.out.println("Kill");
			core.program_state.set_default();
		}
		else if(key == KeyEvent.VK_ENTER && core.global.draw_new_object == false){
			if(core.global.old_function == null) {
				System.out.println("Old function is indefinite yet");
			}
			else{
				core.global.old_function.run();
				}
			
		}
		
	}
}