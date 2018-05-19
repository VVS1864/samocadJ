package core.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import core.Core;
import core.functions.Delete;

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
		Component focus_owner = core.gui.jframe.getMostRecentFocusOwner();
		if(focus_owner==core.gui.field_text_size) {
			core.values.text_size = check_double_field(core.gui.field_text_size, core.values.text_size);
		}
		else if(focus_owner==core.gui.field_dim_text_size) {
			core.values.dim_text_size = check_double_field(core.gui.field_dim_text_size, core.values.dim_text_size);
		}
		else {
			
			if (key == KeyEvent.VK_ESCAPE || (key == KeyEvent.VK_ENTER
					&& core.global.draw_new_object == true)) {
				System.out.println("Kill");
				core.program_state.set_default();
			}
			else if (core.global.draw_new_object == false) {
				if (key == KeyEvent.VK_ENTER) {
					if (core.global.old_function == null) {
						System.out.println("Old function is indefinite yet");
					}
					else {
						core.global.old_function.run();
					}
				}
				else if (key == KeyEvent.VK_DELETE && !core.global.collection.get_shapes().isEmpty()) {
					new Delete().delete(core.global.collection.get_shapes());
				}
			}
		}
		
	}

	private double check_double_field(JTextField field, double current_value) {
		String str = field.getText();
		
		double new_value = current_value;
		try {
			new_value = Double.parseDouble(str);
			field.setBackground(Color.WHITE);
		}
		catch(NumberFormatException ex) {
			field.setBackground(Color.RED);
		}
		return new_value;
		
	}
}