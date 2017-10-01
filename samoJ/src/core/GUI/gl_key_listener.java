package core.GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.Global_var;
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
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ESCAPE || (key == KeyEvent.VK_ENTER && Global_var.draw_new_object == true)){
			System.out.println("Kill");
			program_state.set_default();
		}
		else if(key == KeyEvent.VK_ENTER && Global_var.draw_new_object == false){
			if(Global_var.old_function == null) {
				System.out.println("Old function indefinite yet");
			}
			else{
				Global_var.old_function.run();
				}
			
		}
		
	}
}